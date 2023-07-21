package com.example.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.entity.ShortenedURL;
import com.example.repository.ShortenedURLRepository;

@Service
public class ShortenedURLService {
    private final ShortenedURLRepository shortenedURLRepository;

    // Constructor injection
    public ShortenedURLService(ShortenedURLRepository shortenedURLRepository) {
        this.shortenedURLRepository = shortenedURLRepository;
    }

    public String shortenURL(String originalURL) {
        // Check if the originalURL already exists in the database
        Optional<ShortenedURL> existingURL = shortenedURLRepository.findByShortURL(originalURL);
        if (existingURL.isPresent()) {
            // Return the existing short URL
            return existingURL.get().getShortURL();
        }

        // Generate a short URL and save it to the database with the expiration time
        String shortURL = generateShortURL();
        LocalDateTime expirationDateTime = LocalDateTime.now().plusMinutes(5);
        ShortenedURL shortenedURL = new ShortenedURL(originalURL, shortURL, expirationDateTime);
        shortenedURLRepository.save(shortenedURL);

        return shortURL;
    }

    public String getOriginalURL(String shortURL) {
        // Find the URL entry in the database
        Optional<ShortenedURL> shortenedURL = shortenedURLRepository.findByShortURL(shortURL);
        if (shortenedURL.isPresent()) {
            // Check if the URL is still valid (not expired)
            if (LocalDateTime.now().isBefore(shortenedURL.get().getExpirationDateTime())) {
                return shortenedURL.get().getOriginalURL();
            } else {
                return "URL has expired";
            }
        } else {
            return "URL doesn't exist";
        }
    }

    // Helper method to generate a short URL (e.g., using random strings)
    private String generateShortURL() {
        // Implement your logic to generate a short URL
        // For simplicity, let's just return a random string of length 8 here
        return RandomStringUtils.randomAlphanumeric(8);
    }
}
