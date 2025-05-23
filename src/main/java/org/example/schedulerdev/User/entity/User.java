package org.example.schedulerdev.User.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.schedulerdev.common.BaseEntity;

@Getter
@Entity
@Setter
@Table(name = "users")
@NoArgsConstructor
public class User extends BaseEntity {
    // 유저명 이메일
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Size(min = 1,max = 5)
    private String username;

    @NotEmpty
    private String password;

    @Email
    @Pattern(
            regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$",
            message = "유효한 이메일 형식이 아닙니다."
    )
    private String email;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public void updatePassWord(String password) {
        this.password = password;
    }
}
