package org.feidian.short_url_service.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yinchao
 * @Date 2020/1/22 20:22
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UrlMapping {
    /**
     * 主键
     */
    private Integer id;

    private String sourceUrl;

    public static UrlMappingBuilder builder() {
        return new UrlMappingBuilder();
    }
}