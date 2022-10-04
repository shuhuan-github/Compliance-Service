package com.siemens.osa.data.cs.entity;

import com.vladmihalcea.hibernate.type.array.ListArrayType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GenerationType;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
@Table(name = "config")
@TypeDef(name = "list-array", typeClass = ListArrayType.class)
@AllArgsConstructor
@NoArgsConstructor
public class ConfigInfo {

    /** database Id. */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long cid;

    /** configurationFileStorageTime. */
    @Column(name = "timestamp")
    private Timestamp timestamp;

    /** config file id. */
    @Column(name = "id")
    private Integer id;

    /** operating system. */
    @Column(name = "os")
    private String os;

    /** language Version.*/
    @Column(name = "lang")
    private String lang;

    /** rule id. */
    @Column(name = "rule_id")
    private String ruleId;

    /** rules expect data. */
    @Column(name = "data", columnDefinition = "text[]")
    @Type(type = "list-array")
    private List<String> data;

    /** resultType. */
    @Column(name = "type")
    private Integer type;

    /** run command arguments. */
    @Column(name = "param", columnDefinition = "text[]")
    @Type(type = "list-array")
    private List<String> params;

}
