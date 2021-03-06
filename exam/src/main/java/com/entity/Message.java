package com.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cloudplateform_message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "int(11)")
    private Long id;

    @Column(name = "title", columnDefinition = "varchar(254)")
    private String title;

    @Column(name = "content", columnDefinition = "text")
    private String content;

    @Column(name = "type", columnDefinition = "int(1)")
    private Integer type;

    @Column(name = "creator", columnDefinition = "int(11)")
    private Long creator;

    @Column(name = "created_time", columnDefinition = "datetime")
    private Date time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getCreator() {
        return creator;
    }

    public void setCreator(Long creator) {
        this.creator = creator;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
