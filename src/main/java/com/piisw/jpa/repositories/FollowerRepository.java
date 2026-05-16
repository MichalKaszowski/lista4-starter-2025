package com.piisw.jpa.repositories;

import com.piisw.jpa.entities.Comment;
import com.piisw.jpa.entities.Follower;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowerRepository extends JpaRepository<Follower, String> {

    @EntityGraph(value = "Follower.commentsAndEvents", type = EntityGraph.EntityGraphType.LOAD)
    List<Comment> findByUserId(String userId);
}
