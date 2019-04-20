package com.entity;

import javax.persistence.*;

@Entity
@Table(name = "cloudplateform_quiz")
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "int(20)")
    private Long id;

    @Column(name = "question", columnDefinition = "varchar(254)")
    private String question;

    @Column(name = "selection", columnDefinition = "varchar(254)")
    private String selection;

    @Column(name = "answer", columnDefinition = "varchar(254)")
    private String answer;

    @Column(name = "type", columnDefinition = "int(1)")
    private Integer type;

    @Column(name = "creator", columnDefinition = "int(11)")
    private Integer creator;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getSelection() {
        return selection;
    }

    public void setSelection(String selection) {
        this.selection = selection;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }
}
