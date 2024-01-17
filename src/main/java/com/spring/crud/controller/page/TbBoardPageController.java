package com.spring.crud.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/board")
@Controller
public class TbBoardPageController {

    @GetMapping("")
    public String create() {
        return "board/create";
    }
}
