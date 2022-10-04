package com.siemens.osa.module.cs.service.getcs;

import com.siemens.osa.data.cs.entity.ResultInfo;

import java.sql.Timestamp;
import java.util.List;

public interface IGetResultService {

    /**
     * get all result.
     *
     * @return {@link List}&lt;{@link ResultInfo}&gt;
     */
    List<ResultInfo> getResult();

    /**
     * get all results by config id.
     *
     * @param id
     *            id
     * @return {@link List}&lt;{@link ResultInfo}&gt;
     */
    List<ResultInfo> getResultById(Integer id);

    /**
     * get recent results.
     *
     * @return {@link List}&lt;{@link ResultInfo}&gt;
     */
    List<ResultInfo> getRecentResult();

    /**
     * 得到结果通过主机ip get result by host ip and collect time.
     *
     * @param hostIp
     *            host ip
     * @param collectTime
     *            collect time
     * @param ruleId
     *            rule id
     * @return {@link List}<{@link ResultInfo}>
     */
    List<ResultInfo> getResultByHostIpTime(String hostIp, Timestamp collectTime, String ruleId);

    /**
     * get recent result within a time region.
     *
     * @param beginTime
     *            begin time
     * @param endTime
     *            end time
     * @return {@link List}&lt;{@link ResultInfo}&gt;
     */
    List<ResultInfo> getRecentResultWithZone(Timestamp beginTime, Timestamp endTime);

    /**
     * insert a result data.
     *
     * @param resultInfo
     *            ResultInfo object
     */
    void insertResult(ResultInfo resultInfo);

}
