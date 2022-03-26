package org.feidian.url.service.configuration;

import javax.servlet.http.HttpServletRequest;

import org.feidian.url.service.response.Response;
import org.feidian.url.service.response.Responses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yinchao
 * @Date 2019/8/25 16:51
 */
@ControllerAdvice
@RestController
public class ExceptionHandler {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public Response defaultErrorHandler(Exception e, HttpServletRequest request) {
        logger.info("请求地址：" + request.getRequestURL());
        logger.error("异常信息：", e);
        return Responses.errorResponse("异常类型:" + e.getClass() + "\n异常信息:" + e.getCause());
    }
}
