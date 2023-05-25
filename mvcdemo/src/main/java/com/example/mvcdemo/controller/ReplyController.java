package com.example.mvcdemo.controller;

import com.example.mvcdemo.RequestInfo.ReplyInfo;
import com.example.mvcdemo.model.Author;
import com.example.mvcdemo.model.Post;
import com.example.mvcdemo.service.AuthorService;
import com.example.mvcdemo.service.PostService;
import com.example.mvcdemo.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/reply")
public class ReplyController {
    @Autowired
    private ReplyService replyService;
    @Autowired
    private PostService postService;
    @Autowired
    private AuthorService authorService;

    @PostMapping("/{postId}/replyToPost")
    public String replyToPost(@PathVariable Long postId, @RequestBody ReplyInfo replyInfo) {
        Post optionalPost = postService.getPostById(postId);
        Author optionalAuthor = authorService.findByAuthornameForReply(replyInfo.getAuthorName());
        replyService.replyToPost(optionalAuthor, optionalPost, replyInfo.getContent());
        return "redirect:/user";
    }
    @PostMapping("/{replyId}/replyToReply")
    public String replyToReply(@PathVariable Long replyId, @RequestBody ReplyInfo replyInfo) {
        Author optionalAuthor = authorService.findByAuthornameForReply(replyInfo.getAuthorName());
        replyService.replyToReply(optionalAuthor, replyInfo.getContent(), replyId);
        return "redirect:/user";
    }

    @GetMapping("/repliedPosts")
    public ResponseEntity<List<Post>> getPostsRepliedByAuthor(@RequestParam String authorName) {
        Author optionalAuthor = authorService.findByAuthornameForReply(authorName);
        List<Post> repliedPosts = replyService.getPostsRepliedByAuthor(optionalAuthor);
        return new ResponseEntity<>(repliedPosts, HttpStatus.OK);
    }


}
