package com.example.mvcdemo.repository;

import com.example.mvcdemo.model.Author;
import com.example.mvcdemo.model.Post;
import com.example.mvcdemo.model.PostFavorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostFavoriteRepository extends JpaRepository<PostFavorite, Long> {

    Optional<PostFavorite> findByAuthorAndPost(Author author, Post post);

    int countByPost(Post post);
    List<PostFavorite> findByAuthor(Author author);



}