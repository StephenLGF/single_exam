package com.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cloudplateform_collection")
public class Collection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "int(11)")
    private Long id;

    @Column(name = "user_id", columnDefinition = "int(11)")
    private Long userId;

    @Column(name = "quiz_id", columnDefinition = "int(11)")
    private Long questionId;

    @Column(name = "created_time", columnDefinition = "datetime")
    private Date time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
