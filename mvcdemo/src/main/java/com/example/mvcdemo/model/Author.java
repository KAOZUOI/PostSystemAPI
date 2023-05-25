package com.example.mvcdemo.model;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Author {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "author_id", columnDefinition = "CHAR(36)")
    private String authorId;
    @Column(unique=true)
    private String authorname;
    private String registrationTime;
    private String phoneNumber;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<PostLike> likes;
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<PostShare> shares;
    //post favorite
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<PostFavorite> favorites;
    @OneToMany(mappedBy = "following")
    private List<Follow> followings;
    @OneToMany(mappedBy = "author")
    private List<Reply> replies;
    public Author() {
    }
    public Author(String authorId){
        this.authorId = authorId;
    }
    public Author(String authorname, String registrationTime, String phoneNumber) {
        this.authorname = authorname;
        this.registrationTime = registrationTime;
        this.phoneNumber = phoneNumber;
    }
    public Author(String id, String authorname, String registrationTime, String phoneNumber) {
        this.authorId = id;
        this.authorname = authorname;
        this.registrationTime = registrationTime;
        this.phoneNumber = phoneNumber;
    }

    public String getId() {
        return authorId;
    }
    public String getAuthorname() {
        return authorname;
    }
    public String getRegistrationTime() {
        return registrationTime;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setId(String id) {
        this.authorId = id;
    }
    public void setAuthorname(String authorname) {
        this.authorname = authorname;
    }
    public void setRegistrationTime(String registrationTime) {
        this.registrationTime = registrationTime;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
