package com.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("manage")
public class ManageController {

    private static final String INDEX = "index";

    @GetMapping(value = "/index")
    public String gotoIndex() {
        return INDEX;
    }
}