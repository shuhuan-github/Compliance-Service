package com.siemens.osa.module.cs.service.generatereport;

import com.siemens.osa.data.cs.entity.StatisticsInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IGenerateReportService {

    /**
     * generate default instant reports.
     *
     * @return {@link List}&lt;{@link StatisticsInfo}&gt;
     */
    List<StatisticsInfo> generateDefaultInstantReport();

    /**
     * generate instant reports with ip lists.
     *
     * @param ipList ip list
     * @return {@link List}&lt;{@link StatisticsInfo}&gt;
     */
    List<StatisticsInfo> generateInstantReportWithIpList(List<String> ipList);

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
    List<List<StatisticsInfo>> generateDefaultCircleReport(String beginTime, String endTime, String utc, String zone,
            Integer multiple);

    /**
     * generate circle report with ip list.
     *
     * @param ipList    ip list
     * @param beginTime begin time
     * @param utc       utc
     * @param endTime   end time
     * @param zone      frequency
     * @param multiple  multiple
     * @return {@link List}&lt;{@link List}&lt;{@link StatisticsInfo}&gt;&gt;
     */
    List<List<StatisticsInfo>> generateCircleReportWithIpList(List<String> ipList, String beginTime, String endTime,
                                                              String utc, String zone, Integer multiple);

}
