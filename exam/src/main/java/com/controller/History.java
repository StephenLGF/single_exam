package com.controller;

import com.alibaba.fastjson.JSONObject;
import com.entity.News;
import com.entity.VisitList;
import com.servie.NewsService;
import com.servie.VisitListService;
import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("api")
public class History {

    @Autowired
    private VisitListService visitListService = null;

    @Autowired
    private NewsService newsService = null;

    @GetMapping(value = "/history")
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
        List<News> newsList = visitListService.getVisitListByUserId(userId);
        return new ResponseEntity(newsService.translate(newsList), HttpStatus.OK);
    }

    @PutMapping(value = "/history/news/{newsId}")
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
        VisitList visitList = visitListService.addVisitHistory(newsId, userId);
        if (visitList != null) {
            return new ResponseEntity(visitList, HttpStatus.OK);
        }
        return new ResponseEntity(null, HttpStatus.NOT_FOUND);
    }
}