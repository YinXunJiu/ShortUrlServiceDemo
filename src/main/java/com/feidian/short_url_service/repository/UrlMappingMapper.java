package com.feidian.short_url_service.repository;

import com.feidian.short_url_service.domain.UrlMapping;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author yinchao
 * @Date 2020/1/22 20:22
 */
@Mapper
public interface UrlMappingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UrlMapping record);

    int insertSelective(UrlMapping record);

    UrlMapping selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UrlMapping record);

    int updateByPrimaryKey(UrlMapping record);

    Integer selectMaxId();

    Integer selectIdBySourceUrl(String sourceUrl);
}