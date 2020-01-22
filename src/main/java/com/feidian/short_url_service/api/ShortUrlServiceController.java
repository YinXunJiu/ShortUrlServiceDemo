package com.feidian.short_url_service.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.feidian.short_url_service.constant.Constant;
import com.feidian.short_url_service.domain.UrlMapping;
import com.feidian.short_url_service.request.ShortUrlRequest;
import com.feidian.short_url_service.response.Response;
import com.feidian.short_url_service.response.Responses;
import com.feidian.short_url_service.service.UrlMappingService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author yinchao
 * @Date 2020/1/22 16:54
 */
@RestController
@RequestMapping("/short_url_service")
@Slf4j(topic = "ShortUrlServiceController")
public class ShortUrlServiceController {
    @Autowired
    private UrlMappingService urlMappingService;

    /**
     * 创建一个短链接
     *
     * @param shortUrlRequest 包括源短链接的请求
     * @return 返回操作信息, 如果已经产生过, 则返回原来的链接, 如果没有产生过, 则生成一个新的短链接
     */
    @PostMapping("/create")
    public Response createShortUrl(@RequestBody ShortUrlRequest shortUrlRequest) {
        if(shortUrlRequest.getSourceUrl().contains("short_url_service")){
            return Responses.errorResponse("源链接存在不允许字符");
        }
        Integer result = urlMappingService.selectIdBySourceUrl(shortUrlRequest.getSourceUrl());
        if (result != null) {
            return Responses.errorResponse("this url has been convert, the short url is " + Constant.PREFIX_URL+result);
        }
        Map<String, Object> data = new HashMap<>(1);
        data.put("result", Constant.PREFIX_URL+urlMappingService.insert(new UrlMapping(null, shortUrlRequest.getSourceUrl())));
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
        if (id == null || !Pattern.matches("^[0-9]+$", String.valueOf(id))) {
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
        return Responses.successResponse();
    }
}