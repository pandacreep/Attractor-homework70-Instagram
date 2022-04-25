package com.pandacreep.instagram.model.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class UserDto {
    private String id;
    private String email;
    private String name;
    private String password;
    private String avatar;
    private String description;
    private String phoneNumber;
    private String gender;
    private boolean subscriptionAvailable;
    private int postsNumber;
    private int subscriptionsNumber;
    private int subscribersNumber;

    public static UserDto from(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .password(user.getPassword())
                .avatar(user.getAvatar())
                .description(user.getDescription())
                .phoneNumber(user.getPhoneNumber())
                .gender(user.getGender())
                .subscriptionAvailable(true)
                .postsNumber(user.getPosts().size())
                .subscriptionsNumber(user.getSubscriptions().size())
                .subscribersNumber(user.getSubscribers().size())
                .build();
    }
}
