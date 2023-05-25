package com.example.mvcdemo.repository;

import com.example.mvcdemo.model.Author;
import com.example.mvcdemo.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {

    List<Reply> findByAuthor(Author author);






}
