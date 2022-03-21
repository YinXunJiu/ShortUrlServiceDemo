package org.feidian.short_url_service.constant;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author yinchao
 * @Date 2020/1/22 17:49
 */
@Component
public class Constant {
    private static final String PREFIX_URL_FORMATTER = "http://%s:%d/short_url_service/find/";
    @Value("${server.port}")
    private int port;

    public String getPrefixUrl() {
        String prefix = null;
        try {
            prefix = String.format(PREFIX_URL_FORMATTER, InetAddress.getLocalHost().getHostAddress(), port);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return prefix;
    }

}
