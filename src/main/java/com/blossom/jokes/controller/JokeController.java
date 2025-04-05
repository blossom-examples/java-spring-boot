package com.blossom.jokes.controller;

import com.blossom.jokes.model.Joke;
import com.blossom.jokes.service.JokeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/jokes")
@RequiredArgsConstructor
public class JokeController {
    private final JokeService jokeService;

    @GetMapping
    public String listJokes(Model model) {
        model.addAttribute("jokes", jokeService.getAllJokes());
        return "jokes/index";
    }

    @GetMapping("/{id}")
    public String showJoke(@PathVariable Long id, Model model) {
        return jokeService.getJokeById(id)
                .map(joke -> {
                    model.addAttribute("joke", joke);
                    return "jokes/show";
                })
                .orElse("redirect:/jokes");
    }

    @GetMapping("/new")
    public String newJokeForm(Model model) {
        model.addAttribute("joke", new Joke());
        return "jokes/form";
    }

    @GetMapping("/{id}/edit")
    public String editJokeForm(@PathVariable Long id, Model model) {
        return jokeService.getJokeById(id)
                .map(joke -> {
                    model.addAttribute("joke", joke);
                    return "jokes/form";
                })
                .orElse("redirect:/jokes");
    }

    @PostMapping
    public String saveJoke(@Valid @ModelAttribute Joke joke, BindingResult result) {
        if (result.hasErrors()) {
            return "jokes/form";
        }
        jokeService.saveJoke(joke);
        return "redirect:/jokes";
    }

    @PostMapping("/{id}")
    public String updateJoke(@PathVariable Long id, @Valid @ModelAttribute Joke joke, BindingResult result) {
        if (result.hasErrors()) {
            return "jokes/form";
        }
        jokeService.updateJoke(id, joke);
        return "redirect:/jokes";
    }

    @DeleteMapping("/{id}")
    public String deleteJoke(@PathVariable Long id) {
        jokeService.deleteJoke(id);
        return "redirect:/jokes";
    }

    @GetMapping("/random")
    public String randomJoke(Model model) {
        return jokeService.getRandomJoke()
                .map(joke -> {
                    model.addAttribute("joke", joke);
                    return "jokes/show";
                })
                .orElse("redirect:/jokes");
    }

    // REST API endpoints
    @GetMapping("/api")
    @ResponseBody
    public ResponseEntity<?> getAllJokesApi() {
        return ResponseEntity.ok(jokeService.getAllJokes());
    }

    @GetMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<?> getJokeByIdApi(@PathVariable Long id) {
        return jokeService.getJokeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/api")
    @ResponseBody
    public ResponseEntity<?> createJokeApi(@Valid @RequestBody Joke joke) {
        return ResponseEntity.ok(jokeService.saveJoke(joke));
    }

    @PutMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<?> updateJokeApi(@PathVariable Long id, @Valid @RequestBody Joke joke) {
        return jokeService.updateJoke(id, joke)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteJokeApi(@PathVariable Long id) {
        jokeService.deleteJoke(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/like")
    @ResponseBody
    public ResponseEntity<?> likeJoke(@PathVariable Long id) {
        return jokeService.incrementLikes(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}