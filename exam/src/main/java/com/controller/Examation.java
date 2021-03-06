package com.controller;

import com.alibaba.fastjson.JSONObject;
import com.servie.ExamService;
import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("api")
public class Examation {

    @Autowired
    private ExamService examService = null;

    @GetMapping(value = "/examation")
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
        return new ResponseEntity(examService.getExamsByUserId(userId), HttpStatus.OK);
    }
}