package com.siemens.osa.data.cs.repository;

import com.siemens.osa.data.cs.entity.ResultInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface ResultInfoRepository extends JpaRepository<ResultInfo, Long> {

    /**
     * get result by config id.
     *
     * @param id
     *            id
     * @return {@link List}&lt;{@link ResultInfo}&gt;
     */
    List<ResultInfo> findById(Integer id);

    /**
     * get recent result.
     *
     * @return {@link List}&lt;{@link ResultInfo}&gt;
     */
    @Query(value = "select * from result where timestamp = (select max(timestamp) from result)", nativeQuery = true)
    List<ResultInfo> getRecentResult();

    /**
     * get the result by host ip.
     *
     * @param hostIp
     *            host ip
     * @param collectTime
     *            collectionTime
     * @param ruleId
     *            rule id
     * @return {@link List}<{@link ResultInfo}>
     */
    @Query(value = "select * from result where host_ip = inet(:hostIp) "
            + "and rule_id = :ruleId and timestamp = :collectTime", nativeQuery = true)
    List<ResultInfo> getResultByHostIpTimeAndRuleId(@Param("hostIp") String hostIp,
            @Param("collectTime") Timestamp collectTime, @Param("ruleId") String ruleId);

    /**
     * get recent result within time region.
     *
     * @param beginTime
     *            begin time
     * @param endTime
     *            end time
     * @return {@link List}&lt;{@link ResultInfo}&gt;
     */
    @Query(value = "select * from result where timestamp = (select max(timestamp) from result where "
            + "timestamp >= :beginTime and timestamp < :endTime)", nativeQuery = true)
    List<ResultInfo> getRecentResultWithZone(@Param("beginTime") Timestamp beginTime,
            @Param("endTime") Timestamp endTime);

}
