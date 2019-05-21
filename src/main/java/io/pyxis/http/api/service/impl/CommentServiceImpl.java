package io.pyxis.http.api.service.impl;

import io.pyxis.http.api.domain.model.Comment;
import io.pyxis.http.api.domain.repository.CommentRepository;
import io.pyxis.http.api.domain.repository.PostRepository;
import io.pyxis.http.api.exception.ResourceNotFoundException;
import io.pyxis.http.api.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Override
    public Page<Comment> getCommentsByPostId(Long postId, Pageable pageable) {
        return commentRepository.findByPostId(postId, pageable);
    }

    @Override
    public Comment addCommentToPost(Long postId, Comment comment) {
        return postRepository.findById(postId)
                            .map(post -> {
                                comment.setPost(post);
                                return commentRepository.save(comment);})
                            .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public Comment update(Long postId, Long commentId, Comment comment) {
        if (!postRepository.existsById(postId)) {
            throw new ResourceNotFoundException();
        }
        return commentRepository.findById(commentId)
                                .map(c -> {
                                    c.setText(c.getText());
                                    return commentRepository.save(comment); })
                                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public ResponseEntity delete(Long postId, Long commentId) {
        return commentRepository.findByIdAndPostId(commentId, postId)
                                .map(comment -> {
                                    commentRepository.delete(comment);
                                    return ResponseEntity.ok().build(); })
                                .orElseThrow(ResourceNotFoundException::new);
    }
}
