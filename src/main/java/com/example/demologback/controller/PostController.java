package com.example.demologback.controller;

import com.example.demologback.dto.PostDto;
import com.example.demologback.dto.PostResponseDto;
import com.example.demologback.dto.PostUpdateDto;
import com.example.demologback.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    @GetMapping("/list")
    public ResponseEntity<List<PostResponseDto>> getPostList() {
        log.info("Http Method (GET) - post list");
        return ResponseEntity.ok(postService.findAll());
    }
    @GetMapping("/{postId}")
    public ResponseEntity<PostResponseDto> getPost(@PathVariable("postId") Long postId) {
        log.info("Http Method (GET) - post");
        return ResponseEntity.ok(postService.findPostById(postId));
    }

    @PostMapping
    public ResponseEntity<String> addPost(@RequestBody PostDto postDto) {
        log.info("Http Method (POST) - post");

        postService.addPost(postDto);

        return ResponseEntity.ok("success");
    }

    @PatchMapping("/{postId}")
    public ResponseEntity<String> updatePost(@PathVariable("postId") Long postId, @RequestBody PostUpdateDto postUpdateDto) {
        log.info("Http Method (PATCH) - post");

        postService.updatePost(postId, postUpdateDto);

        return ResponseEntity.ok("success");
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable("postId") Long postId) {
        log.info("Http Method (DELETE) - post");

        postService.deletePost(postId);

        return ResponseEntity.ok("success");
    }
}
