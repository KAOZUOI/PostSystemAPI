package com.example.mvcdemo.model;

import javax.persistence.*;

@Entity
@Table
public class PostShare {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Author author;

    @ManyToOne
    private Post post;

    //Constructor, Getter and Setter methods
    public PostShare() {
    }
    public PostShare(Author author, Post post) {
        this.author = author;
        this.post = post;
    }
    public PostShare(Long id, Author author, Post post) {
        this.id = id;
        this.author = author;
        this.post = post;
    }
    public Long getId() {
        return id;
    }
    public Author getAuthor() {
        return author;
    }
    public Post getPost() {
        return post;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setAuthor(Author author) {
        this.author = author;
    }
    public void setPost(Post post) {
        this.post = post;
    }


}
