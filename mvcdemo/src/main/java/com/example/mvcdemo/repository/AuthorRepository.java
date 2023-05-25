package com.example.mvcdemo.repository;

import com.example.mvcdemo.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, String> {
    Author findByAuthorname(String authorname);
    Author findByAuthorId(String authorId);

}
