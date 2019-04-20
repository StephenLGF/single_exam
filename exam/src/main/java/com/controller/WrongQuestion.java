package com.controller;

import com.entity.WrongQuiz;
import com.servie.WrongQuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @PutMapping(value = "/wrongquestion/{questionId}")
    public ResponseEntity createWrongQuestion(@PathVariable Long questionId, @RequestBody String wAnswer) {
        Long userId = 1L;
        if (wAnswer.isEmpty()) {
            return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
        }
        WrongQuiz wrongQuiz = wrongQuizService.addWrongQuiz(questionId, userId, wAnswer);
        if (wrongQuiz != null) {
            return new ResponseEntity(wrongQuiz, HttpStatus.OK);
        }
        return new ResponseEntity(null, HttpStatus.NOT_FOUND);
    }
}