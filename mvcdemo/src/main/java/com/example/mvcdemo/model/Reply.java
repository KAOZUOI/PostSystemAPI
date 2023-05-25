package com.example.mvcdemo.model;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne
    private Author author;

    @ManyToOne
    private Post post;

    @ManyToOne
    private Reply parent;

    public Reply() {
    }
    public Reply(Author author, Post post, Reply parent) {
        this.author = author;
        this.post = post;
        this.parent = parent;
    }
    public Reply(Long id, Author author, Post post, Reply parent) {
        this.id = id;
        this.author = author;
        this.post = post;
        this.parent = parent;
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
    public Reply getParent() {
        return parent;
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
    public void setParent(Reply parent) {
        this.parent = parent;
    }
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
