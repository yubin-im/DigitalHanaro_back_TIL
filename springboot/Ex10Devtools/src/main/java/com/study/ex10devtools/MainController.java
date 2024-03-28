package com.study.ex10devtools;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("data", "서버 데이터: 123");

        return "index";
    }
}
