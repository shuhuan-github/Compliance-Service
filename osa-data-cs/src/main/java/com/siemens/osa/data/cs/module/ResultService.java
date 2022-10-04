package com.siemens.osa.data.cs.module;

import com.siemens.osa.data.cs.entity.ResultInfo;
import com.siemens.osa.data.cs.repository.ResultInfoRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class ResultService {

    /** resultInfo repository. */
    private ResultInfoRepository resultInfoRepository;

    /**
     * constructor.
     *
     * @param repository
     *            repository
     */
    public ResultService(final ResultInfoRepository repository) {
        this.resultInfoRepository = repository;
    }

    /**
     * get all results.
     *
     * @return {@link List}&lt;{@link ResultInfo}&gt;
     */
    public List<ResultInfo> getAllResult() {
        return resultInfoRepository.findAll();
    }

    /**
     * get result by config id.
     *
     * @param id
     *            id
     * @return {@link List}&lt;{@link ResultInfo}&gt;
     */
    public List<ResultInfo> getResultById(Integer id) {
        return resultInfoRepository.findById(id);
    }

    /**
     * get most recent results.
     *
     * @return {@link List}&lt;{@link ResultInfo}&gt;
     */
    public List<ResultInfo> getRecentResult() {
        return resultInfoRepository.getRecentResult();
    }

    /**
     * get the result by host ip and collectTime.
     *
     * @param hostIp
     *            host ip
     * @param collectTime
     *            collectionTime
     * @param ruleId
     *            rule id
     * @return {@link List}&lt;{@link ResultInfo}&gt;
     */
    public List<ResultInfo> getResultByHostIpTime(String hostIp, Timestamp collectTime, String ruleId) {
        return resultInfoRepository.getResultByHostIpTimeAndRuleId(hostIp, collectTime, ruleId);
    }

    /**
     * get the latest results within a region time.
     *
     * @param beginTime
     *            begin time
     * @param endTime
     *            end time
     * @return {@link List}&lt;{@link ResultInfo}&gt;
     */
    public List<ResultInfo> getRecentResultWithZone(Timestamp beginTime, Timestamp endTime) {
        return resultInfoRepository.getRecentResultWithZone(beginTime, endTime);
    }

    /**
     * insert a result.
     *
     * @param result
     *            a resultInfo object
     */
    public void addResult(ResultInfo result) {
        ResultInfo resultInfo = new ResultInfo();

        resultInfo.setTimestamp(result.getTimestamp());
        resultInfo.setUtc(result.getUtc());
        resultInfo.setId(result.getId());
        resultInfo.setOs(result.getOs());
        resultInfo.setLang(result.getLang());
        resultInfo.setHostIP(result.getHostIP());
        resultInfo.setServerIP(result.getServerIP());
        resultInfo.setRuleId(result.getRuleId());
        resultInfo.setActual(result.getActual());
        resultInfo.setExpected(result.getExpected());
        resultInfo.setStatus(result.getStatus());

        resultInfoRepository.save(resultInfo);
    }

}
