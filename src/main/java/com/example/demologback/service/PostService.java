package com.example.demologback.service;

import com.example.demologback.dto.PostDto;
import com.example.demologback.dto.PostResponseDto;
import com.example.demologback.dto.PostUpdateDto;
import com.example.demologback.entity.Member;
import com.example.demologback.entity.Post;
import com.example.demologback.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    @Transactional(readOnly = true)
    public List<PostResponseDto> findAll() {
        return postRepository.findAll().stream()
                .map(Post::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PostResponseDto findPostById(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("포스트가 존재하지 않습니다."));

        return post.toDto();
    }

    @Transactional
    public Long addPost(PostDto postDto) {
        Post post = Post.builder()
                .writer(Member.builder()
                        .memberId(postDto.getWriterId())
                        .build())
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .build();

        Post savePost = postRepository.save(post);
        return savePost.getPostId();
    }

    @Transactional
    public boolean updatePost(Long postId, PostUpdateDto postUpdateDto) {
        try {
            Post post = postRepository.findById(postId)
                    .orElseThrow(() -> new RuntimeException("포스트가 존재하지 않습니다."));

            post.update(postUpdateDto.getTitle(), postUpdateDto.getContent());

            postRepository.save(post);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    public boolean deletePost(Long postId) {
        try {
            postRepository.deleteById(postId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
