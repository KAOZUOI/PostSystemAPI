package com.example.mvcdemo.service;

import com.example.mvcdemo.model.Post;
import com.example.mvcdemo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;
    public void addPost(Post post) {
        postRepository.save(post);
    }
    //getPostById
    public Post getPostById(Long id) {
        return postRepository.findById(id).get();
    }
    //getPost list by author
    public List<Post> getPostsByAuthor(String authorId) {
        return postRepository.findByAuthorId(authorId);
    }
    public List<Post> findPostsByCity(String city) {
        return postRepository.findByPostingCity(city);
    }
    //country
    public List<Post> findPostsByCountry(String country) {
        return postRepository.findByPostingCountry(country);
    }

    public List<Post> findPostsByCountryAndCity(String country, String city) {
        return postRepository.findByPostingCountryAndPostingCity(country, city);
    }
}
