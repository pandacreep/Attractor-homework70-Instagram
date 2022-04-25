package com.pandacreep.instagram.model.comment;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentDto {
    private String id;
    private String text;
    private String email;
    private String dateTime;

    public static CommentDto from(Comment comment) {
        return CommentDto.builder()
                .id(comment.getId())
                .text(comment.getText())
                .email(comment.getUser().getEmail())
                .dateTime(comment.getDateTime().toString())
                .build();
    }
}
