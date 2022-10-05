package com.siemens.osa.module.cs.service.comparedata.impl;

import com.siemens.osa.data.cs.config.Inet;
import com.siemens.osa.data.cs.entity.ConfigInfo;
import com.siemens.osa.data.cs.entity.ResultInfo;
import com.siemens.osa.data.cs.entity.StatisticsInfo;
import com.siemens.osa.data.es.entity.ESInfo;
import com.siemens.osa.module.cs.service.comparedata.ICompareDataService;
import com.siemens.osa.module.cs.service.getcs.impl.GetConfigServiceImpl;
import com.siemens.osa.module.cs.service.getcs.impl.GetResultServiceImpl;
import com.siemens.osa.module.cs.service.getcs.impl.GetStatisticsServiceImpl;
import com.siemens.osa.module.cs.service.getes.impl.GetESServiceImpl;
import com.siemens.osa.module.cs.util.StringUtil;
import com.siemens.osa.module.cs.util.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

enum STATUS {

    /**
     * pass enum.
     */
    PASS("pass"),
    /**
     * failed enum.
     */
    FAILED("failed");

    /**
     * value.
     */
    private final String value;

    STATUS(final String values) {
        this.value = values;
    }

    @Override
    public String toString() {
        return value;
    }

}

@Service
public class CompareDataServiceImpl implements ICompareDataService {

    /** GetConfigServiceImpl. */
    private GetConfigServiceImpl getConfigServiceImpl;

    /** GetResultServiceImpl. */
    private GetResultServiceImpl getResultServiceImpl;

    /** GetStatisticsServiceImpl. */
    private GetStatisticsServiceImpl getStatisticsServiceImpl;

    /** GetESServiceImpl. */
    private GetESServiceImpl getESServiceImpl;

    /**
     * constructor.
     *
     * @param getConfigServiceImpls
     *            getConfigServiceImpl
     * @param getResultServiceImpls
     *            getResultServiceImpl
     * @param getStatisticsServiceImpls
     *            getStatisticsServiceImpl
     * @param getESServiceImpls
     *            getESServiceImpl
     */
    public CompareDataServiceImpl(final GetConfigServiceImpl getConfigServiceImpls,
            final GetResultServiceImpl getResultServiceImpls, final GetStatisticsServiceImpl getStatisticsServiceImpls,
            final GetESServiceImpl getESServiceImpls) {
        this.getConfigServiceImpl = getConfigServiceImpls;
        this.getResultServiceImpl = getResultServiceImpls;
        this.getStatisticsServiceImpl = getStatisticsServiceImpls;
        this.getESServiceImpl = getESServiceImpls;
    }

