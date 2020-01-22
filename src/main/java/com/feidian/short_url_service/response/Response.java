package com.feidian.short_url_service.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @author yinchao
 * @Date 2020/1/22 16:55
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Response {
    private Meta meta;
    private Map<String, Object> data;
}
