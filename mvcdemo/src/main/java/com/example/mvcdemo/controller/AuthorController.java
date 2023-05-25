package com.example.mvcdemo.controller;


import com.example.mvcdemo.model.Author;
import com.example.mvcdemo.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    @Autowired
    private AuthorRepository authorRepository;

    @PostMapping
    public ResponseEntity<String> createAuthor(@RequestBody Author author) {
        authorRepository.save(author);
        return ResponseEntity.status(HttpStatus.CREATED).body("Author created successfully");
    }
    
    @GetMapping("/allAuthors")
    public ResponseEntity<List<Author>> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        return ResponseEntity.ok(authors);
    }

}