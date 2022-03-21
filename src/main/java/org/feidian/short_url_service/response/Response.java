package org.feidian.short_url_service.response;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
