package com.example.mvcdemo.service;

import com.example.mvcdemo.model.Author;
import com.example.mvcdemo.model.Post;
import com.example.mvcdemo.model.PostLike;

import java.util.List;

public interface PostLikeService {
    void likePost(Author author, Post post);
    int getPostLikesCount(Post post);
    List<Post> getPostsLikedByAuthor(Author author);
}
