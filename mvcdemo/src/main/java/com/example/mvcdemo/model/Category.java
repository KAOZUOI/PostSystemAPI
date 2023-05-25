package com.example.mvcdemo.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "categories")
    private List<Post> posts;

    public Category() {
    }
    public Category(String name) {
        this.name = name;
    }
    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    public Long getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
    public List<Post> getPosts() {
        return this.posts;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name= name;
    }
    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

}
