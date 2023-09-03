package com.springboot.blog.payload;

import com.springboot.blog.entity.Comment;
import lombok.Builder;
import lombok.Data;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Data
@Builder
public class CommentDto {
    private Long id;
    // name should not be null or empty
    @NotEmpty(message = "Name should not be null or empty")
    private String name;

    // email should not be null or empty
    // email field validation
    @NotEmpty(message = "Email should not be null or empty")
    @Email
    private String email;

    // comment body should not be bull or empty
    // Comment body must be minimum 10 characters
    @NotEmpty
    @Size(min = 10, message = "Comment body must be minimum 10 characters")
    private String body;

    public static CommentDto mapToDTO(Comment comment) {
        return CommentDto.builder()
                .id(comment.getId())
                .name(comment.getName())
                .email(comment.getEmail())
                .body(comment.getBody())
                .build();
    }
}
