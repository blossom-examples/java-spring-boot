package com.blossom.jokes.controller;

import com.blossom.jokes.model.Joke;
import com.blossom.jokes.service.JokeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/jokes")
@RequiredArgsConstructor
public class JokeController {

    private final JokeService jokeService;

    @GetMapping
    public String listJokes(Model model) {
        List<Joke> jokes = jokeService.getAllJokes();
        model.addAttribute("jokes", jokes);
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
        model.addAttribute("categories", jokeService.getAllCategories());
        return "jokes/form";
    }

    @GetMapping("/{id}/edit")
    public String editJokeForm(@PathVariable Long id, Model model) {
        return jokeService.getJokeById(id)
                .map(joke -> {
                    model.addAttribute("joke", joke);
                    model.addAttribute("categories", jokeService.getAllCategories());
                    return "jokes/form";
                })
                .orElse("redirect:/jokes");
    }

    @PostMapping
    public String saveJoke(@Valid @ModelAttribute("joke") Joke joke, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("categories", jokeService.getAllCategories());
            return "jokes/form";
        }
        jokeService.createJoke(joke);
        return "redirect:/jokes";
    }

    @PostMapping("/{id}")
    public String updateJoke(@PathVariable Long id, @Valid @ModelAttribute("joke") Joke joke, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("categories", jokeService.getAllCategories());
            return "jokes/form";
        }
        jokeService.updateJoke(id, joke);
        return "redirect:/jokes";
    }

    @GetMapping("/{id}/delete")
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
    public ResponseEntity<List<Joke>> getAllJokesApi() {
        return ResponseEntity.ok(jokeService.getAllJokes());
    }

    @GetMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<Joke> getJokeByIdApi(@PathVariable Long id) {
        return jokeService.getJokeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/api/random")
    @ResponseBody
    public ResponseEntity<Joke> getRandomJokeApi() {
        return jokeService.getRandomJoke()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/api")
    @ResponseBody
    public ResponseEntity<Joke> createJokeApi(@Valid @RequestBody Joke joke) {
        Joke createdJoke = jokeService.createJoke(joke);
        return new ResponseEntity<>(createdJoke, HttpStatus.CREATED);
    }

    @PutMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<Joke> updateJokeApi(@PathVariable Long id, @Valid @RequestBody Joke joke) {
        return jokeService.updateJoke(id, joke)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteJokeApi(@PathVariable Long id) {
        jokeService.deleteJoke(id);
        return ResponseEntity.noContent().build();
    }
}