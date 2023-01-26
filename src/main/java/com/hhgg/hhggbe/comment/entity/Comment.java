package com.hhgg.hhggbe.comment.entity;

import com.hhgg.hhggbe.post.Post;
import com.hhgg.hhggbe.timestamped.Timestamped;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Comment extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;   // 다른 id랑 헷갈릴거같아서 구분해봤습니다

    @ManyToOne
    @JoinColumn(nullable = false)
    private Post post;

    @Column(nullable = false)
    private String comment;


}
