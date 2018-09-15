package com.ethan.stage.dal;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "members_changelog")
public class VersionDO {

    @Id private Long id;

    @Column(name = "version")
    private String version;

    @Column(name = "client_type")
    @JsonProperty("client_type")
    private String clientType;

    @Column(name = "package_name")
    @JsonProperty("package_name")
    private String packageName;
}
