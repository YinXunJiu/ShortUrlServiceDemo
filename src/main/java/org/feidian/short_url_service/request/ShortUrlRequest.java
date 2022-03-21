package org.feidian.short_url_service.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yinchao
 * @Date 2020/1/22 17:14
 */
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ShortUrlRequest {
    private String sourceUrl;
}