    /**
     * compare data between es and cs.
     *
     * @throws UnknownHostException
     *             Unknown host exception
     */
    @Override
    public void compareData() throws UnknownHostException {
        Logger logger = LoggerFactory.getLogger(getClass());

        // define statistics table
        Map<Inet, Map<Timestamp, StatisticsInfo>> statistics = new HashMap<>();

        // get Es list
        List<ESInfo> esInfos = getESServiceImpl.getES();
        // Determine if the index has data
        if (esInfos.isEmpty()) {
            logger.error("The index is empty");
            return;
        }
        // Get the configuration file version ID in the ES result
        Integer id = esInfos.get(0).getId();
        // Get the corresponding configuration file according to the configuration file ID
        Map<String, ConfigInfo> configInfoMap = getConfigServiceImpl.getConfigMapById(id);
        // Traverse the Es check result list
        for (ESInfo esInfo : esInfos) {
            STATUS status;
            String hostAddress = InetAddress.getLocalHost().getHostAddress();
            // If the configuration file id in ES is different, you can get the corresponding configuration file
            if (!esInfo.getId().equals(id)) {
                id = esInfo.getId();
                configInfoMap = getConfigServiceImpl.getConfigMapById(id);
            }
            // Obtain the corresponding config configuration information according to the result rule ID
            ConfigInfo configInfo = configInfoMap.get(esInfo.getRuleId());
            // Get the type and data of the check item
            Integer type = configInfo.getType();
            List<String> data = configInfo.getData();
            // Get the result of the ES corresponding rule ID check item
            List<String> result = esInfo.getResult();
            // set statistics
            // Determine whether the machine ip exists in the statistics
            Inet hostIp = new Inet(esInfo.getHostIp());
            if (!statistics.containsKey(hostIp)) {
                Map<Timestamp, StatisticsInfo> timestampStatisticsInfoMap = new HashMap<>();
                statistics.put(hostIp, timestampStatisticsInfoMap);
            }
            Map<Timestamp, StatisticsInfo> timestampStatisticsInfoMap = statistics.get(hostIp);
            // Determine whether the collection time exists in the machine ip in the statistics
            String collectTime = TimeUtil.timeCovert(esInfo.getCollectTime());
            logger.info(esInfo.getCollectTime(), "=====>>", collectTime);
            Timestamp timestamp = Timestamp.valueOf(collectTime);
            if (!timestampStatisticsInfoMap.containsKey(timestamp)) {
                StatisticsInfo statisticsInfo = new StatisticsInfo();
                statisticsInfo.setPassList(new LinkedList<>());
                statisticsInfo.setFailedList(new LinkedList<>());
                statisticsInfo.setTimestamp(timestamp);
                statisticsInfo.setHostIp(hostIp);
                statisticsInfo.setUtc(esInfo.getUtc());
                timestampStatisticsInfoMap.put(timestamp, statisticsInfo);
            }
            StatisticsInfo statisticsInfo = timestampStatisticsInfoMap.get(timestamp);
            // determine whether the result type is "access"
            if (type == 1) {
                System.out.println(esInfo.getRuleId());
                if (esInfo.getRuleId().equals("BL999_2876"))
                    status = accessResultProcessing(result, data, logger);
                status = accessResultProcessing(result, data, logger);

                getResultServiceImpl.insertResult(new ResultInfo(1, timestamp, esInfo.getUtc(), id, configInfo.getOs(),
                        configInfo.getLang(), new Inet(hostAddress), hostIp, esInfo.getRuleId(), configInfo.getData(),
                        esInfo.getResult(), status.toString()));
            } else if (type == 2) {
                System.out.println(esInfo.getRuleId());
                if (esInfo.getRuleId().equals("BL999_3714"))
                    status = multiFileProcessing(result, data, logger);
                status = multiFileProcessing(result, data, logger);
                List<String> results = esInfo.getResult().isEmpty() ? null : esInfo.getResult();
                getResultServiceImpl.insertResult(new ResultInfo(1, timestamp, esInfo.getUtc(), id, configInfo.getOs(),
                        configInfo.getLang(), new Inet(hostAddress), hostIp, esInfo.getRuleId(), configInfo.getData(),
                        results, status.toString()));
            } else if (type == 3) {
                System.out.println(esInfo.getRuleId());
                if (esInfo.getRuleId().equals("BL999_1429"))
                    status = smallerDataProcessing(result, data, logger);
                status = multiFileProcessing(result, data, logger);
                List<String> results = esInfo.getResult().isEmpty() ? null : esInfo.getResult();
                getResultServiceImpl.insertResult(new ResultInfo(1, timestamp, esInfo.getUtc(), id, configInfo.getOs(),
                        configInfo.getLang(), new Inet(hostAddress), hostIp, esInfo.getRuleId(), configInfo.getData(),
                        results, status.toString()));
            } else if (type == 4) {
                System.out.println(esInfo.getRuleId());
                if (esInfo.getRuleId().equals("BL999_9200"))
                    status = largerDataProcessing(result, data, logger);
                status = multiFileProcessing(result, data, logger);
                List<String> results = esInfo.getResult().isEmpty() ? null : esInfo.getResult();
                getResultServiceImpl.insertResult(new ResultInfo(1, timestamp, esInfo.getUtc(), id, configInfo.getOs(),
                        configInfo.getLang(), new Inet(hostAddress), hostIp, esInfo.getRuleId(), configInfo.getData(),
                        results, status.toString()));
            } else {
                System.out.println(esInfo.getRuleId());
                if (esInfo.getRuleId().equals("BL999_3171"))
                    status = resultProcessing(result, data, logger);
                status = resultProcessing(result, data, logger);
                List<String> results = esInfo.getResult().isEmpty() ? null : esInfo.getResult();
                getResultServiceImpl.insertResult(new ResultInfo(1, timestamp, esInfo.getUtc(), id, configInfo.getOs(),
                        configInfo.getLang(), new Inet(hostAddress), hostIp, esInfo.getRuleId(), configInfo.getData(),
                        results, status.toString()));
            }
            addList(status, statisticsInfo, esInfo.getRuleId());
        }
        getStatisticsServiceImpl.insertStatisticsMap(statistics);
    }

