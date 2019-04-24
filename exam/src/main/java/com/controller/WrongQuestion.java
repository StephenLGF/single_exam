package com.controller;

import com.alibaba.fastjson.JSONObject;
import com.entity.WrongQuiz;
import com.servie.WrongQuizService;
import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("api")
public class WrongQuestion {

    @Autowired
    private WrongQuizService wrongQuizService = null;

    @GetMapping(value = "/wrongquestion")
    public ResponseEntity getQuestionById(@RequestParam(value = "token") String token) {
        if (token.isEmpty()) {
            return new ResponseEntity(null, HttpStatus.UNAUTHORIZED);
        }
        Hashids hashids = new Hashids("this is my salt");
        long[] decodeLong = hashids.decode(token);
        Long userId = decodeLong[0];
        Long tokenTime = decodeLong[1];
        Long now = Instant.now().toEpochMilli();
        if (now - tokenTime > 1000 * 60 * 60 * 5) {
            return new ResponseEntity(null, HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity(wrongQuizService.getWrongQuizByUserId(userId), HttpStatus.OK);
    }

    @PutMapping(value = "/wrongquestion/{questionId}")
    public ResponseEntity createWrongQuestion(@PathVariable Long questionId, @RequestBody String json) {
        if (json.isEmpty()) {
            return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
        }
        JSONObject body = JSONObject.parseObject(json);
        String token = body.getString("token");
        if (token == null || token.isEmpty()) {
            return new ResponseEntity(null, HttpStatus.UNAUTHORIZED);
        }
        Hashids hashids = new Hashids("this is my salt");
        long[] decodeLong = hashids.decode(token);
        Long userId = decodeLong[0];
        Long tokenTime = decodeLong[1];
        Long now = Instant.now().toEpochMilli();
        if (now - tokenTime > 1000 * 60 * 60 * 5) {
            return new ResponseEntity(null, HttpStatus.UNAUTHORIZED);
        }
        String wAnswer = body.getString("wAnswer");
        WrongQuiz wrongQuiz = wrongQuizService.addWrongQuiz(questionId, userId, wAnswer);
        if (wrongQuiz != null) {
            return new ResponseEntity(wrongQuiz, HttpStatus.OK);
        }
        return new ResponseEntity(null, HttpStatus.NOT_FOUND);
    }
}