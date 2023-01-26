package com.hhgg.hhggbe.post;

import java.util.Optional;

public interface PostRepository {
    Optional<Object> findById(Long id);
}
