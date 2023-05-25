package com.example.mvcdemo.repository;

import com.example.mvcdemo.model.Author;
import com.example.mvcdemo.model.Post;
import com.example.mvcdemo.model.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLike, Long> {

    Optional<PostLike> findByAuthorAndPost(Author author, Post post);

    int countByPost(Post post);
    List<PostLike> findByAuthor(Author author);






}