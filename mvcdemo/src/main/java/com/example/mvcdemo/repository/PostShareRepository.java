package com.example.mvcdemo.repository;

import com.example.mvcdemo.model.Author;
import com.example.mvcdemo.model.Post;
import com.example.mvcdemo.model.PostLike;
import com.example.mvcdemo.model.PostShare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostShareRepository extends JpaRepository<PostShare, Long> {

    Optional<PostShare> findByAuthorAndPost(Author author, Post post);

    int countByPost(Post post);
    List<PostShare> findByAuthor(Author author);



}
