package com.controller;

import com.servie.WrongQuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class WrongQuestion {

    @Autowired
    private WrongQuizService wrongQuizService = null;

    @GetMapping(value = "/wrongquestion")
    public ResponseEntity getQuestionById() {
        Long userId = 1L;
        return new ResponseEntity(wrongQuizService.getWrongQuizByUserId(userId), HttpStatus.OK);
    }
}