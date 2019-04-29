package com.controller;

import com.servie.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}