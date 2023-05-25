package com.example.mvcdemo.service;

import com.example.mvcdemo.model.Author;
import com.example.mvcdemo.model.Post;

import java.util.List;

public interface PostShareService {
    void sharePost(Author author, Post post);
    int getPostSharesCount(Post post);
    List<Post> getPostsSharedByAuthor(Author author);
}
