package com.example.mvcdemo.model;

import javax.persistence.*;

@Entity
@Table
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Author follower; // 关注者
    @ManyToOne
    private Author following; // 被关注者

    public Follow() {
    }
    public Follow(Author follower, Author following) {
        this.follower = follower;
        this.following = following;
    }
    public Follow(Long id, Author follower, Author following) {
        this.id = id;
        this.follower = follower;
        this.following = following;
    }
    public Long getId() {
        return id;
    }
    public Author getFollower() {
        return follower;
    }
    public Author getFollowing() {
        return following;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setFollower(Author follower) {
        this.follower = follower;
    }
    public void setFollowing(Author following) {
        this.following = following;
    }

}