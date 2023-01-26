package com.hhgg.hhggbe.comment.service;

import com.hhgg.hhggbe.comment.dto.CommentRequestDto;
import com.hhgg.hhggbe.comment.dto.ResponseMessageDto;
import com.hhgg.hhggbe.comment.repository.CommentRepository;
import com.hhgg.hhggbe.post.Post;
import com.hhgg.hhggbe.post.PostRepository;
import com.hhgg.hhggbe.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
    CommentRepository commentRepository;
    PostRepository postRepository;

    @Transactional
    public ResponseMessageDto create(Long id, CommentRequestDto request, User user) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디의 게시글이 존재하지 않습니다.")
        );

    }
}
