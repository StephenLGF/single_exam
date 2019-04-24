package com.controller;

import com.alibaba.fastjson.JSONObject;
import com.entity.Collection;
import com.servie.CollectionService;
import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("api")
public class Collect {

    @Autowired
    private CollectionService collectionService = null;

    @GetMapping(value = "/collection")
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
        return new ResponseEntity(collectionService.getCollectionByUserId(userId), HttpStatus.OK);
    }

    @PutMapping(value = "/collection/{questionId}")
    public ResponseEntity createWrongQuestion(@PathVariable Long questionId, @RequestBody String json) {
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
        Collection collection = collectionService.addCollection(questionId, userId);
        if (collection != null) {
            return new ResponseEntity(collection, HttpStatus.OK);
        }
        return new ResponseEntity(null, HttpStatus.NOT_FOUND);
    }
}