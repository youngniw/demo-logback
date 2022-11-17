package com.example.demologback.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class MemberDto {
    private Long memberId;
    private String name;
}
