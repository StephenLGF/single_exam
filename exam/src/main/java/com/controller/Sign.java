package com.controller;

import com.alibaba.fastjson.JSONObject;
import com.entity.User;
import com.servie.UserService;
import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Date;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api")
public class Sign {

    @Autowired
    private UserService userService = null;

    @PostMapping(value = "/sign")
    public ResponseEntity getQuestionById(@RequestBody String json) {
        if (json.isEmpty()) {
            return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
        }
        JSONObject body = JSONObject.parseObject(json);
        Long telNum = body.getLong("telNum");
        String password = body.getString("password");
        String encodePassword = getResult(password);
        if (telNum == null || telNum == 0 || password == null || encodePassword == null) {
            return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
        }
        User user = userService.getUserNameByTelNum(telNum);
        if (user == null || user.getPassword() == null || !encodePassword.equals(user.getPassword())) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        Long now = Instant.now().toEpochMilli();
        Long x = Math.round(Math.random() * 1000);
        Hashids hashids = new Hashids("this is my salt");
        Map<String, String> result = new HashMap<>();
        result.put("username", user.getName());
        result.put("token", hashids.encode(user.getId(), now, x));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping(value = "/sign")
    public ResponseEntity createUser(@RequestBody String json) {
        if (json.isEmpty()) {
            return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
        }
        JSONObject body = JSONObject.parseObject(json);
        Long telNum = body.getLong("telNum");
        String name = body.getString("name");
        String password = body.getString("password");
        String encodePassword = getResult(password);
        if (telNum == null || telNum == 0 || name == null || name.isEmpty() || password == null || password.isEmpty() || encodePassword == null) {
            return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
        }
        User user = userService.getUserNameByTelNum(telNum);
        if (user != null) {
            return new ResponseEntity(null, HttpStatus.NOT_ACCEPTABLE);
        } else {
            user = new User();
            user.setTelNum(telNum);
            user.setPassword(encodePassword);
            user.setName(name);
            user.setTime(new Date(System.currentTimeMillis()));
            userService.createUser(user);
        }
        Long now = Instant.now().toEpochMilli();
        Long x = Math.round(Math.random() * 1000);
        Hashids hashids = new Hashids("this is my salt");
        Map<String, String> result = new HashMap<>();
        result.put("username", user.getName());
        result.put("token", hashids.encode(user.getId(), now, x));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    private String getResult(String inputStr) {
        if (inputStr == null) {
            return null;
        }
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] inputData = inputStr.getBytes();
            md.update(inputData);
            return new BigInteger(md.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping(value = "/users")
    public ResponseEntity getFeedback() {
        return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
    }
}