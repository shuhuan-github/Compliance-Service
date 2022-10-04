package com.siemens.osa.data.cs.module;

import com.siemens.osa.data.cs.config.Inet;
import com.siemens.osa.data.cs.entity.StatisticsInfo;
import com.siemens.osa.data.cs.repository.StatisticsInfoRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class StatisticsService {

    /** statisticsInfo Repository. */
    private StatisticsInfoRepository statisticsInfoRepository;

    /**
     * constructor.
     *
     * @param repository repository
     */
    public StatisticsService(final StatisticsInfoRepository repository) {
        this.statisticsInfoRepository = repository;
    }

    /**
     * get all statistics data.
     *
     * @return {@link List}&lt;{@link StatisticsInfo}&gt;
     */
    public List<StatisticsInfo> getAllStatistics() {
        return statisticsInfoRepository.findAll();
    }

    /**
     * insert a statistics data.
     *
     * @param timestamp  collectTime
     * @param utc        utc
     * @param hostIp     host ip
     * @param failedList list of failed rule id
     * @param passList   list of passed rule id
     */
    public void insertStatistics(Timestamp timestamp, String utc, Inet hostIp, List<String> failedList,
            List<String> passList) {
        StatisticsInfo statisticsInfo = new StatisticsInfo();
        statisticsInfo.setTimestamp(timestamp);
        statisticsInfo.setUtc(utc);
        statisticsInfo.setHostIp(hostIp);
        statisticsInfo.setFailedList(failedList);
        statisticsInfo.setPassList(passList);

        statisticsInfoRepository.save(statisticsInfo);
    }

    /**
     * get the instant statistics data by host ip.
     *
     * @param hostIp host ip
     * @return {@link StatisticsInfo}
     */
    public StatisticsInfo getInstantStaInfoByHostIp(String hostIp) {
        return statisticsInfoRepository.getInstantStatisticsInfoByIp(hostIp);
    }

    /**
     * get the instant statistics data by host ip within a time zone.
     *
     * @param hostIp    host ip
     * @param beginTime begin time
     * @param endTime   end time
     * @return {@link StatisticsInfo}
     */
    public StatisticsInfo getZoneInstantStaInfoByHostIp(String hostIp, Timestamp beginTime, Timestamp endTime) {
        return statisticsInfoRepository.getZoneInstantStatisticsInfoByIp(hostIp, beginTime, endTime);
    }

}
