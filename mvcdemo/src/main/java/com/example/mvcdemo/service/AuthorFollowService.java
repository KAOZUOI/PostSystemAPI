package com.example.mvcdemo.service;

import com.example.mvcdemo.model.Author;
import com.example.mvcdemo.model.Follow;
import com.example.mvcdemo.repository.AuthorFollowRepository;
import com.example.mvcdemo.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorFollowService {
    @Autowired
    private AuthorFollowRepository authorFollowRepository;
    @Autowired
    private AuthorRepository authorRepository;
    public void followAuthor(Author follower, Author author) {
        Optional<Follow> optionalFollow = authorFollowRepository.findByFollowerAndFollowing(follower, author);
        if (optionalFollow.isEmpty()) {
            Follow follow = new Follow(follower, author);
            authorFollowRepository.save(follow);
        }
    }
    //method:unfollow author
    public void unfollowAuthor(Author follower, Author author) {
        Optional<Follow> optionalFollow = authorFollowRepository.findByFollowerAndFollowing(follower, author);
        if (optionalFollow.isPresent()) {
            authorFollowRepository.delete(optionalFollow.get());
        }
    }
    //method:get following list
    public List<Follow> getFollowingList(Author follower) {
        return authorFollowRepository.findByFollower(follower);
    }

}
