package com.example.mvcdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("message", "Hello World");
        return "index";
    }
    @GetMapping("/post")
    public String post(Model model) {
        model.addAttribute("message", "Hello World");
        return "post";
    }
    @GetMapping("/postInter")
    public String postInter(Model model) {
        model.addAttribute("message", "Hello World");
        return "post_inter";
    }
    @GetMapping("/user")
    public String user(Model model) {
        model.addAttribute("message", "Hello World");
        return "user";
    }
    @GetMapping("/reply")
    public String reply(Model model) {
        model.addAttribute("message", "Hello World");
        return "reply";
    }
    @GetMapping("/replyList")
    public String replyList(Model model) {
        model.addAttribute("message", "Hello World");
        return "reply_list";
    }
    @GetMapping("/hotPost")
    public String hotPost(Model model) {
        model.addAttribute("message", "Hello World");
        return "hot_post";
    }
    @GetMapping("/search")
    public String search(Model model) {
        model.addAttribute("message", "Hello World");
        return "search";
    }
}
