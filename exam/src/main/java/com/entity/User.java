package com.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cloudplateform_user")
public class User {

    @Id
    @Column(name = "id", columnDefinition = "int(11)")
    private Long id;

    @Column(name = "name", columnDefinition = "varchar(254)")
    private String name;

    @Column(name = "password", columnDefinition = "varchar(254)")
    private String password;

    @Column(name = "type", columnDefinition = "int(1)")
    private Integer type;

    @Column(name = "created_time", columnDefinition = "datetime")
    private Date time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
