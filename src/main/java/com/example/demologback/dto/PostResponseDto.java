package com.example.demologback.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Getter
public class PostResponseDto {
    private Long postId;
    private MemberDto writer;
    private String title;
    private String content;
    private LocalDateTime createDate;
}
