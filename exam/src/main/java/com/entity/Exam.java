package com.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cloudplateform_exam")
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "int(11)")
    private Long id;

    @Column(name = "user_id", columnDefinition = "int(11)")
    private Long userId;

    @Column(name = "paper_id", columnDefinition = "int(11)")
    private Long paperId;

    @Column(name = "re_answers", columnDefinition = "varchar(500)")
    private String reAnswer;

    @Column(name = "state", columnDefinition = "int(1)")
    private String state;

    @Column(name = "result", columnDefinition = "decimal(5,2)")
    private Float result;

    @Column(name = "start_time", columnDefinition = "datetime")
    private Date startTime;

    @Column(name = "end_time", columnDefinition = "datetime")
    private Date endTime;

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

    public Long getPaperId() {
        return paperId;
    }

    public void setPaperId(Long paperId) {
        this.paperId = paperId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Float getResult() {
        return result;
    }

    public void setResult(Float result) {
        this.result = result;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
