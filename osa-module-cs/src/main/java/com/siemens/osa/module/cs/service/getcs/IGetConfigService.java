package com.siemens.osa.module.cs.service.getcs;

import com.siemens.osa.data.cs.entity.ConfigInfo;

import java.util.List;
import java.util.Map;

public interface IGetConfigService {

    /**
     * get all config.
     *
     * @return {@link List}&lt;{@link ConfigInfo}&gt;
     */
    List<ConfigInfo> getConfig();

    /**
     * get all config by config id.
     *
     * @param id id
     * @return {@link List}&lt;{@link ConfigInfo}&gt;
     */
    List<ConfigInfo> getConfigById(Integer id);

    /**
     * get the map of all config  by config id.
     *
     * @param id id
     * @return {@link Map}&lt;{@link String}, {@link ConfigInfo}&gt;
     */
    Map<String, ConfigInfo> getConfigMapById(Integer id);

}
