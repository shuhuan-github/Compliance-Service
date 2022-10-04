package com.siemens.osa.data.cs;

import com.siemens.osa.data.cs.config.Inet;
import com.siemens.osa.data.cs.entity.ResultInfo;
import com.siemens.osa.data.cs.module.ResultService;
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
public class ResultTest {
    @Autowired
    private ResultService resultService;

    @Test
    public void testGetAllResult() {
        List<ResultInfo> resultInfoList = resultService.getAllResult();
        for(ResultInfo resultInfo: resultInfoList) {
            System.out.println(resultInfo.toString());
        }
    }

    @Test
    public void testGetResultById() {
        int id = 2;
        List<ResultInfo> resultInfoList = resultService.getResultById(id);
        for (ResultInfo resultInfo : resultInfoList) {
            System.out.println(resultInfo);
        }
    }

    @Test
    public void testGetRecentResult(){
        List<ResultInfo> recentResult = resultService.getRecentResult();
        for (ResultInfo resultInfo : recentResult) {
            System.out.println(resultInfo);
        }
    }

    @Test
    public void testGetRecentResultWithZone(){
        String beginTimeStr = "2022-07-11 23:33:00";
        String endTimeStr = "2022-07-11 23:34:00";
        Timestamp beginTime = Timestamp.valueOf(beginTimeStr);
        Timestamp endTime = Timestamp.valueOf(endTimeStr);
        List<ResultInfo> recentResultWithZone = resultService.getRecentResultWithZone(beginTime, endTime);
        for (ResultInfo resultInfo : recentResultWithZone) {
            System.out.println(resultInfo);
        }
    }

    @Test
    public void testAddResult() {
        List<String> expected = new ArrayList<>();
        expected.add("0");
        expected.add("1");
        List<String> actual = new ArrayList<>();
        actual.add("0");
        actual.add("2");
        try {
            ResultInfo resultInfo=new ResultInfo(1,new Timestamp(System.currentTimeMillis()), "+2",2, "windows10", "Chinese",
                    new Inet(InetAddress.getLocalHost().getHostAddress()), new Inet("192.168.1.155"), "BL696_0461",
                    actual,expected,"failed");
            resultService.addResult(resultInfo);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
