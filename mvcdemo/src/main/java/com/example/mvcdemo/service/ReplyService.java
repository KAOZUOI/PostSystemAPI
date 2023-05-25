package com.example.mvcdemo.service;

import com.example.mvcdemo.model.Author;
import com.example.mvcdemo.model.Post;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ReplyService {
    void replyToPost(Author author, Post post, String content);
    void replyToReply(Author author, String content, Long replyId);
    void deleteReply(Author author, Post post, Long replyId);
    List<Post> getPostsRepliedByAuthor(Author author);




}
