package com.hhgg.hhggbe.post;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hhgg.hhggbe.comment.Comment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@RequiredArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long postId;

    private String title;
    private String content;
    private String imageUrl;

//    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
//    @JsonIgnore
//    private List<Comment> comment = new ArrayList<>();
    private boolean isDelete;

    public void PostPatch(PostRequestDto postRequestDto, String imageUrl){
        this.title = (postRequestDto.getTitle() == null) ? this.getTitle() : postRequestDto.getTitle();
        this.content = (postRequestDto.getContent() == null) ? this.getContent() : postRequestDto.getContent();
        this.imageUrl = (imageUrl == null) ? this.getImageUrl() : imageUrl;
    }
    public void PostPatchNoImage(PostRequestDto postRequestDto) {
        this.title = (postRequestDto.getTitle() == null) ? this.getTitle() : postRequestDto.getTitle();
        this.content = (postRequestDto.getContent() == null) ? this.getContent() : postRequestDto.getContent();
        this.imageUrl = this.getImageUrl();
    }
    public void PostDelete(){
        this.isDelete = true;
    }
}
