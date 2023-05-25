package com.example.mvcdemo.controller;

import com.example.mvcdemo.model.Post;
import com.example.mvcdemo.repository.PostRepository;
import com.example.mvcdemo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.Set;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private Validator validator;

    @PostMapping
    public ResponseEntity<Post> savePost(@RequestBody Post post) {
        Set<ConstraintViolation<Post>> violations = validator.validate(post);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
        Post savedPost = postRepository.save(post);
        return ResponseEntity.ok(savedPost);
    }
    @GetMapping("/author/{authorId}")
    public ResponseEntity<List<Post>> getPostsByAuthor(@PathVariable String authorId) {
        List<Post> posts = postRepository.findByAuthorId(authorId);
        return ResponseEntity.ok(posts);
    }
    @GetMapping("/allPosts")
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return ResponseEntity.ok(posts);
    }
    @GetMapping("/sortedByWeight")
    public ResponseEntity<List<Post>> getPostsSortedByWeight() {
        List<WeightedPostDTO> weightedPosts = postRepository.findPostsByWeightedScore();
        List<Post> sortedPosts = new ArrayList<>();

        for (WeightedPostDTO weightedPost : weightedPosts) {
            Long postId = weightedPost.getId();
            Post post = postRepository.findById(postId).orElse(null);
            if (post != null) {
                sortedPosts.add(post);
            }
        }
        return ResponseEntity.ok(sortedPosts);
    }
}