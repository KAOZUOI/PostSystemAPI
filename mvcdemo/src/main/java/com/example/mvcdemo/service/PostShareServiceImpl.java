package com.example.mvcdemo.service;

import com.example.mvcdemo.model.Author;
import com.example.mvcdemo.model.Post;
import com.example.mvcdemo.model.PostLike;
import com.example.mvcdemo.model.PostShare;
import com.example.mvcdemo.repository.PostShareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostShareServiceImpl implements PostShareService{
    @Autowired
    private PostShareRepository postShareRepository;

    @Override
    public void sharePost(Author author, Post post) {
        Optional<PostShare> optionalPostShare = postShareRepository.findByAuthorAndPost(author, post);
        if (optionalPostShare.isEmpty()) {
            PostShare postShare = new PostShare(author, post);
            postShareRepository.save(postShare);
        }
    }

    @Override
    public int getPostSharesCount(Post post) {
        return postShareRepository.countByPost(post);
    }

    @Override
    public List<Post> getPostsSharedByAuthor(Author author) {
        List<PostShare> postShare = postShareRepository.findByAuthor(author);
        if (postShare != null) {
            return postShare.stream().map(PostShare::getPost).toList();
        }
        return null;
    }
}
