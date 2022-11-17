package com.example.demologback.entity;

import com.example.demologback.dto.MemberDto;
import com.example.demologback.dto.PostResponseDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @ManyToOne
    @JoinColumn(name = "member_Id")
    private Member writer;

    @Column(length = 50)
    private String title;

    @Column
    private String content;

    @CreatedDate
    private LocalDateTime createDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;

    @JsonIgnore
    public PostResponseDto toDto() {
        return PostResponseDto.builder()
                .postId(postId)
                .writer(MemberDto.builder()
                        .memberId(writer.getMemberId())
                        .name(writer.getName())
                        .build())
                .title(title)
                .content(content)
                .createDate(createDate)
                .build();
    }

    @JsonIgnore
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
