package com.hhgg.hhggbe.comment.dto;

import com.hhgg.hhggbe.comment.entity.Comment;

public class CommentDto {
    private Long id;
    private String comment;

    public CommentDto(Comment comment) {
        this.id = comment.getId();
        this.comment = comment.getComment();
    }
}
