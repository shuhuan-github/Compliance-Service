package com.siemens.osa.module.cs;

import com.siemens.osa.data.cs.config.Inet;
import com.siemens.osa.data.cs.entity.ResultInfo;
import com.siemens.osa.data.cs.entity.StatisticsInfo;
import com.siemens.osa.data.es.entity.ESInfo;
import com.siemens.osa.module.cs.service.comparedata.impl.CompareDataServiceImpl;
import com.siemens.osa.module.cs.service.getcs.impl.GetConfigServiceImpl;
import com.siemens.osa.module.cs.service.getcs.impl.GetResultServiceImpl;
import com.siemens.osa.module.cs.service.getcs.impl.GetStatisticsServiceImpl;
import com.siemens.osa.module.cs.service.getes.impl.GetESServiceImpl;
import com.siemens.osa.module.cs.util.TimeUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CompareDataTest {

    @Autowired
    GetConfigServiceImpl getConfigServiceImpl;

    @Autowired
    GetResultServiceImpl getResultServiceImpl;

    @Autowired
    GetStatisticsServiceImpl getStatisticsServiceImpl;

    @Autowired
    GetESServiceImpl getESServiceImpl;

    @Test
    public void testCompareData() throws UnknownHostException {
        CompareDataServiceImpl compareDataService = new CompareDataServiceImpl(getConfigServiceImpl,
                getResultServiceImpl, getStatisticsServiceImpl, getESServiceImpl);
        compareDataService.compareData();
    }

    @Test
    public void test1(){
        List<ResultInfo> result = getResultServiceImpl.getResult();
        for (ResultInfo resultInfo : result) {
            System.out.println(resultInfo);
        }
    }

    @Test
    public void check(){
        List<StatisticsInfo> allStatistics = getStatisticsServiceImpl.getAllStatistics();
//        List<String> failedList = allStatistics.get(0).getPassList();
        List<String> failedList = allStatistics.get(2).getFailedList();
        Inet hostIp = allStatistics.get(2).getHostIp();
        Timestamp timestamp = allStatistics.get(2).getTimestamp();
        System.out.println(failedList.size());
        for (String ruleID : failedList) {
            List<ResultInfo> resultByHostIpTime = getResultServiceImpl.getResultByHostIpTime(hostIp.getAddress(), timestamp, ruleID);
            ResultInfo resultInfo = resultByHostIpTime.get(0);
            List<String> actual = resultInfo.getActual();
            List<String> expected = resultInfo.getExpected();
            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$");
            System.out.println(resultInfo.getRuleId());
            for (int i = 0; i < actual.size(); i++) {
                System.out.println("#######################");
                System.out.println("expected:");
                System.out.println(expected.get(i));
                System.out.println("actual:");
                System.out.println(actual.get(i));
            }
        }
    }

    @Test
    public void test() {
        // List<ConfigInfo> configInfos = getConfigServiceImpl.GetConfig();
        // for (ConfigInfo configInfo : configInfos) {
        // System.out.println(configInfo);
        // }
        // List<ResultInfo> resultInfos = getResultServiceImpl.GetResult();
        // for (ResultInfo resultInfo : resultInfos) {
        // System.out.println(resultInfo);
        // }
        // List<StatisticsInfo> allStatistics = getStatisticsServiceImpl.getAllStatistics();
        // for (StatisticsInfo allStatistic : allStatistics) {
        // System.out.println(allStatistic);
        // }
        List<ESInfo> es = getESServiceImpl.getES();
        ESInfo esInfo = es.get(0);
        String collectTime = esInfo.getCollectTime();
        System.out.println(collectTime);

        String value = TimeUtil.timeCovert(collectTime);
        System.out.println(value);
        Timestamp timestamp = Timestamp.valueOf(value);
        System.out.println(timestamp);

        System.out.println(value);
        // collectTime.replace("T"," ").replace("Z"," ")

        // DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
        // LocalDateTime ldt = LocalDateTime.parse(collectTime, df);
        // ZoneId currentZone = ZoneId.of("UTC");
        // ZoneId newZone = ZoneId.of("Asia/Shanghai");
        // collectTime = ldt.atZone(currentZone).withZoneSameInstant(newZone).toLocalDateTime().toString();
        // System.out.println(collectTime);
    }

    @Test
    public void testTime() {
        String time = "2022-07-14 00:00:00";
        String UTC = "+8";

        Timestamp zeroUtc = TimeUtil.getZeroUtc(time, UTC);
        System.out.println(zeroUtc);

        // Timestamp timestamp = Timestamp.valueOf(time);
        // Calendar calendar = Calendar.getInstance();
        // calendar.setTimeInMillis(timestamp.getTime());
        //
        // calendar.add(Calendar.HOUR_OF_DAY,-1*Integer.parseInt(UTC));
        // Timestamp t1 = new Timestamp(calendar.getTimeInMillis());
        // System.out.println(t1);

    }

}
