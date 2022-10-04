package com.siemens.osa.data.cs.module;

import com.siemens.osa.data.cs.entity.ConfigInfo;
import com.siemens.osa.data.cs.repository.ConfigInfoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ConfigService {

    /** configInfo repository. */
    private ConfigInfoRepository configInfoRepository;

    /**
     * constructor.
     *
     * @param infoRepository configInfo repository
     */
    public ConfigService(final ConfigInfoRepository infoRepository) {
        this.configInfoRepository = infoRepository;
    }

    /**
     * get all configurations.
     *
     * @return {@link List}&lt;{@link ConfigInfo}&gt;
     */
    public List<ConfigInfo> getAllConfig() {
        return configInfoRepository.findAll();
    }

    /**
     * get configuration by config Id.
     *
     * @param id id
     * @return {@link List}&lt;{@link ConfigInfo}&gt;
     */
    public List<ConfigInfo> getConfigById(Integer id) {
        return configInfoRepository.findById(id);
    }

    /**
     * get the map of the config list by config id.
     *
     * @param id id
     * @return {@link Map}&lt;{@link String}, {@link ConfigInfo}&gt;
     */
    public Map<String, ConfigInfo> getConfigMapById(Integer id) {
        List<ConfigInfo> configInfoList = configInfoRepository.findById(id);
        return configInfoList.stream().collect(Collectors.toMap(ConfigInfo::getRuleId, v -> v));
    }

    /**
     * get configuration data by id and rule id.
     *
     * @param id     id
     * @param ruleId 规则id
     * @return {@link List}&lt;{@link String}&gt;
     */
    public List<String> getConfigDataListByIdAndRuleId(Integer id, String ruleId) {
        List<ConfigInfo> configInfoList = configInfoRepository.findByIdAndRuleId(id, ruleId);
        List<String> dataList = new ArrayList<>();
        for (ConfigInfo configInfo : configInfoList) {
            String data = String.join(",", configInfo.getData());
            dataList.add(data);
        }
        return dataList;
    }

}
