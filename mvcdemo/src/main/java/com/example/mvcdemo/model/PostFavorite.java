package com.example.mvcdemo.model;

import javax.persistence.*;

@Entity
@Table
public class PostFavorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Author author;

    @ManyToOne
    private Post post;
    //constructor getter setter
    public PostFavorite() {
    }
    public PostFavorite(Author author, Post post) {
        this.author = author;
        this.post = post;
    }
    public PostFavorite(Long id, Author author, Post post) {
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
