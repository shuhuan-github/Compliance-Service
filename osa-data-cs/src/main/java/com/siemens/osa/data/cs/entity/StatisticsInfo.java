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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "statistics")
@TypeDef(name = "string-array", typeClass = StringArrayType.class)
@TypeDef(name = "list-array", typeClass = ListArrayType.class)
@TypeDef(name = "ipv4", typeClass = PostgreSQLInetType.class, defaultForType = Inet.class)
public class StatisticsInfo {

    /** database id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long sid;

    /** machine check item collection time. */
    @Column(name = "check_time")
    private Timestamp timestamp;

    /** machine utc time zone. */
    @Column(name = "utc")
    private String utc;

    /** collection machine ip. */
    @Column(name = "host_ip", columnDefinition = "inet")
    private Inet hostIp;

    /** machine failure check item rule id list. */
    @Type(type = "list-array")
    @Column(name = "failed_list")
    private List<String> failedList;

    /** machine success check item rule id list. */
    @Type(type = "list-array")
    @Column(name = "pass_list")
    private List<String> passList;

}
