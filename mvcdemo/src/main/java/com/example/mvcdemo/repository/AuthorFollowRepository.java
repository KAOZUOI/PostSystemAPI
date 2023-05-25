package com.example.mvcdemo.repository;

import com.example.mvcdemo.model.Author;
import com.example.mvcdemo.model.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorFollowRepository extends JpaRepository<Follow, Long> {
    List<Follow> findByFollower(Author follower);
    Optional<Follow> findByFollowerAndFollowing(Author follower, Author following);


}
