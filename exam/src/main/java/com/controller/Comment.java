package com.controller;

import com.alibaba.fastjson.JSONObject;
import com.servie.CommentService;
import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("api")
public class Comment {

    @Autowired
    private CommentService commentService = null;

    @GetMapping(value = "/comment/{newsId}")
    public ResponseEntity getNewsById(@PathVariable Long newsId) {
        return new ResponseEntity(commentService.getCommentByNewsId(newsId), HttpStatus.OK);
    }


    @PutMapping(value = "/comment/news/{newsId}")
    public ResponseEntity createWrongQuestion(@PathVariable Long newsId, @RequestBody String json) {
        JSONObject body = JSONObject.parseObject(json);
        String token = body.getString("token");
        String content = body.getString("content");
        if (token == null || token.isEmpty() || content == null || content.isEmpty()) {
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
        com.entity.Comment comment = commentService.AddComment(newsId, userId, content);
        if (comment != null) {
            return new ResponseEntity(comment, HttpStatus.OK);
        }
        return new ResponseEntity(null, HttpStatus.NOT_FOUND);
    }
}