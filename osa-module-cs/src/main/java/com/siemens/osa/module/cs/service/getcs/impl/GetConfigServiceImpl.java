package com.siemens.osa.module.cs.service.getcs.impl;

import com.siemens.osa.data.cs.entity.ConfigInfo;
import com.siemens.osa.data.cs.module.ConfigService;
import com.siemens.osa.module.cs.service.getcs.IGetConfigService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GetConfigServiceImpl implements IGetConfigService {

    /** the cs module configService object.*/
    private ConfigService configService;

    /**
     * constructor.
     *
     * @param configServices configService object
     */
    public GetConfigServiceImpl(final ConfigService configServices) {
        this.configService = configServices;
    }

    /**
     * get all config.
     *
     * @return {@link List}&lt;{@link ConfigInfo}&gt;
     */
    @Override
    public List<ConfigInfo> getConfig() {
        return configService.getAllConfig();
    }

    /**
     * get all config by config id.
     *
     * @param id id
     * @return {@link List}&lt;{@link ConfigInfo}&gt;
     */
    @Override
    public List<ConfigInfo> getConfigById(Integer id) {
        return configService.getConfigById(id);
    }

    /**
     * get the map of the config by config id.
     *
     * @param id id
     * @return {@link Map}&lt;{@link String}, {@link ConfigInfo}&gt;
     */
    @Override
    public Map<String, ConfigInfo> getConfigMapById(Integer id) {
        return configService.getConfigMapById(id);
    }

}
