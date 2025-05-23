package org.example.schedulerdev.User.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.schedulerdev.User.entity.User;


@Getter
@Valid
@NoArgsConstructor
public class LoginRequestDto {

    @Email
    @NotEmpty(message = "이메일을 적어주세요")
    private String email;

    @NotEmpty(message = "비밀번호를 입력하세요")
    private String password;

    public LoginRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
