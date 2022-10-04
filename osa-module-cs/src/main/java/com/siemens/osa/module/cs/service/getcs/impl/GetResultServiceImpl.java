package com.siemens.osa.module.cs.service.getcs.impl;

import com.siemens.osa.data.cs.entity.ResultInfo;
import com.siemens.osa.data.cs.module.ResultService;
import com.siemens.osa.module.cs.service.getcs.IGetResultService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class GetResultServiceImpl implements IGetResultService {

    /** the cs module resultService object. */
    private ResultService resultService;

    /**
     * constructor.
     *
     * @param resultServices
     *            resultService object
     */
    public GetResultServiceImpl(final ResultService resultServices) {
        this.resultService = resultServices;
    }

    /**
     * get all results.
     *
     * @return {@link List}&lt;{@link ResultInfo}&gt;
     */
    @Override
    public List<ResultInfo> getResult() {
        return resultService.getAllResult();
    }

    /**
     * get all results by config id.
     *
     * @param id
     *            id
     * @return {@link List}&lt;{@link ResultInfo}&gt;
     */
    @Override
    public List<ResultInfo> getResultById(Integer id) {
        return resultService.getResultById(id);
    }

    /**
     * get recent results.
     *
     * @return {@link List}&lt;{@link ResultInfo}&gt;
     */
    @Override
    public List<ResultInfo> getRecentResult() {
        return resultService.getRecentResult();
    }

    /**
     * get recent result by host ip.
     *
     * @param hostIp
     *            主机ip
     * @param collectTime
     *            收集时间
     * @return {@link List}&lt;{@link ResultInfo}&gt;
     */
    @Override
    public List<ResultInfo> getResultByHostIpTime(String hostIp, Timestamp collectTime, String ruleId) {
        return resultService.getResultByHostIpTime(hostIp, collectTime, ruleId);
    }

    /**
     * get recent result within a time region.
     *
     * @param beginTime
     *            begin time
     * @param endTime
     *            end time
     * @return {@link List}&lt;{@link ResultInfo}&gt;
     */
    @Override
    public List<ResultInfo> getRecentResultWithZone(Timestamp beginTime, Timestamp endTime) {
        return resultService.getRecentResultWithZone(beginTime, endTime);
    }

    /**
     * insert a result data.
     *
     * @param resultInfo
     *            resultInfo
     */
    @Override
    public void insertResult(ResultInfo resultInfo) {
        resultService.addResult(resultInfo);
    }

}
