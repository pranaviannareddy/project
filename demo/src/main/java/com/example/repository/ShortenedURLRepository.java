package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.ShortenedURL;

public interface ShortenedURLRepository extends JpaRepository<ShortenedURL, Long> {
    Optional<ShortenedURL> findByShortURL(String shortURL);
}
