package com.spring.crud.controller.page;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
@Controller
public class TbUserPageController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/{page}")
    public String user(@PathVariable("page") String page) {
        return "/user/" + page;
    }

}
