package com.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExamVo {

    private Long paperId;

    private String name;

    private String state;

    private Float result;

    private Date startTime;

    private Date endTime;

    private Date createTime;

    private Long creator;

    private List<Integer> questions;

    public Long getPaperId() {
        return paperId;
    }

    public void setPaperId(Long paperId) {
        this.paperId = paperId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreator() {
        return creator;
    }

    public void setCreator(Long creator) {
        this.creator = creator;
    }

    public List<Integer> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Integer> questions) {
        this.questions = questions;
    }

    public void setQuestions(String questions) {
        String[] questionsArray = questions.split(",");
        List<Integer> questionList = new ArrayList<>();
        for (String questionStr : questionsArray) {
            questionList.add(Integer.parseInt(questionStr));
        }
        this.questions = questionList;
    }
}
