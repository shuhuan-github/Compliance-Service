package com.siemens.osa.module.cs.service.generatereport.impl;

import com.siemens.osa.data.cs.entity.StatisticsInfo;
import com.siemens.osa.module.cs.service.generatereport.IGenerateReportService;
import com.siemens.osa.module.cs.service.getcs.impl.GetStatisticsServiceImpl;
import com.siemens.osa.module.cs.util.TimeUtil;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

@Service
public class GenerateReportServiceImpl implements IGenerateReportService {

    /** statisticsServiceImpl. */
    private GetStatisticsServiceImpl statisticsServiceImpl;

    /**
     * constructor.
     *
     * @param statisticsServiceImpls statisticsServiceImpl
     */
    public GenerateReportServiceImpl(final GetStatisticsServiceImpl statisticsServiceImpls) {
        this.statisticsServiceImpl = statisticsServiceImpls;
    }

    /**
     * generate default instant reports.
     *
     * @return {@link List}&lt;{@link StatisticsInfo}&gt;
     */
    @Override
    public List<StatisticsInfo> generateDefaultInstantReport() {
        List<String> defaultIp = new LinkedList<>();
        HashSet<String> ipSet = new HashSet<>();
        // 获取数据库中存储的所有ip
        List<StatisticsInfo> allStatistics = statisticsServiceImpl.getAllStatistics();
        for (StatisticsInfo allStatistic : allStatistics) {
            String ip = allStatistic.getHostIp().getAddress();
            if (!ipSet.contains(ip)) {
                ipSet.add(ip);
                defaultIp.add(ip);
            }
        }

        return generateInstantReportWithIpList(defaultIp);
    }

    /**
     * generate instant reports with ip lists.
     *
     * @param ipList ip列表
     * @return {@link List}&lt;{@link StatisticsInfo}&gt;
     */
    @Override
    public List<StatisticsInfo> generateInstantReportWithIpList(List<String> ipList) {
        List<StatisticsInfo> statisticsInfoList = new LinkedList<>();
        for (String ip : ipList) {
            StatisticsInfo statisticsInfo = statisticsServiceImpl.getInstantStatisticsInfoByHostIp(ip);
            if (statisticsInfo != null) {
                statisticsInfoList.add(statisticsInfo);
            }
        }
        return statisticsInfoList;
    }

    /**
     * generate default circle report.
     *
     * @param beginTime begin time
     * @param endTime   end time
     * @param utc       utc
     * @param zone      frequency
     * @param multiple  multiple
     * @return {@link List}&lt;{@link List}&lt;{@link StatisticsInfo}&gt;&gt;
     */
    @Override
    public List<List<StatisticsInfo>> generateDefaultCircleReport(String beginTime, String endTime, String utc,
            String zone, Integer multiple) {
        List<String> defaultIp = new LinkedList<>();
        HashSet<String> ipSet = new HashSet<>();
        // 获取数据库中存储的所有ip
        List<StatisticsInfo> allStatistics = statisticsServiceImpl.getAllStatistics();
        for (StatisticsInfo allStatistic : allStatistics) {
            String ip = allStatistic.getHostIp().getAddress();
            if (!ipSet.contains(ip)) {
                ipSet.add(ip);
                defaultIp.add(ip);
            }
        }

        return generateCircleReportWithIpList(defaultIp, beginTime, endTime, utc, zone, multiple);
    }

    /**
     * generate circle report with ip list.
     *
     * @param ipList    ip list
     * @param beginTime begin time
     * @param endTime   end time
     * @param utc       utc
     * @param zone      frequency
     * @param multiple  multiple
     * @return {@link List}&lt;{@link List}&lt;{@link StatisticsInfo}&gt;&gt;
     */
    @Override
    public List<List<StatisticsInfo>> generateCircleReportWithIpList(List<String> ipList, String beginTime,
            String endTime, String utc, String zone, Integer multiple) {
        // change to zero utc timestamp
        Timestamp beginTimeStamp = TimeUtil.getZeroUtc(beginTime, utc);
        Timestamp endTimeStamp = TimeUtil.getZeroUtc(endTime, utc);
        // change to Calendar
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(beginTimeStamp.getTime());
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTimeInMillis(endTimeStamp.getTime());
        // get filed
        Integer filed = getFiled(zone);

        List<List<StatisticsInfo>> resultReport = new LinkedList<>();
        while (calendar.before(endCalendar)) {
            Timestamp startTime = new Timestamp(calendar.getTimeInMillis());
            calendar.add(filed, multiple);
            Timestamp terminalTime = new Timestamp(calendar.getTimeInMillis());
            if (!calendar.before(endCalendar)) {
                terminalTime = new Timestamp(endCalendar.getTimeInMillis());
            }
            List<StatisticsInfo> statisticsInfos = new LinkedList<>();
            for (String ip : ipList) {
                StatisticsInfo statisticsInfo =
                        statisticsServiceImpl.getZoneInstantStatisticsInfoByHostIp(ip, startTime, terminalTime);
                if (statisticsInfo != null) {
                    statisticsInfos.add(statisticsInfo);
                }
            }
            resultReport.add(statisticsInfos);
        }
        return resultReport;
    }

    /**
     * get frequency filed.
     *
     * @param zone frequency
     * @return {@link Integer}
     */
    public Integer getFiled(String zone) {
        switch (zone) {
        case "year":
            return Calendar.YEAR;
        case "month":
            return Calendar.MONTH;
        case "date":
            return Calendar.DAY_OF_YEAR;
        case "hour":
            return Calendar.HOUR_OF_DAY;
        case "minute":
            return Calendar.MINUTE;
        case "second":
            return Calendar.SECOND;
        default:
            return Calendar.DAY_OF_YEAR;
        }
    }

}
