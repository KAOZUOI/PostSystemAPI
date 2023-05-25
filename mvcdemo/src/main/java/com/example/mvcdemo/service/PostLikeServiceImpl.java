package com.example.mvcdemo.service;

import com.example.mvcdemo.model.Author;
import com.example.mvcdemo.model.Post;
import com.example.mvcdemo.model.PostLike;
import com.example.mvcdemo.repository.PostLikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostLikeServiceImpl implements PostLikeService {

    @Autowired
    private PostLikeRepository postLikeRepository;

    @Override
    public void likePost(Author author, Post post) {
        Optional<PostLike> optionalPostLike = postLikeRepository.findByAuthorAndPost(author, post);
        if (optionalPostLike.isEmpty()) {
            PostLike postLike = new PostLike(author, post);
            postLikeRepository.save(postLike);
        }
    }

    @Override
    public int getPostLikesCount(Post post) {
        return postLikeRepository.countByPost(post);
    }
    @Override
    public List<Post> getPostsLikedByAuthor(Author author) {
        List<PostLike> postLikes = postLikeRepository.findByAuthor(author);
        System.out.println(postLikes.get(0).getPost().getContent());
        if (postLikes != null) {
            return postLikes.stream().map(PostLike::getPost).toList();
        }
        return null;
    }
}