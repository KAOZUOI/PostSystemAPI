package com.example.mvcdemo.service;

import com.example.mvcdemo.model.Author;
import com.example.mvcdemo.model.Post;
import com.example.mvcdemo.model.PostFavorite;
import com.example.mvcdemo.model.PostLike;
import com.example.mvcdemo.repository.PostFavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostFavoriteServiceImpl implements PostFavoriteService{
    @Autowired
    private PostFavoriteRepository postFavoriteRepository;
    @Override
    public void favoritePost(Author author, Post post) {
        Optional<PostFavorite> optionalPostFavorite = postFavoriteRepository.findByAuthorAndPost(author, post);
        if (optionalPostFavorite.isEmpty()) {
            PostFavorite postFavorite = new PostFavorite(author, post);
            postFavoriteRepository.save(postFavorite);
        }
    }

    @Override
    public int getPostFavoritesCount(Post post) {
        return postFavoriteRepository.countByPost(post);
    }

    @Override
    public List<Post> getPostsFavoritedByAuthor(Author author) {
        List<PostFavorite> postFavorites = postFavoriteRepository.findByAuthor(author);
        if (postFavorites != null) {
            return postFavorites.stream().map(PostFavorite::getPost).toList();
        }
        return null;
    }
}
