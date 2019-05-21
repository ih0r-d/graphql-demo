package io.pyxis.http.api.service;

import io.pyxis.http.api.domain.model.Post;
import io.pyxis.http.api.web.dto.PostRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface PostService {

    Page<Post> getPosts(Pageable pageable);
    Post createPost(PostRequestDto post);
    Post update(Long postId, Post post);
    ResponseEntity delete(Long postId);
}
