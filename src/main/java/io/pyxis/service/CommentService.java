package io.pyxis.service;

import io.pyxis.domain.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface CommentService {
    Page<Comment> getCommentsByPostId(Long postId, Pageable pageable);
    Comment addCommentToPost(Long postId, Comment comment);
    Comment update(Long postId, Long commentId, Comment comment);
    ResponseEntity delete(Long postId, Long commentId);


    }
