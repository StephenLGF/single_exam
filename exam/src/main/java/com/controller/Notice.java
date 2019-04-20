package com.controller;

import com.servie.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class Notice {

    @Autowired
    private NotificationService notificationService = null;

    @GetMapping(value = "/notification")
    public ResponseEntity getAllNotification() {
        Long userId = 1L;
        return new ResponseEntity(notificationService.getNormalByUserId(userId), HttpStatus.OK);
    }
}