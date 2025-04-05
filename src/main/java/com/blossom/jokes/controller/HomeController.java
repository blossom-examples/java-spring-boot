package com.blossom.jokes.controller;

import com.blossom.jokes.service.JokeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final JokeService jokeService;

    @GetMapping("/")
    public String home(Model model) {
        jokeService.getRandomJoke().ifPresent(joke -> model.addAttribute("randomJoke", joke));
        return "home/index";
    }
}