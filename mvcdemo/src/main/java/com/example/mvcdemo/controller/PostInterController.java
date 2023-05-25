package com.example.mvcdemo.controller;

import com.example.mvcdemo.model.Author;
import com.example.mvcdemo.model.Category;
import com.example.mvcdemo.model.Post;
import com.example.mvcdemo.service.AuthorService;
import com.example.mvcdemo.service.PostLikeService;
import com.example.mvcdemo.service.PostService;
import com.example.mvcdemo.service.PostShareService;
import com.example.mvcdemo.service.PostFavoriteService;
import com.example.mvcdemo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostInterController {

    @Autowired
    private PostService postService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private PostLikeService postLikeService;

    @Autowired
    private PostShareService postShareService;

    @Autowired
    private PostFavoriteService postFavoriteService;

    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping("/{postId}/like")
    public ResponseEntity likePost(@PathVariable Long postId, @RequestBody String authorName) {
        Post optionalPost = postService.getPostById(postId);
        Author optionalAuthor = authorService.findByAuthorname(authorName);
        System.out.println(optionalAuthor.getAuthorname());
        postLikeService.likePost(optionalAuthor, optionalPost);
        return new ResponseEntity<>("Post liked", HttpStatus.OK);
    }
    @PostMapping("/{postId}/share")
    public ResponseEntity sharePost(@PathVariable Long postId, @RequestBody String authorName) {
        Post optionalPost = postService.getPostById(postId);
        Author optionalAuthor = authorService.findByAuthorname(authorName);
        postShareService.sharePost(optionalAuthor, optionalPost);
        return new ResponseEntity<>("Post shared", HttpStatus.OK);
    }
    @PostMapping("/{postId}/favorite")
    public ResponseEntity favoritePost(@PathVariable Long postId, @RequestBody String authorName) {
        Post optionalPost = postService.getPostById(postId);
        Author optionalAuthor = authorService.findByAuthorname(authorName);
        postFavoriteService.favoritePost(optionalAuthor, optionalPost);
        return new ResponseEntity<>("Post favorited", HttpStatus.OK);
    }

    @GetMapping("/likes")
    public ResponseEntity getPostLikesCount(@RequestParam("authorName") String authorName) {
        Author optionalAuthor = authorService.findByAuthornameForReply(authorName);
        return new ResponseEntity<>(postLikeService.getPostsLikedByAuthor(optionalAuthor), HttpStatus.OK);
    }

    @GetMapping("/shares")
    public ResponseEntity getPostSharesCount(@RequestParam("authorName") String authorName) {
        Author optionalAuthor = authorService.findByAuthornameForReply(authorName);
        return new ResponseEntity<>(postShareService.getPostsSharedByAuthor(optionalAuthor), HttpStatus.OK);
    }

    @GetMapping("/favorites")
    public ResponseEntity getPostFavoritesCount(@RequestParam("authorName") String authorName) {
        Author optionalAuthor = authorService.findByAuthornameForReply(authorName);
        return new ResponseEntity<>(postFavoriteService.getPostsFavoritedByAuthor(optionalAuthor), HttpStatus.OK);
    }

    @GetMapping("/category/{name}")
    public List<Post> getPostsByCategory(@PathVariable String name) {
        Category category = categoryRepository.findByName(name);
        if (category != null) {
            return category.getPosts();
        }
        return null;
    }

    @GetMapping("/city/{city}")
    public List<Post> getPostsByCity(@PathVariable String city) {
        return postService.findPostsByCity(city);
    }
    @GetMapping("/country/{country}")
    public List<Post> getPostsByCountry(@PathVariable String country) {
        return postService.findPostsByCountry(country);
    }
    @GetMapping("/country/{country}/city/{city}")
    public List<Post> getPostsByCountryAndCity(@PathVariable String country, @PathVariable String city) {
        return postService.findPostsByCountryAndCity(country, city);
    }


}

