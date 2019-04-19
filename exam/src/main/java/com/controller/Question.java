package com.controller;

import com.servie.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class Question {

    @Autowired
    private QuizService quizService = null;

    @GetMapping(value = "/question/{id}")
    public ResponseEntity getQuestionById(@PathVariable Long id) {
        return new ResponseEntity(quizService.getQuizById(id), HttpStatus.OK);
    }
}