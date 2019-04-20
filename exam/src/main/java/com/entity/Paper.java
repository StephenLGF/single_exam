package com.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cloudplateform_paper")
public class Paper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "int(20)")
    private Long id;

    @Column(name = "name", columnDefinition = "varchar(254)")
    private String name;

    @Column(name = "quiz_ids", columnDefinition = "varchar(500)")
    private String questions;

    @Column(name = "answers", columnDefinition = "varchar(500)")
    private String answer;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuestions() {
        return questions;
    }

    public void setQuestions(String questions) {
        this.questions = questions;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
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
