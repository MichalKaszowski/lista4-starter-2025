package com.piisw.jpa.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table
@NamedEntityGraph(name = "graph.Follower.comments.event",
        attributeNodes = @NamedAttributeNode("comments")
)
@NamedEntityGraph(
        name = "graph.Follower.comments.event",
        attributeNodes = {
                @NamedAttributeNode(value = "comments", subgraph = "comments")
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "comments",
                        attributeNodes = @NamedAttributeNode("event")
                )
        }
)
public class Follower {

    @Id
    private String userId;

    @Column
    private LocalDateTime subscriptionDate;

    @OneToMany(mappedBy = "follower", cascade = CascadeType.ALL)
    private Set<Comment> comments = new HashSet<>();
}