package com.siemens.osa.data.cs.repository;

import com.siemens.osa.data.cs.entity.StatisticsInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public interface StatisticsInfoRepository extends JpaRepository<StatisticsInfo, Long> {

    /**
     * get instant statistics data by host ip.
     *
     * @param hostIp host ip
     * @return {@link StatisticsInfo}
     */
    @Query(value = "select * from statistics where host_ip = Inet(:hostIp) and "
            + "check_time = (select max(check_time) from statistics where host_ip = Inet(:hostIp))", nativeQuery = true)
    StatisticsInfo getInstantStatisticsInfoByIp(@Param("hostIp") String hostIp);

    /**
     * get instant statistics data by host ip within a time region.
     *
     * @param hostIp    host ip
     * @param beginTime begin time
     * @param endTime   end time
     * @return {@link StatisticsInfo}
     */
    @Query(value = "select * from statistics where host_ip = Inet(:hostIp) and "
            + "check_time = (select max(check_time) from statistics where "
            + "check_time >= :beginTime and check_time < :endTime)", nativeQuery = true)
    StatisticsInfo getZoneInstantStatisticsInfoByIp(@Param("hostIp") String hostIp,
            @Param("beginTime") Timestamp beginTime, @Param("endTime") Timestamp endTime);

}
