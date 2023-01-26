package com.hhgg.hhggbe.comment.dto;

import java.util.ArrayList;
import java.util.List;

public class CommentListDto {
    private List<CommentDto> data = new ArrayList<>();

    public CommentListDto(List<CommentDto> data) {
        this.data = data;
    }
}
