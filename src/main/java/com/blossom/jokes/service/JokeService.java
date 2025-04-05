package com.blossom.jokes.service;

import com.blossom.jokes.model.Joke;
import com.blossom.jokes.repository.JokeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JokeService {

    private final JokeRepository jokeRepository;

    public List<Joke> getAllJokes() {
        return jokeRepository.findAll();
    }

    public Optional<Joke> getJokeById(Long id) {
        return jokeRepository.findById(id);
    }

    public Optional<Joke> getRandomJoke() {
        return jokeRepository.findRandomJoke();
    }

    public List<Joke> getJokesByCategory(String category) {
        return jokeRepository.findByCategory(category);
    }

    public List<String> getAllCategories() {
        return jokeRepository.findAllCategories();
    }

    @Transactional
    public Joke createJoke(Joke joke) {
        return jokeRepository.save(joke);
    }

    @Transactional
    public Optional<Joke> updateJoke(Long id, Joke jokeDetails) {
        return jokeRepository.findById(id)
                .map(existingJoke -> {
                    existingJoke.setContent(jokeDetails.getContent());
                    existingJoke.setAuthor(jokeDetails.getAuthor());
                    existingJoke.setCategory(jokeDetails.getCategory());
                    return jokeRepository.save(existingJoke);
                });
    }

    @Transactional
    public void deleteJoke(Long id) {
        jokeRepository.deleteById(id);
    }
}