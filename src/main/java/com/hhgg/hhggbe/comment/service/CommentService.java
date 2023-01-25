package com.hhgg.hhggbe.comment.service;

import com.hhgg.hhggbe.comment.dto.CommentRequestDto;
import com.hhgg.hhggbe.comment.dto.ResponseMessageDto;
import com.hhgg.hhggbe.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
    CommentRepository commentRepository;

    @Transactional
    public ResponseMessageDto create(Long id, CommentRequestDto request, User user) {

    }
}
