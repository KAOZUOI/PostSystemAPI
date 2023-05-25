package com.example.mvcdemo.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class PostLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Author author;

    @ManyToOne
    private Post post;

    // Constructor, Getter and Setter methods

    public PostLike() {
    }
    public PostLike(Author author, Post post) {
        this.author = author;
        this.post = post;
    }
    public PostLike(Long id, Author author, Post post) {
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