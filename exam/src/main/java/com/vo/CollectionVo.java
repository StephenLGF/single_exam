package com.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CollectionVo {

    private Long id;

    private String question;

    private List<String> selections;

    private List<Integer> answer;

    private Integer type;

    private Date time;

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

    public List<String> getSelections() {
        return selections;
    }

    public void setSelections(List<String> selections) {
        this.selections = selections;
    }

    public List<Integer> getAnswer() {
        return answer;
    }

    public void setAnswer(List<Integer> answer) {
        this.answer = answer;
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

    public void setAnswer(String answer) {
        List<Integer> result = new ArrayList<>();
        String[] answerArray = answer.split(",");
        for (String answerStr : answerArray) {
            result.add(Integer.parseInt(answerStr));
        }
        this.answer = result;
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