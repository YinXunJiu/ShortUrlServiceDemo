package org.feidian.url.service.api;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;
import org.feidian.dha.spring.boot.autoconfigure.DhaService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xunjiu
 * @date 2022/3/24 21:34
 **/
@RestController
@Slf4j
public class DhaTest {
    @Resource
    private DhaService dhaService;

    @Value("${dha.config.app-name}")
    private String appName;

    @GetMapping("/dha")
    public void test() {
        dhaService.getRoleAndSetDataSource(appName);
    }
}
