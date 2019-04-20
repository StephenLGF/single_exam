package com.controller;

import com.servie.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class Examation {

    @Autowired
    private ExamService examService = null;

    @GetMapping(value = "/examation")
    public ResponseEntity getQuestionById() {
        Long userId = 1L;
        return new ResponseEntity(examService.getExamsByUserId(userId), HttpStatus.OK);
    }
}