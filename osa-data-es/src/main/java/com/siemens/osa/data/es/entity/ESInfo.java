package com.siemens.osa.data.es.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ESInfo {

    /** config id. */
    private Integer id;

    /** machine check item collection time. */
    private String collectTime;

    /** machine utc zone. */
    private String utc;

    /** result list. */
    private List<String> result;

    /** machine ip. */
    private String hostIp;

    /** rule id. */
    private String ruleId;

}
