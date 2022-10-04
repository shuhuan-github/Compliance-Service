package com.siemens.osa.module.cs.service.getcs.impl;

import com.siemens.osa.data.cs.config.Inet;
import com.siemens.osa.data.cs.entity.StatisticsInfo;
import com.siemens.osa.data.cs.module.StatisticsService;
import com.siemens.osa.module.cs.service.getcs.IGetStatisticsService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class GetStatisticsServiceImpl implements IGetStatisticsService {

    /** the cs module statisticsService object. */
    private StatisticsService statisticsService;

    /**
     * constructor.
     *
     * @param statisticsServices statisticsService object
     */
    public GetStatisticsServiceImpl(final StatisticsService statisticsServices) {
        this.statisticsService = statisticsServices;
    }

    /**
     * get all statistics.
     *
     * @return {@link List}&lt;{@link StatisticsInfo}&gt;
     */
    @Override
    public List<StatisticsInfo> getAllStatistics() {
        return statisticsService.getAllStatistics();
    }

    /**
     * insert a statistics data.
     *
     * @param timestamp  collect time
     * @param utc        utc
     * @param hostIp     host ip
     * @param failedList failedList
     * @param passList   passList
     */
    @Override
    public void insertStatistics(Timestamp timestamp, String utc, Inet hostIp, List<String> failedList,
            List<String> passList) {
        statisticsService.insertStatistics(timestamp, utc, hostIp, failedList, passList);
    }

    /**
     * insert a statistics list.
     *
     * @param statisticsInfoList statistics list
     */
    @Override
    public void insertAllStatistics(List<StatisticsInfo> statisticsInfoList) {
        for (StatisticsInfo statisticsInfo : statisticsInfoList) {
            statisticsService.insertStatistics(statisticsInfo.getTimestamp(), statisticsInfo.getUtc(),
                    statisticsInfo.getHostIp(), statisticsInfo.getFailedList(), statisticsInfo.getPassList());
        }
    }

    /**
     * insert a statistics map.
     *
     * @param statistics statistics map
     */
    @Override
    public void insertStatisticsMap(Map<Inet, Map<Timestamp, StatisticsInfo>> statistics) {
        List<StatisticsInfo> statisticsInfoList = new LinkedList<>();
        for (Map.Entry<Inet, Map<Timestamp, StatisticsInfo>> entry : statistics.entrySet()) {
            Map<Timestamp, StatisticsInfo> timestampStatisticsInfoMap = entry.getValue();
            for (Map.Entry<Timestamp, StatisticsInfo> mapEntry : timestampStatisticsInfoMap.entrySet()) {
                StatisticsInfo statisticsInfo = mapEntry.getValue();
                statisticsInfoList.add(statisticsInfo);
            }
        }
        insertAllStatistics(statisticsInfoList);
    }

    /**
     * get instant statistics data by host ip.
     *
     * @param hostIp host ip
     * @return {@link StatisticsInfo}
     */
    @Override
    public StatisticsInfo getInstantStatisticsInfoByHostIp(String hostIp) {
        return statisticsService.getInstantStaInfoByHostIp(hostIp);
    }

    /**
     * get instant statistics data by host ip within a time region.
     *
     * @param hostIp    host ip
     * @param beginTime begin time
     * @param endTime   end time
     * @return {@link StatisticsInfo}
     */
    @Override
    public StatisticsInfo getZoneInstantStatisticsInfoByHostIp(String hostIp, Timestamp beginTime, Timestamp endTime) {
        return statisticsService.getZoneInstantStaInfoByHostIp(hostIp, beginTime, endTime);
    }

}
