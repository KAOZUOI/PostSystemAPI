package com.example.mvcdemo.controller;

import com.example.mvcdemo.model.Author;
import com.example.mvcdemo.model.Post;
import com.example.mvcdemo.service.AuthorFollowService;
import com.example.mvcdemo.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/author")
public class AuthorFollowController {
    @Autowired
    private AuthorFollowService authorFollowService;
    @Autowired
    private AuthorService authorService;
    //method:follow author
    @RequestMapping("/{authorId}/followAuthor")
    public ResponseEntity likePost(@PathVariable String authorId, @RequestBody String authorName) {
        Author optionalFollower = authorService.findByAuthorId(authorId);
        Author optionalAuthor = authorService.findByAuthorname(authorName);
        authorFollowService.followAuthor(optionalFollower, optionalAuthor);
        return new ResponseEntity<>("Author Followed", HttpStatus.OK);
    }
    //method:unfollow author
    @RequestMapping("/{authorId}/unfollowAuthor")
    public ResponseEntity unfollowAuthor(@PathVariable String authorId, @RequestBody String authorName) {
        Author optionalFollower = authorService.findByAuthorId(authorId);
        Author optionalAuthor = authorService.findByAuthorname(authorName);
        authorFollowService.unfollowAuthor(optionalFollower, optionalAuthor);
        return new ResponseEntity<>("Author Unfollowed", HttpStatus.OK);
    }
    //method:getFollowingList
    @RequestMapping("/{authorId}/getFollowingList")
    public ResponseEntity getFollowingList(@PathVariable String authorId) {
        Author optionalFollower = authorService.findByAuthorId(authorId);
        return new ResponseEntity<>(authorFollowService.getFollowingList(optionalFollower), HttpStatus.OK);
    }





}
