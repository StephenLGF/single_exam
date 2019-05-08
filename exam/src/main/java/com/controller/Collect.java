package com.controller;

import com.alibaba.fastjson.JSONObject;
import com.entity.Collection;
import com.entity.News;
import com.servie.CollectionService;
import com.servie.NewsService;
import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("api")
public class Collect {

    @Autowired
    private CollectionService collectionService = null;

    @Autowired
    private NewsService newsService = null;

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
        List<News> newsList = collectionService.getCollectionByUserId(userId);
        return new ResponseEntity<>(newsService.translate(newsList), HttpStatus.OK);
    }

    @PutMapping(value = "/collection/{newsId}")
    public ResponseEntity createWrongQuestion(@PathVariable Long newsId, @RequestBody String json) {
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
        Collection collection = collectionService.addCollection(newsId, userId);
        if (collection != null) {
            return new ResponseEntity<>(collection, HttpStatus.OK);
        }
        return new ResponseEntity(null, HttpStatus.NOT_FOUND);
    }
}