package com.example.mvcdemo.service;

import com.example.mvcdemo.model.Author;
import com.example.mvcdemo.model.Post;

import java.util.List;

public interface PostFavoriteService {
    void favoritePost(Author author, Post post);
    int getPostFavoritesCount(Post post);
    List<Post> getPostsFavoritedByAuthor(Author author);
}