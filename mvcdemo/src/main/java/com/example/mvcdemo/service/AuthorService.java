package com.example.mvcdemo.service;

import com.example.mvcdemo.model.Author;
import com.example.mvcdemo.repository.AuthorRepository;
import com.example.mvcdemo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;
    //method:findByAuthorname
    public Author findByAuthorname(String authorname) {
        System.out.println(authorname);

        authorname = authorname.substring(1,authorname.length()-1);
        return authorRepository.findByAuthorname(authorname);
    }
    public Author findByAuthornameForReply(String authorname) {
        System.out.println(authorname);
        return authorRepository.findByAuthorname(authorname);
    }
    //method:findByAuthorId
    public Author findByAuthorId(String authorId) {
        return authorRepository.findByAuthorId(authorId);
    }




}
