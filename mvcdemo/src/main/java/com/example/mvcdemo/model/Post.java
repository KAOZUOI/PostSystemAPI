package com.example.mvcdemo.model;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "标题不能为空")
    private String title;
    @NotBlank(message = "内容不能为空")
    private String content;

    private String authorId;
    private String postingTime;
    private String postingCity;
    private String postingCountry;
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<PostLike> likes;
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<PostShare> shares;
    //post favorite
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<PostFavorite> favorites;
    @OneToMany(mappedBy = "post")
    private List<Reply> replies;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "post_category",
        joinColumns = {@JoinColumn(name = "post_id")},
        inverseJoinColumns = {@JoinColumn(name = "category_id")}
    )
    private List<Category> categories;


    public String getPostingTime() {
        return postingTime;
    }
    public void setPostingTime(String postingTime) {
        this.postingTime = postingTime;
    }
    public String getPostingCity() {
        return postingCity;
    }
    public void setPostingCity(String postingCity) {
        this.postingCity = postingCity;
    }
    public String getPostingCountry() {
        return postingCountry;
    }
    public void setPostingCountry(String postingCountry) {
        this.postingCountry = postingCountry;
    }
    public String getAuthorId() {
        return authorId;
    }
    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }
    public Post() {
    }
    public Post(String title, String content, String authorId, String postingTime, String postingCity,
            String postingCountry) {
        this.title = title;
        this.content = content;
        this.authorId = authorId;
        this.postingTime = postingTime;
        this.postingCity = postingCity;
        this.postingCountry = postingCountry;
    }
    public Post(Long id, String title, String content, String authorId, String postingTime, String postingCity,
            String postingCountry) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.authorId = authorId;
        this.postingTime = postingTime;
        this.postingCity = postingCity;
        this.postingCountry = postingCountry;


    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public String getContent() {
        return content;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setContent(String content) {
        this.content = content;
    }

}
