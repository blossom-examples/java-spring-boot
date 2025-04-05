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

    public List<Joke> getJokesByCategory(String category) {
        return jokeRepository.findByCategory(category);
    }

    public Optional<Joke> getRandomJoke() {
        return Optional.ofNullable(jokeRepository.findRandomJoke());
    }

    public List<String> getAllCategories() {
        return jokeRepository.findAllCategories();
    }

    @Transactional
    public Joke saveJoke(Joke joke) {
        return jokeRepository.save(joke);
    }

    @Transactional
    public void deleteJoke(Long id) {
        jokeRepository.deleteById(id);
    }

    @Transactional
    public Optional<Joke> updateJoke(Long id, Joke jokeDetails) {
        return jokeRepository.findById(id)
                .map(joke -> {
                    joke.setContent(jokeDetails.getContent());
                    joke.setAuthor(jokeDetails.getAuthor());
                    joke.setCategory(jokeDetails.getCategory());
                    return jokeRepository.save(joke);
                });
    }

    @Transactional
    public Optional<Joke> incrementLikes(Long id) {
        return jokeRepository.findById(id)
                .map(joke -> {
                    joke.setLikes(joke.getLikes() + 1);
                    return jokeRepository.save(joke);
                });
    }

    public List<Joke> searchJokes(String query) {
        List<Joke> byContent = jokeRepository.findByContentContainingIgnoreCase(query);
        List<Joke> byAuthor = jokeRepository.findByAuthorContainingIgnoreCase(query);
        byContent.addAll(byAuthor);
        return byContent.stream().distinct().toList();
    }
}