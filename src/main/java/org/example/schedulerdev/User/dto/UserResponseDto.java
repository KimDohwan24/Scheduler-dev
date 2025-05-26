package org.example.schedulerdev.User.dto;

import lombok.Getter;
import org.example.schedulerdev.User.entity.User;

@Getter
public class UserResponseDto {

    private final Long id;
    private final String username;
    private final String email;

    public UserResponseDto(Long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }



    public static UserResponseDto toDto(User user){
        return new UserResponseDto(user.getId(), user.getUsername(), user.getEmail());
    }
}