    /**
     * addList.
     *
     * @param status
     *            state
     * @param statisticsInfo
     *            statistics
     * @param ruleId
     *            ruleId
     */
    public void addList(STATUS status, StatisticsInfo statisticsInfo, String ruleId) {
        if (status.toString().equals("pass")) {
            statisticsInfo.getPassList().add(ruleId);
        } else if (status.toString().equals("failed")) {
            statisticsInfo.getFailedList().add(ruleId);
        }
    }

    /**
     * process access result from linux.
     *
     * @param result
     *            results
     * @param data
     *            data
     * @param logger
     *            logger
     * @return {@link STATUS}
     */
    public STATUS accessResultProcessing(List<String> result, List<String> data, Logger logger) {
        int index = 0;
        for (String re : result) {
            if (re.equals(""))
                return STATUS.FAILED;
            List<String> strings = StringUtil.stringFliter(re);
            for (int i = 0; i < strings.size(); i++, index++) {
                if (!strings.get(i).trim().equals(data.get(index))) {
                    String trim = result.get(i).trim();
                    logger.info("actual data:{}", trim);
                    logger.info("expected data:{}", data.get(index));
                    return STATUS.FAILED;
                }
            }
        }
        return STATUS.PASS;
    }

    /**
     * process result for the other common result.
     *
     * @param result
     *            result
     * @param data
     *            data
     * @param logger
     *            logger
     * @return {@link STATUS}
     */
    public STATUS resultProcessing(List<String> result, List<String> data, Logger logger) {
        if ((result.isEmpty() && data == null) || (!result.isEmpty() && data != null)) {
            if (data != null) {
                for (int i = 0; i < result.size(); i++) {
                    result.set(i, result.get(i).replace("\"", ""));
                    if (!result.get(i).trim().equals(data.get(i))) {
                        String trim = result.get(i).trim();
                        logger.info("actual data:{}", trim);
                        logger.info("expected data:{}", data.get(i));
                        return STATUS.FAILED;
                    }
                }
            }
            return STATUS.PASS;
        }
        return STATUS.FAILED;
    }

    public STATUS multiFileProcessing(List<String> result, List<String> data, Logger logger) {
        STATUS status = STATUS.FAILED;
        for (int i = 0; i < result.size(); i++) {
            status = STATUS.FAILED;
            String dataStr = data.get(i).replace(" ", "");
            String results = result.get(i);
            if (data.get(i).equals("")) {
                if (!data.get(i).equals(results))
                    return STATUS.FAILED;
            } else {
                String[] split = results.split("\n");
                for (String res : split) {
                    String re = res.replace(" ", "");
                    if (!re.contains("#") && re.contains(dataStr)) {
                        status = STATUS.PASS;
                        break;
                    }
                }
                if (status == STATUS.FAILED) {
                    return status;
                }
            }
        }
        return status;
    }

    public STATUS smallerDataProcessing(List<String> result, List<String> data, Logger logger) {
        for (int i = 0; i < result.size(); i++) {
            String[] split = result.get(i).split("\n");
            for (String res : split) {
                int flag = 1;
                if (res.contains("-"))
                    flag = -1;
                String re = res.replaceAll("\\D", "");
                if (!re.equals("")) {
                    int results = Integer.parseInt(re) * flag;
                    int dataNum = Integer.parseInt(data.get(i));
                    if (results > dataNum)
                        return STATUS.FAILED;
                } else {
                    return STATUS.FAILED;
                }
            }
        }
        return STATUS.PASS;
    }

    public STATUS largerDataProcessing(List<String> result, List<String> data, Logger logger) {
        for (int i = 0; i < result.size(); i++) {
            String[] split = result.get(i).split("\n");
            for (String res : split) {
                int flag = 1;
                if (res.contains("-"))
                    flag = -1;
                String re = res.replaceAll("\\D", "");
                if (!re.equals("")) {
                    int results = Integer.parseInt(re) * flag;
                    int dataNum = Integer.parseInt(data.get(i));
                    if (results < dataNum)
                        return STATUS.FAILED;
                } else {
                    return STATUS.FAILED;
                }
            }
        }
        return STATUS.PASS;
    }

}
