package com.siemens.osa.module.cs.service.getcs;

import com.siemens.osa.data.cs.config.Inet;
import com.siemens.osa.data.cs.entity.StatisticsInfo;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Repository
public interface IGetStatisticsService {

    /**
     * get all statistics data.
     *
     * @return {@link List}<{@link StatisticsInfo}>
     */
    List<StatisticsInfo> getAllStatistics();

    /**
     * insert a statistics data.
     *
     * @param timestamp  collect time
     * @param utc        utc
     * @param hostIp     host ip
     * @param failedList failed list
     * @param passList   pass list
     */
    void insertStatistics(Timestamp timestamp, String utc, Inet hostIp, List<String> failedList, List<String> passList);

    /**
     * insert all statistics.
     *
     * @param statisticsInfoList statistics list
     */
    void insertAllStatistics(List<StatisticsInfo> statisticsInfoList);

    /**
     * insert a statistics map.
     *
     * @param statistics statistics
     */
    void insertStatisticsMap(Map<Inet, Map<Timestamp, StatisticsInfo>> statistics);

    /**
     * get instant statistics data by ip.
     *
     * @param hostIp host ip
     * @return {@link StatisticsInfo}
     */
    StatisticsInfo getInstantStatisticsInfoByHostIp(String hostIp);

    /**
     * get instant statistics data by ip within a time region.
     *
     * @param hostIp    host ip
     * @param beginTime begin time
     * @param endTime   end time
     * @return {@link StatisticsInfo}
     */
    StatisticsInfo getZoneInstantStatisticsInfoByHostIp(String hostIp, Timestamp beginTime, Timestamp endTime);

}
