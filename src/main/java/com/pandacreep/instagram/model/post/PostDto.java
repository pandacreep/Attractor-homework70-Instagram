package com.pandacreep.instagram.model.post;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostDto {
    private String id;
    private String imageString;
    private String description;
    private String date;
    private boolean liked;

    public static PostDto from(Post post) {
        return PostDto.builder()
                .id(post.getId())
                .imageString(post.getImageString())
                .description(post.getDescription())
                .date(post.getDateTime().toLocalDate().toString())
                .liked(false)
                .build();
    }
}
