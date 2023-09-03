package com.springboot.blog.entity;

import com.springboot.blog.payload.CommentDto;
import lombok.*;

import jakarta.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String body;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @Builder
    public Comment(Long id, String name, String email, String body, Post post) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.body = body;
        this.post = post;
    }

    public void assignToPost(Post createdPost) {
        this.post = createdPost;
    }

    public void update(CommentDto commentRequest) {
        this.name = commentRequest.getName();
        this.email = commentRequest.getEmail();
        this.body = commentRequest.getBody();
    }
}
