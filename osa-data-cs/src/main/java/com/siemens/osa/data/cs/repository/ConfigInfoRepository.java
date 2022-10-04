package com.siemens.osa.data.cs.repository;

import com.siemens.osa.data.cs.entity.ConfigInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConfigInfoRepository extends JpaRepository<ConfigInfo, Long> {

    /**
     * get config by config id.
     *
     * @param id id
     * @return {@link List}&lt;{@link ConfigInfo}&gt;
     */
    List<ConfigInfo> findById(Integer id);

    /**
     * get config by config id and rule id.
     *
     * @param id     id
     * @param ruleId rule id
     * @return {@link List}&lt;{@link ConfigInfo}&gt;
     */
    List<ConfigInfo> findByIdAndRuleId(Integer id, String ruleId);

}
