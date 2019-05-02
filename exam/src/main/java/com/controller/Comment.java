package com.controller;

import com.servie.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class Comment {

    @Autowired
    private CommentService commentService = null;

    @GetMapping(value = "/comment/{newsId}")
    public ResponseEntity getNewsById(@PathVariable Long newsId) {
        return new ResponseEntity(commentService.getCommentByNewsId(newsId), HttpStatus.OK);
    }
}