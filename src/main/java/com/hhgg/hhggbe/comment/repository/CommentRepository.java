package com.hhgg.hhggbe.comment.repository;

import com.hhgg.hhggbe.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
