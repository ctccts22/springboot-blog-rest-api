package com.springboot.blog.service.impl;

import com.springboot.blog.entity.Comment;
import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.BlogAPIException;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.repository.CommentRepository;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Override
    public CommentDto createComment(Long postId, CommentDto commentDto) {
        Comment comment = mapToEntity(commentDto);

        // retrieve post entity by id
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", postId));

        comment.assignToPost(post);
//        comment.setPost(post);

        // comment entity to dto
        Comment newComment = commentRepository.save(comment);

        return mapToDTO(newComment);
    }

    @Override
    public List<CommentDto> getCommentsByPostId(Long postId) {
        // retrieve comments by postId
        List<Comment> comments = commentRepository.findByPostId(postId);

        // convert list of comment entities to list of comment dto's
        return comments
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(Long postId, Long commentId) {
//        // retrieve post entity by id
//        Post post = postRepository.findById(postId).orElseThrow(
//                () -> new ResourceNotFoundException("Post", "id", postId));
//        // retrieve comment entity by id
//        Comment comment = commentRepository.findById(commentId).orElseThrow(
//                () -> new ResourceNotFoundException("Comment", "id", commentId));

        // retrieve comment entity by post id and comment id - fetch 조인
        Comment comment = commentRepository.findByPostIdAndId(postId, commentId).orElseThrow(
                () -> new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment either does not exist or does not belong to the specified post."));

//        if (!comment.getPost().getId().equals(post.getId())) {
//            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
//        }

        return mapToDTO(comment);
    }

    @Override
    public CommentDto updateComment(Long postId, Long commentId, CommentDto commentRequest) {
        // retrieve comment entity by post id and comment id - fetch 조인
        Comment comment = commentRepository.findByPostIdAndId(postId, commentId).orElseThrow(
                () -> new BlogAPIException(HttpStatus.BAD_REQUEST,
                        "Comment either does not exist or does not belong to the specified post."));

        comment.update(commentRequest);
        Comment updatedComment = commentRepository.save(comment);

        return mapToDTO(updatedComment);
    }

    @Override
    public void deleteComment(Long postId, Long commentId) {
        // retrieve comment entity by post id and comment id - fetch 조인
        Comment comment = commentRepository.findByPostIdAndId(postId, commentId).orElseThrow(
                () -> new BlogAPIException(HttpStatus.BAD_REQUEST,
                        "Comment either does not exist or does not belong to the specified post."));

        commentRepository.delete(comment);
    }

    private CommentDto mapToDTO(Comment comment) {
//        CommentDto commentDto = modelMapper.map(comment, CommentDto.class);
        CommentDto commentDto = CommentDto.builder()
                .id(comment.getId())
                .name(comment.getName())
                .email(comment.getEmail())
                .body(comment.getBody())
                .build();
        return commentDto;
    }

    private Comment mapToEntity(CommentDto commentDto) {
//        Comment comment = modelMapper.map(commentDto, Comment.class);
        Comment comment = Comment.builder()
                .id(commentDto.getId())
                .name(commentDto.getName())
                .email(commentDto.getEmail())
                .body(commentDto.getEmail())
                .build();
        return comment;
    }
}
