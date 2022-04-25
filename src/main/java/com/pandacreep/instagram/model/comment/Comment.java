package com.pandacreep.instagram.model.comment;

import com.pandacreep.instagram.model.post.Post;
import com.pandacreep.instagram.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.codecs.pojo.annotations.BsonId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Builder
@Document("comments")
public class Comment {
    @Id
    private String id = UUID.randomUUID().toString();

    private String text;
    private LocalDateTime dateTime;

    @DBRef
    private User user;

    @DBRef
    private Post post;

}
