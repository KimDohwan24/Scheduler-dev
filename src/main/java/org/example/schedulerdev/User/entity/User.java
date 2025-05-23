package org.example.schedulerdev.User.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.schedulerdev.common.BaseEntity;

@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor
public class User extends BaseEntity {
    // 유저명 이메일
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    @NotEmpty
    @Email
    private String useremail;

    public User(String username, String password, String useremail) {
        this.username = username;
        this.password = password;
        this.useremail = useremail;
    }

    public void updatePassWord(String password) {
        this.password = password;
    }
}
