package org.example.schedulerdev.User.service;

import lombok.RequiredArgsConstructor;
import org.example.schedulerdev.User.dto.UserResponseDto;
import org.example.schedulerdev.User.entity.User;
import org.example.schedulerdev.User.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // 회원가입
    public UserResponseDto signup(String username, String password, String email) {
        User user = new User(username, password, email);

        User saveduser = userRepository.save(user);

        return new UserResponseDto(saveduser.getId(), saveduser.getUsername(), saveduser.getUseremail());
    }

    // 전체 유저 조회
    public List<UserResponseDto> findAll() {

        return userRepository.findAll()
                .stream()
                .map(UserResponseDto::toDto)
                .toList();

    }
    // 특정 유저 조회
    public UserResponseDto findById(Long id) {
        Optional<User> findById = userRepository.findById(id);

        if(findById.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"유저가 없습니다 : " + id);
        }

        User user = findById.get();

        return new UserResponseDto(user.getId(), user.getUsername(), user.getUseremail());
    }


    // 비밀번호 변경
    @Transactional
    public void updatePassword(Long id, String oldPassword, String newPassword) {
        User user = userRepository.findByIdOrElseThrow(id);

        if(!user.getPassword().equals(oldPassword)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"비밀번호가 일치하지 않습니다.");
        }

        user.updatePassWord(newPassword);
    }

    public void deleteUser(Long id, String password) {
        User user = userRepository.findByIdOrElseThrow(id);

        if(!user.getPassword().equals(password)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"비밀번호가 일치하지 않습니다");
        }

        userRepository.delete(user);
    }
}
