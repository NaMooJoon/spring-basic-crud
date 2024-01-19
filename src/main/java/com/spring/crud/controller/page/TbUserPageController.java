package com.spring.crud.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
@Controller
public class TbUserPageController {

    @GetMapping("/{page}")
    public String user(@PathVariable("page") String page) {
        return "/user/" + page;
    }
}
