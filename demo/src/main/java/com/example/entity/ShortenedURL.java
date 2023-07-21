package com.example.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.Generated;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;


@EntityScan
public class ShortenedURL {
    

	public ShortenedURL(String originalURL2, String shortURL2, LocalDateTime expirationDateTime2) {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOriginalURL() {
		return originalURL;
	}

	public void setOriginalURL(String originalURL) {
		this.originalURL = originalURL;
	}

	public String getShortURL() {
		return shortURL;
	}

	public void setShortURL(String shortURL) {
		this.shortURL = shortURL;
	}

	public LocalDateTime getExpirationDateTime() {
		return expirationDateTime;
	}

	public void setExpirationDateTime(LocalDateTime expirationDateTime) {
		this.expirationDateTime = expirationDateTime;
	}

	@Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")   
    private Long id;

    @Column(nullable = false, unique = true)
    private String originalURL;

    @Column(nullable = false, unique = true)
    private String shortURL;

    @Column(nullable = false)
    private LocalDateTime expirationDateTime;
    
    // Constructors, getters, setters, etc.
}