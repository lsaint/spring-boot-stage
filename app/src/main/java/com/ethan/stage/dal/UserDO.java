package com.ethan.stage.dal;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "members_user")
public class UserDO {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    @JsonProperty("username")
    private String userName;

    @Column(name = "uid")
    private String uid;

    @Column(name = "phone")
    private String phone;

    @Column(name = "sex")
    private String sex;
}
