package com.feidian.short_url_service.service;

import com.feidian.short_url_service.constant.Constant;
import com.feidian.short_url_service.domain.UrlMapping;
import com.feidian.short_url_service.repository.UrlMappingMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author yinchao
 * @Date 2020/1/22 17:27
 */
@Service
public class UrlMappingService {

    @Resource
    private UrlMappingMapper urlMappingMapper;


    public int deleteByPrimaryKey(Integer id) {
        return urlMappingMapper.deleteByPrimaryKey(id);
    }


    public int insert(UrlMapping record) {
        urlMappingMapper.insert(record);
        return record.getId();
    }


    public int insertSelective(UrlMapping record) {
        return urlMappingMapper.insertSelective(record);
    }


    public UrlMapping selectByPrimaryKey(Integer id) {
        return urlMappingMapper.selectByPrimaryKey(id);
    }


    public int updateByPrimaryKeySelective(UrlMapping record) {
        return urlMappingMapper.updateByPrimaryKeySelective(record);
    }


    public int updateByPrimaryKey(UrlMapping record) {
        return urlMappingMapper.updateByPrimaryKey(record);
    }

    /**
     * 根据源链接找对应的id
     *
     * @param sourceUrl 源链接
     * @return 有的话返回对应的主键id, 没有则返回null
     */
    public Integer selectIdBySourceUrl(String sourceUrl){
        return urlMappingMapper.selectIdBySourceUrl(sourceUrl);
    }

    /**
     *
     * @param id
     * @return
     */
    public String findSourceUrl(Integer id) {
        UrlMapping urlMapping = selectByPrimaryKey(id);
        return urlMapping == null ? null : String.valueOf(urlMapping.getSourceUrl());
    }
}
