package com.feidian.short_url_service.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.feidian.short_url_service.domain.UrlMappingExample;
import com.feidian.short_url_service.repository.UrlMappingMapper;
import java.util.List;
import com.feidian.short_url_service.domain.UrlMapping;
/**
    *@author yinchao
    *@Date 2020/1/22 17:21
    */
@Service
public class UrlMappingService{

    @Resource
    private UrlMappingMapper urlMappingMapper;

    
    public long countByExample(UrlMappingExample example) {
        return urlMappingMapper.countByExample(example);
    }

    
    public int deleteByExample(UrlMappingExample example) {
        return urlMappingMapper.deleteByExample(example);
    }

    
    public int deleteByPrimaryKey(Integer id) {
        return urlMappingMapper.deleteByPrimaryKey(id);
    }

    
    public int insert(UrlMapping record) {
        return urlMappingMapper.insert(record);
    }

    
    public int insertSelective(UrlMapping record) {
        return urlMappingMapper.insertSelective(record);
    }

    
    public List<UrlMapping> selectByExample(UrlMappingExample example) {
        return urlMappingMapper.selectByExample(example);
    }

    
    public UrlMapping selectByPrimaryKey(Integer id) {
        return urlMappingMapper.selectByPrimaryKey(id);
    }

    
    public int updateByExampleSelective(UrlMapping record,UrlMappingExample example) {
        return urlMappingMapper.updateByExampleSelective(record,example);
    }

    
    public int updateByExample(UrlMapping record,UrlMappingExample example) {
        return urlMappingMapper.updateByExample(record,example);
    }

    
    public int updateByPrimaryKeySelective(UrlMapping record) {
        return urlMappingMapper.updateByPrimaryKeySelective(record);
    }

    
    public int updateByPrimaryKey(UrlMapping record) {
        return urlMappingMapper.updateByPrimaryKey(record);
    }

}
