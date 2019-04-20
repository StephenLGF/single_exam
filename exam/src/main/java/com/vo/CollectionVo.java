package com.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CollectionVo {

    private Long questionId;

    private String question;

    private List<String> selections;

    private Integer type;

    private Date time;

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getSelections() {
        return selections;
    }

    public void setSelections(List<String> selections) {
        this.selections = selections;
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

    public void setSelections(String selections) {
        List<String> result = new ArrayList<>();
        String[] selectionsArray = selections.split(",");
        for (String selectionsStr : selectionsArray) {
            result.add(selectionsStr);
        }
        this.selections = result;
    }
}