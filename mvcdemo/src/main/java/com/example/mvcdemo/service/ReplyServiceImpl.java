package com.example.mvcdemo.service;

import com.example.mvcdemo.model.Author;
import com.example.mvcdemo.model.Post;
import com.example.mvcdemo.model.Reply;
import com.example.mvcdemo.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyServiceImpl implements ReplyService{
    @Autowired
    private ReplyRepository replyRepository;
    @Override
    public void replyToPost(Author author, Post post, String content) {
        Reply reply = new Reply();
        reply.setAuthor(author);
        reply.setPost(post);
        reply.setContent(content);
        replyRepository.save(reply);
    }

    @Override
    public void replyToReply(Author author, String content, Long replyId) {
        Reply reply = new Reply();
        reply.setAuthor(author);
        reply.setContent(content);
        reply.setParent(replyRepository.findById(replyId).get());
        replyRepository.save(reply);

    }

    @Override
    public void deleteReply(Author author, Post post, Long replyId) {

    }

    @Override
    public List<Post> getPostsRepliedByAuthor(Author author) {
        List<Reply> replies = replyRepository.findByAuthor(author);
        if (replies != null) {
            return replies.stream().map(Reply::getPost).toList();
        }
        return null;
    }
}
