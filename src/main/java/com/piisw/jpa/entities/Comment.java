//package com.piisw.jpa.entities;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//
//@Getter
//@Setter
//@Entity
//public abstract class Comment {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    private Long id;
//
//    @Column(nullable = false)
//    private String content;
//
//    @ManyToOne
//    @JoinColumn(name = "event_id")
//    private Event event;
//}
