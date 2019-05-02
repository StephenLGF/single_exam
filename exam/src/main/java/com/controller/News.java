package com.controller;

import com.alibaba.fastjson.JSONObject;
import com.entity.Collection;
import com.servie.NewsService;
import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("api")
public class News {

    @Autowired
    private NewsService newsService = null;

    @GetMapping(value = "/news/all")
    public ResponseEntity getAll() {
        return new ResponseEntity(newsService.getAllNews(), HttpStatus.OK);
    }

    @GetMapping(value = "/news/words")
    public ResponseEntity getWords() {
        return new ResponseEntity(newsService.getAllWords(), HttpStatus.OK);
    }

    @GetMapping(value = "/news/pics")
    public ResponseEntity getPics() {
        return new ResponseEntity(newsService.getAllPics(), HttpStatus.OK);
    }

    @GetMapping(value = "/news/videos")
    public ResponseEntity getVideos() {
        return new ResponseEntity(newsService.getAllVideos(), HttpStatus.OK);
    }


    @GetMapping(value = "/news/{newsId}")
    public ResponseEntity getNewsById(@PathVariable Long newsId) {
        return new ResponseEntity(newsService.getNewsById(newsId), HttpStatus.OK);
    }

}