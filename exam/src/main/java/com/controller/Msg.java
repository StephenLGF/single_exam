package com.controller;

import com.servie.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class Msg {

    @Autowired
    private MessageService messageService = null;

    @GetMapping(value = "/message/{id}")
    public ResponseEntity getmessageById(@PathVariable Long id) {
        return new ResponseEntity(messageService.getMessageInfo(id), HttpStatus.OK);
    }
}