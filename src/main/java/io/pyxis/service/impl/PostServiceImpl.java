package io.pyxis.service.impl;

import io.pyxis.domain.model.Post;
import io.pyxis.domain.repository.CommentRepository;
import io.pyxis.domain.repository.PostRepository;
import io.pyxis.exception.ResourceNotFoundException;
import io.pyxis.service.PostService;
import io.pyxis.web.dto.PostRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Override
    public Page<Post> getPosts(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @Override
    public Post createPost(PostRequestDto postRequest) {
        Post post = new Post();
        post.setContent(postRequest.getContent());
        post.setTitle(postRequest.getTitle());
        post.setDescription(postRequest.getDescription());
        postRepository.save(post);
        return post;
    }

    @Override
    public Post update(Long postId, Post post) {
        return postRepository.findById(postId)
                            .map(p -> {
                                p.setTitle(post.getTitle());
                                p.setDescription(post.getDescription());
                                p.setContent(post.getContent());
                                return postRepository.save(p); })
                            .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public ResponseEntity delete(Long postId) {
        return postRepository.findById(postId).map(post -> {
            postRepository.delete(post);
            return ResponseEntity.noContent().build();
        }).orElseThrow(ResourceNotFoundException::new);
    }
}
