package com.controller;

import com.entity.Collection;
import com.servie.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class Collect {

    @Autowired
    private CollectionService collectionService = null;

    @GetMapping(value = "/collection")
    public ResponseEntity getQuestionById() {
        Long userId = 1L;
        return new ResponseEntity(collectionService.getCollectionByUserId(userId), HttpStatus.OK);
    }

    @PutMapping(value = "/collection/{questionId}")
    public ResponseEntity createWrongQuestion(@PathVariable Long questionId) {
        Long userId = 1L;
        Collection collection = collectionService.addCollection(questionId, userId);
        if (collection != null) {
            return new ResponseEntity(collection, HttpStatus.OK);
        }
        return new ResponseEntity(null, HttpStatus.NOT_FOUND);
    }
}