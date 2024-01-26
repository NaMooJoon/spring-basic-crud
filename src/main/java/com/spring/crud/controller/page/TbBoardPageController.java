package com.spring.crud.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/board")
@Controller
public class TbBoardPageController {

    @GetMapping("/create")
    public String create() {
        return "board/create";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") String id, Model model) {
        model.addAttribute("id", id);
        return "board/update";
    }

    @GetMapping("/list")
    public String list() {
        return "board/list";
    }

    @GetMapping("/page")
    public String page() {
        return "board/page";
    }

    @GetMapping("/detail/{id}")
    public String detail() {
        return "board/detail";
    }

    @GetMapping("/scroll")
    public String scroll() {
        return "board/scroll";
    }
}
