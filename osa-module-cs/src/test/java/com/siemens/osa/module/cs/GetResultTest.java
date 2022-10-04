package com.siemens.osa.module.cs;

import com.siemens.osa.data.cs.config.Inet;
import com.siemens.osa.data.cs.entity.ResultInfo;
import com.siemens.osa.data.cs.module.ResultService;
import com.siemens.osa.module.cs.service.getcs.impl.GetResultServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GetResultTest {
    @Autowired
    private ResultService resultService;

    @Test
    public void testGetAllResult() {
        GetResultServiceImpl getResultService = new GetResultServiceImpl(resultService);
        List<ResultInfo> resultInfoList = getResultService.getResult();
        for (ResultInfo resultInfo: resultInfoList) {
            System.out.println(resultInfo);
        }
    }

    @Test
    public void testGetResultById() {
        int id = 2;
        GetResultServiceImpl getResultService = new GetResultServiceImpl(resultService);
        List<ResultInfo> resultInfoList = getResultService.getResultById(id);
        for (ResultInfo resultInfo: resultInfoList) {
            System.out.println(resultInfo);
        }
    }

//    @Test
//    public void testGetResultMapById() {
//        int id = 2;
//        GetResultServiceImpl getResultService = new GetResultServiceImpl(resultService);
//        Map<String, ResultInfo> resultInfoMap = getResultService.GetResultMapById(id);
//        ResultInfo resultInfo = resultInfoMap.get("BL696_0711");
//        System.out.println(resultInfo);
//        String[] expected = resultInfo.getExpected();
//        for (String s : expected) {
//            System.out.println(s);
//        }
//    }

    @Test
    public void testGetRecentResult() {
        GetResultServiceImpl getResultService = new GetResultServiceImpl(resultService);
        List<ResultInfo> resultInfoList = getResultService.getRecentResult();
        for (ResultInfo resultInfo : resultInfoList) {
            System.out.println(resultInfo);
        }
    }

    @Test
    public void testGetRecentResultWithZone(){
        GetResultServiceImpl getResultService = new GetResultServiceImpl(resultService);
        String beginTimeStr = "2022-08-11 00:00:00";
        String endTimeStr = "2022-08-12 00:00:00";
        Timestamp beginTime = Timestamp.valueOf(beginTimeStr);
        Timestamp endTime = Timestamp.valueOf(endTimeStr);
        List<ResultInfo> resultInfoList = getResultService.getRecentResultWithZone(beginTime, endTime);
        for (ResultInfo resultInfo : resultInfoList) {
            System.out.println(resultInfo);
        }
    }

    @Test
    public void testInsertResult(){
        GetResultServiceImpl getResultService = new GetResultServiceImpl(resultService);
        List<String> expected = new ArrayList<>();
        expected.add("0");
        expected.add("1");
        List<String> actual = new ArrayList<>();
        actual.add("0");
        actual.add("2");
        try {
            getResultService.insertResult(new ResultInfo(1,new Timestamp(System.currentTimeMillis()),"+2" ,2, "windows10", "Chinese",
                    new Inet(InetAddress.getLocalHost().getHostAddress()), new Inet("192.168.1.155"), "BL696_0461",
                    null,null,"failed"));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
