package com.pandacreep.instagram.model.post;

import com.pandacreep.instagram.model.comment.Comment;
import com.pandacreep.instagram.model.like.Like;
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
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("posts")
public class Post {
    @Id
    private String id = UUID.randomUUID().toString();
    private String imageString;
    private String description;
    private LocalDateTime dateTime;

    @DBRef
    private User user;

    @DBRef
    private List<User> likeUsers;

    @DBRef
    private List<Comment> comments;
}
