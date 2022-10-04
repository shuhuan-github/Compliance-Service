package com.siemens.osa.module.cs;

import com.siemens.osa.data.cs.config.Inet;
import com.siemens.osa.data.cs.entity.StatisticsInfo;
import com.siemens.osa.data.cs.module.StatisticsService;
import com.siemens.osa.module.cs.service.getcs.impl.GetStatisticsServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GetStatisticsTest {
    @Autowired
    private StatisticsService statisticsService;

    @Test
    public void testGetAllStatistics(){
        GetStatisticsServiceImpl statisticsServiceImpl = new GetStatisticsServiceImpl(statisticsService);
        List<StatisticsInfo> allStatistics = statisticsServiceImpl.getAllStatistics();
        StatisticsInfo statisticsInfo = allStatistics.get(0);
        List<String> failedList = statisticsInfo.getFailedList();
        System.out.println(failedList.size());
        for (String ruleId : failedList) {
            System.out.println(ruleId);
        }
//        for (StatisticsInfo allStatistic : allStatistics) {
//            System.out.println(allStatistic);
//        }
        System.out.println(allStatistics);
    }

    @Test
    public void testStatisticsMap(){
        Map<Inet, Map<Timestamp, StatisticsInfo>> statistics=new HashMap<>();
        Inet inet = new Inet("127.0.0.1");
        Timestamp timestamp = Timestamp.valueOf("2022-07-22 00:00:00");
        Map<Timestamp,StatisticsInfo> timestampStatisticsInfoMap=new HashMap<>();
        StatisticsInfo statisticsInfo1 = new StatisticsInfo();
        statisticsInfo1.setFailedList(new LinkedList<String>());
        statisticsInfo1.setPassList(new LinkedList<String>());
        timestampStatisticsInfoMap.put(timestamp,statisticsInfo1);
        statistics.put(inet,timestampStatisticsInfoMap);
        System.out.println(statistics);

        StatisticsInfo statisticsInfo = statistics.get(inet).get(timestamp);
        List<String> failedList = statisticsInfo.getFailedList();
        failedList.add("BL999_1234");

        System.out.println(statistics);
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

        GetStatisticsServiceImpl getStatisticsService = new GetStatisticsServiceImpl(statisticsService);
        getStatisticsService.insertStatistics(timestamp,"2",inet,failedList,passList);
    }

    @Test
    public void testGetInstantStatistics(){
        GetStatisticsServiceImpl getStatisticsService = new GetStatisticsServiceImpl(statisticsService);
        StatisticsInfo instantStatisticsInfoByHostIp = getStatisticsService.getInstantStatisticsInfoByHostIp("192.168.192.46");
        System.out.println(instantStatisticsInfoByHostIp);
    }

    @Test
    public void testGetZoneInstantStatistics(){
        String beginTimeStr = "2022-7-14 00:00:00";
        String endTimeStr = "2022-07-16 00:00:00";
        String hostIp = "192.168.192.46";

        GetStatisticsServiceImpl getStatisticsService = new GetStatisticsServiceImpl(statisticsService);
        StatisticsInfo zoneInstantStatisticsInfoByHostIp = getStatisticsService.getZoneInstantStatisticsInfoByHostIp(hostIp, Timestamp.valueOf(beginTimeStr), Timestamp.valueOf(endTimeStr));

        System.out.println(zoneInstantStatisticsInfoByHostIp);
    }
}
