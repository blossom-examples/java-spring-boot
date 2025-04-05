package com.blossom.jokes.repository;

import com.blossom.jokes.model.Joke;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JokeRepository extends JpaRepository<Joke, Long> {

    List<Joke> findByCategory(String category);

    @Query(value = "SELECT * FROM jokes ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    Joke findRandomJoke();

    List<Joke> findByAuthorContainingIgnoreCase(String author);

    List<Joke> findByContentContainingIgnoreCase(String content);

    @Query(value = "SELECT DISTINCT category FROM jokes", nativeQuery = true)
    List<String> findAllCategories();
}