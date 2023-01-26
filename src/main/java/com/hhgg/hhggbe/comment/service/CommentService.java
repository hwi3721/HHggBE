package com.hhgg.hhggbe.comment.service;

import com.hhgg.hhggbe.comment.dto.CommentDto;
import com.hhgg.hhggbe.comment.dto.CommentListDto;
import com.hhgg.hhggbe.comment.dto.CommentRequestDto;
import com.hhgg.hhggbe.comment.dto.ResponseMessageDto;
import com.hhgg.hhggbe.comment.entity.Comment;
import com.hhgg.hhggbe.comment.repository.CommentRepository;
import com.hhgg.hhggbe.post.Post;
import com.hhgg.hhggbe.post.PostRepository;
import com.hhgg.hhggbe.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    CommentRepository commentRepository;
    PostRepository postRepository;

    @Transactional(readOnly = true)
    public CommentListDto get(Long id) {
        List<Comment> comments = commentRepository.findAllByPost_Id(id).orElse(new ArrayList<>());
        List<CommentDto> commentDto = comments.stream().map(CommentDto::new).collect(Collectors.toList());
        return new CommentListDto(commentDto);
    }

    @Transactional
    public ResponseMessageDto create(Long id, CommentRequestDto request, User user) {
        //댓글
        if(!request.isReply()) {
            Post post = postRepository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("해당 아이디의 게시글이 존재하지 않습니다.")
            );
            commentRepository.save(new Comment(user, post, request.getComment(), request.isReply(), null));
        }

        //대댓글
        if(request.isReply()) {
            Comment comment = commentRepository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("해당 아이디의 댓글이 존재하지 않습니다.")
            );
            commentRepository.save(new Comment(user, comment.getPost(), request.getComment(), request.isReply(), comment.getId()));
        }

        //리턴
        return new ResponseMessageDto(HttpStatus.CREATED.value(), "댓글 등록 완료");
    }

    @Transactional
    public ResponseMessageDto update(Long id, CommentRequestDto request, User user) {
        Comment comment = getComment(id, user);
        comment.update(request.getComment());
        return new ResponseMessageDto(HttpStatus.OK.value(), "댓글 수정 완료");
    }

    @Transactional
    public ResponseMessageDto delete(Long id, User user) {
        Comment comment = getComment(id, user);
        commentRepository.delete(comment);
        return new ResponseMessageDto(HttpStatus.OK.value(), "댓글 삭제 완료");
    }

    private Comment getComment(Long id, User user) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디의 댓글이 존재하지 않습니다.")
        );
        if(!comment.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("본인이 작성한 댓글만 수정 / 삭제 가능합니다");
        }
        return comment;
    }
}
