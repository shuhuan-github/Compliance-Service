package com.siemens.osa.module.cs;

import com.siemens.osa.data.es.service.getdata.impl.GetESInfoServiceImpl;
import com.siemens.osa.data.es.entity.ESInfo;
import com.siemens.osa.module.cs.service.getes.impl.GetESServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class GetESTest {
    @Autowired
    @Qualifier("esInfo")
    GetESInfoServiceImpl esInfoGetService;

    @Test
    public void testGetAllES() {
        GetESServiceImpl getESService = new GetESServiceImpl(esInfoGetService);
        List<ESInfo> esInfoList = getESService.getES();
        System.out.println(esInfoList.size());
        for (ESInfo esInfo: esInfoList) {
            System.out.println(esInfo);
            List<String> result = esInfo.getResult();
            String ruleId = esInfo.getRuleId();
            for (String s : result) {
                System.out.println(ruleId);
                System.out.println(s);
            }
        }
    }
}
