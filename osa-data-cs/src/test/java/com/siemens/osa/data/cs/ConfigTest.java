package com.siemens.osa.data.cs;

import com.siemens.osa.data.cs.entity.ConfigInfo;
import com.siemens.osa.data.cs.module.ConfigService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConfigTest {
    @Autowired
    private ConfigService configService;

    @Test
    public void testGetAllConfig() {
        List<ConfigInfo> configInfoList = configService.getAllConfig();
        for (ConfigInfo configInfo: configInfoList) {
            System.out.println("############config item############");
            System.out.println(configInfo);
            System.out.println("$$$$$$$$$$$$$$ data $$$$$$$$$$$$$$$$");
            List<String> data = configInfo.getData();
            for (String datum : data) {
                System.out.println(datum);
            }
            System.out.println("$$$$$$$$$$$$$$ param $$$$$$$$$$$$$$$$");
            List<String> params = configInfo.getParams();
            for (String param : params) {
                System.out.println(param);
            }
        }
    }

    @Test
    public void testGetConfigById() {
        int id = 2;
        List<ConfigInfo> configInfoList = configService.getConfigById(id);
        for (ConfigInfo configInfo : configInfoList) {
            System.out.println(configInfo);
        }
    }

    @Test
    public void testGetConfigMapById() {
        int id = 2;
        Map<String, ConfigInfo> configInfoMap = configService.getConfigMapById(id);
        ConfigInfo configInfo = configInfoMap.get("BL999_6629");
        List<String> data = configInfo.getData();
        System.out.println(data);
        for (String datum : data) {
            System.out.println(datum);
        }
    }

    @Test
    public void testGetConfigListById() {
        int id = 2;
        List<String> configListById = configService.getConfigDataListByIdAndRuleId(id, "BL999_7387");
        for (String res: configListById) {
            System.out.println(res);
        }
    }
}
