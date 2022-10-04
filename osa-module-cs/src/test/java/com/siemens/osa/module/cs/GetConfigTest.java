package com.siemens.osa.module.cs;

import com.siemens.osa.data.cs.entity.ConfigInfo;
import com.siemens.osa.data.cs.module.ConfigService;
import com.siemens.osa.module.cs.service.getcs.impl.GetConfigServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GetConfigTest {
    @Autowired
    private ConfigService configService;

    @Test
    public void testGetAllConfig() {
        GetConfigServiceImpl getConfigService = new GetConfigServiceImpl(configService);
        List<ConfigInfo> configInfoList = getConfigService.getConfig();
        for (ConfigInfo configInfo: configInfoList) {
            System.out.println(configInfo);
        }
    }

    @Test
    public void testGetConfigById() {
        int id = 2;
        GetConfigServiceImpl getConfigService = new GetConfigServiceImpl(configService);
        List<ConfigInfo> configInfoList = getConfigService.getConfigById(id);
        for (ConfigInfo configInfo: configInfoList) {
            System.out.println(configInfo);
        }
    }

    @Test
    public void testGetConfigMapById() {
        int id = 2;
        GetConfigServiceImpl getConfigService = new GetConfigServiceImpl(configService);
        Map<String, ConfigInfo> configInfoMap = getConfigService.getConfigMapById(id);
        ConfigInfo configInfo = configInfoMap.get("BL696_0711");
        List<String> data = configInfo.getData();
        for (String datum : data) {
            System.out.println(datum);
        }
    }
}
