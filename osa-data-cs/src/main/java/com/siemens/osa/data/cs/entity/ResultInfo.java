package com.siemens.osa.data.cs.entity;

import com.siemens.osa.data.cs.config.Inet;
import com.siemens.osa.data.cs.config.PostgreSQLInetType;
import com.vladmihalcea.hibernate.type.array.ListArrayType;
import com.vladmihalcea.hibernate.type.array.StringArrayType;
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

@Entity(name = "Event")
@Data
@Table(name = "result")
@TypeDef(name = "string-array", typeClass = StringArrayType.class)
@TypeDef(name = "list-array", typeClass = ListArrayType.class)
@TypeDef(name = "ipv4", typeClass = PostgreSQLInetType.class, defaultForType = Inet.class)
@NoArgsConstructor
@AllArgsConstructor
public class ResultInfo {

    /** database id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long rid;

    /** machine check item collection time. */
    @Column(name = "timestamp")
    private Timestamp timestamp;

    /** machine utc time zone. */
    @Column(name = "utc")
    private String utc;

    /** config file id. */
    @Column(name = "id")
    private Integer id;

    /** operating System. */
    @Column(name = "os")
    private String os;

    /** language Version. */
    @Column(name = "lang")
    private String lang;

    /** server ip. */
    @Column(name = "server_ip", columnDefinition = "inet")
    private Inet serverIP;

    /** collection machine ip. */
    @Column(name = "host_ip", columnDefinition = "inet")
    private Inet hostIP;

    /** rule id. */
    @Column(name = "rule_id")
    private String ruleId;

    /** expected result value. */
    @Column(name = "expected")
    @Type(type = "list-array")
    private List<String> expected;

    /** actual result value. */
    @Column(name = "actual")
    @Type(type = "list-array")
    private List<String> actual;

    /** comparison result status. */
    @Column(name = "status")
    private String status;

}
