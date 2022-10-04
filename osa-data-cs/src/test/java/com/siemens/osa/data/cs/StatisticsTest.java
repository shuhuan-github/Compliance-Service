package com.siemens.osa.data.cs;

import com.siemens.osa.data.cs.config.Inet;
import com.siemens.osa.data.cs.entity.StatisticsInfo;
import com.siemens.osa.data.cs.module.StatisticsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StatisticsTest {
    @Autowired
    StatisticsService statisticsService;

    @Test
    public void testGetAllStatistics(){
        List<StatisticsInfo> allStatistics = statisticsService.getAllStatistics();
        for (StatisticsInfo allStatistic : allStatistics) {
            System.out.println(allStatistic);
        }
    }

    @Test
    public void testInsertStatistics(){
        List<String> failedList=new LinkedList<>();
        failedList.add("BL999_1234");
        failedList.add("BL999_2345");
        List<String> passList=new LinkedList<>();
        passList.add("BL666_1234");
        passList.add("BL666_2345");

        Inet inet = new Inet("127.0.0.1");
        Timestamp timestamp = Timestamp.valueOf("2022-07-22 00:00:00");

        statisticsService.insertStatistics(timestamp,"+2",inet,failedList,passList);
    }

    @Test
    public void testGetInstantStaInfo(){
        StatisticsInfo instantStaInfoByHostIp = statisticsService.getInstantStaInfoByHostIp("192.168.1.232");
        System.out.println(instantStaInfoByHostIp);
    }

    @Test
    public void testGetZoneInstantStaInfo(){
        String beginTimeStr = "2022-7-14 00:00:00";
        String endTimeStr = "2022-07-16 00:00:00";
        String hostIp = "192.168.192.46";

        StatisticsInfo zoneInstantStaInfoByHostIp = statisticsService.getZoneInstantStaInfoByHostIp(hostIp, Timestamp.valueOf(beginTimeStr), Timestamp.valueOf(endTimeStr));

        System.out.println(zoneInstantStaInfoByHostIp);

//        statisticsService.ge  tZoneInstantStaInfoByHostIp("192.168.1.232",)
    }
}
