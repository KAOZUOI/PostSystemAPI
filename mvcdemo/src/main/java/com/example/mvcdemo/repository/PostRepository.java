package com.example.mvcdemo.repository;

import com.example.mvcdemo.controller.WeightedPostDTO;
import com.example.mvcdemo.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByTitle(String title);
    List<Post> findByAuthorId(String authorId);

    List<Post> findByPostingCity(String city);
    List<Post> findByPostingCountry(String country);
    List<Post> findByPostingCountryAndPostingCity(String country, String city);
    @Query(value = "SELECT new com.example.mvcdemo.controller.WeightedPostDTO(p.id, (0.5 * COALESCE(COUNT(l), 0) + 0.3 * COALESCE(COUNT(s), 0) + 0.2 * COALESCE(COUNT(f), 0))) " +
        "FROM Post p " +
        "LEFT JOIN p.likes l " +
        "LEFT JOIN p.shares s " +
        "LEFT JOIN p.favorites f " +
        "GROUP BY p.id " +
        "ORDER BY (0.5 * COALESCE(COUNT(l), 0) + 0.3 * COALESCE(COUNT(s), 0) + 0.2 * COALESCE(COUNT(f), 0)) DESC")
    List<WeightedPostDTO> findPostsByWeightedScore();



}