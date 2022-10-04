package com.siemens.osa.module.cs;

import com.siemens.osa.data.cs.entity.StatisticsInfo;
import com.siemens.osa.module.cs.service.generatereport.impl.GenerateReportServiceImpl;
import com.siemens.osa.module.cs.util.IpUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.UnknownHostException;
import java.util.List;

@SpringBootTest
public class GenerateReportTest {
    @Autowired
    private GenerateReportServiceImpl generateReportServiceImpl;

    @Value("${circle.beginTime}")
    String beginTime;

    @Value("${circle.endTime}")
    String endTime;

    @Value("${circle.zone}")
    String zone;

    @Value("${circle.multiple}")
    Integer multiple;

    @Value("${ip.list}")
    String ipStrings;

    @Value("${circle.utc}")
    String utc;

    @Test
    public void testIp() throws UnknownHostException {
        System.out.println(ipStrings);
//        List<String> strings = IpUtil.IpSeparate(ipList);
        List<String> strings = IpUtil.ipStrToIpList(ipStrings);
        for (String string : strings) {
            System.out.println(string);
        }
    }

    @Test
    public void testGetInstantStatisticsByIp() throws UnknownHostException {
        List<String> ipList = IpUtil.ipStrToIpList(ipStrings);
        List<StatisticsInfo> statisticsInfos = generateReportServiceImpl.generateInstantReportWithIpList(ipList);
        for (StatisticsInfo statisticsInfo : statisticsInfos) {
            System.out.println(statisticsInfo.getHostIp().getAddress());
            System.out.println(statisticsInfo);
        }
    }

    @Test
    public void testGenerateDefaultInstantStatistics(){
        List<StatisticsInfo> statisticsInfos = generateReportServiceImpl.generateDefaultInstantReport();
        for (StatisticsInfo statisticsInfo : statisticsInfos) {
            System.out.println(statisticsInfo.getHostIp().getAddress());
            System.out.println(statisticsInfo);
        }
    }

    @Test
    public void testGenerateCircleStatistics() throws UnknownHostException {
        List<String> ipList = IpUtil.ipStrToIpList(ipStrings);
        List<List<StatisticsInfo>> reportWithIpList = generateReportServiceImpl.generateCircleReportWithIpList(ipList, beginTime, endTime, utc,zone, multiple);
        for (List<StatisticsInfo> statisticsInfos : reportWithIpList) {
            System.out.println("--------------------------------------------");
            for (StatisticsInfo statisticsInfo : statisticsInfos) {
                System.out.println(statisticsInfo.getHostIp().getAddress());
                System.out.println(statisticsInfo);
            }
        }
    }

    @Test
    public void testGenerateDefaultCircleStatistics() throws UnknownHostException {
        List<List<StatisticsInfo>> reportWithIpList = generateReportServiceImpl.generateDefaultCircleReport(beginTime, endTime, utc,zone, multiple);
        for (List<StatisticsInfo> statisticsInfos : reportWithIpList) {
            System.out.println("--------------------------------------------");
            for (StatisticsInfo statisticsInfo : statisticsInfos) {
                System.out.println(statisticsInfo.getHostIp().getAddress());
                System.out.println(statisticsInfo);
            }
        }
    }
}
