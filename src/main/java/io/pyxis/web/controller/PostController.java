package io.pyxis.web.controller;

import io.pyxis.domain.model.Post;
import io.pyxis.service.PostService;
import io.pyxis.web.dto.PostRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/posts")
    public Page<Post> getAllPosts(Pageable pageable) {
        return postService.getPosts(pageable);
    }

    @PostMapping("/posts")
    public Post createPost(@RequestBody PostRequestDto post) {
        return postService.createPost(post);
    }

    @PutMapping("/posts/{postId}")
    public Post updatePost(@PathVariable Long postId, @Valid @RequestBody Post postRequest) {
        return postService.update(postId,postRequest);
    }


    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId) {
        return postService.delete(postId);
    }

}
