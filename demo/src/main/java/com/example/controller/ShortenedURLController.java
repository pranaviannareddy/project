package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.ShortenedURLService;

@RestController
public class ShortenedURLController {
    private final ShortenedURLService shortenedURLService;

    // Constructor injection
    public ShortenedURLController(ShortenedURLService shortenedURLService) {
        this.shortenedURLService = shortenedURLService;
    }

    @PostMapping("/shorten")
    public String shortenURL(@RequestBody String originalURL) {
        return shortenedURLService.shortenURL(originalURL);
    }

    @GetMapping("/{shortURL}")
    public String redirectToOriginalURL(@PathVariable String shortURL) {
        String originalURL = shortenedURLService.getOriginalURL(shortURL);
        if (originalURL.startsWith("http://") || originalURL.startsWith("https://")) {
            return "redirect:" + originalURL;
        } else {
            return originalURL; // For simplicity, return the error message as the response
        }
    }
}
