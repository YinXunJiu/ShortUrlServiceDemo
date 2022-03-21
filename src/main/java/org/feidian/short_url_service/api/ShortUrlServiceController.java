package org.feidian.short_url_service.api;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.feidian.short_url_service.constant.Constant;
import org.feidian.short_url_service.domain.UrlMapping;
import org.feidian.short_url_service.request.ShortUrlRequest;
import org.feidian.short_url_service.response.Response;
import org.feidian.short_url_service.response.Responses;
import org.feidian.short_url_service.service.UrlMappingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yinchao
 * @Date 2020/1/22 16:54
 */
@RestController
@RequestMapping("/short_url_service")
@Slf4j(topic = "ShortUrlServiceController")
public class ShortUrlServiceController {
    @Resource
    private UrlMappingService urlMappingService;

    @Resource
    private Constant constant;

    /**
     * 创建一个短链接
     *
     * @param shortUrlRequest 包括源短链接的请求
     * @return 返回操作信息, 如果已经产生过, 则返回原来的链接, 如果没有产生过, 则生成一个新的短链接
     */
    @PostMapping("/create")
    public Response createShortUrl(@RequestBody ShortUrlRequest shortUrlRequest) {
        if (shortUrlRequest.getSourceUrl().contains("short_url_service")) {
            return Responses.errorResponse("源链接存在不允许字符");
        }
        Integer result = urlMappingService.selectIdBySourceUrl(shortUrlRequest.getSourceUrl());
        if (result != null) {
            return Responses.errorResponse(
                "this url has been convert, the short url is " + constant.getPrefixUrl() + result);
        }
        Map<String, Object> data = new HashMap<>(1);
        data.put("result",
            constant.getPrefixUrl() + urlMappingService.insert(new UrlMapping(null, shortUrlRequest.getSourceUrl())));
        return Responses.successResponse(data);
    }

    /**
     * @param id                  对应url_mapping表的主键id
     * @param httpServletResponse httpServletResponse
     * @return 错误信息或者重定向的源网站
     */
    @SneakyThrows
    @GetMapping("/find/{id}")
    public Response findSourceUrl(@PathVariable("id") String id, HttpServletResponse httpServletResponse) {
        log.info("invoke the api of short_url_service/find/{}", id);
        // id为空或者为非法字符
        if (id == null || !Pattern.matches("^[0-9]+$", id)) {
            log.error("invalid character");
            return Responses.errorResponse("非法字符");
        }
        String message = urlMappingService.findSourceUrl(Integer.valueOf(id));
        // message 为空代表没有找到对应的短链接
        if (message == null) {
            httpServletResponse.setCharacterEncoding("utf-8");
            httpServletResponse.setContentType("application/json;charset=utf-8");
            httpServletResponse.setStatus(200);
            log.info("没有找到对应短链接和源链接");
            return Responses.errorResponse("没有找到对应短链接和源链接");
        }
        httpServletResponse.setStatus(302);
        Map<String, String> data = new HashMap<>(1);
        data.put("result", message);
        httpServletResponse.sendRedirect(message);
        return Responses.successResponse(data);
    }
}