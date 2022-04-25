package com.pandacreep.instagram.model.like;

import com.pandacreep.instagram.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.codecs.pojo.annotations.BsonId;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Builder
@Document("likes")
public class Like {
    @BsonId
    private String id;

    @DBRef
    private User user;
}
