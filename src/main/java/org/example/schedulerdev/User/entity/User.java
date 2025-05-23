package org.example.schedulerdev.User.entity;

import jakarta.persistence.*;
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

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
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
