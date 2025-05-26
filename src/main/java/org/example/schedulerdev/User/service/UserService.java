package org.example.schedulerdev.User.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.schedulerdev.User.dto.LoginRequestDto;
import org.example.schedulerdev.User.dto.UserResponseDto;
import org.example.schedulerdev.User.entity.User;
import org.example.schedulerdev.User.repository.UserRepository;
import org.example.schedulerdev.config.PasswordEncoder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 회원가입
    public UserResponseDto signup(String username, String password, String email) {
        String encodePassword = passwordEncoder.encode(password);

        User user = new User(username, encodePassword, email);
        User saveduser = userRepository.save(user);

        return new UserResponseDto(saveduser.getId(), saveduser.getUsername(), saveduser.getEmail());
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

        if (findById.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "유저가 없습니다 : " + id);
        }

        User user = findById.get();

        return new UserResponseDto(user.getId(), user.getUsername(), user.getEmail());
    }


    // 비밀번호 변경
    @Transactional
    public void updatePassword(Long id, String oldPassword, String newPassword) {
        User user = userRepository.findByIdOrElseThrow(id);

        if(!passwordEncoder.matches(oldPassword,user.getPassword())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"비밀번호가 일치하지 않습니다.");
        }

        String encodePassword = passwordEncoder.encode(newPassword);

        user.updatePassWord(encodePassword);
    }

    // 유저 삭제
    @Transactional
    public void deleteUser(Long id, String password) {
        User user = userRepository.findByIdOrElseThrow(id);

        if(!passwordEncoder.matches(password,user.getPassword())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"비밀번호가 일치하지 않습니다.");
        }

        userRepository.delete(user);
    }

    // 로그인
    public LoginRequestDto login(String email, String password) {

        User user = userRepository.findByEmailOrElseThrow(email);

        if(!passwordEncoder.matches(password,user.getPassword())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"비밀번호가 일치하지 않습니다.");
        }

        return new LoginRequestDto(user.getEmail(),user.getPassword());
    }
}
