package org.example.schedulerdev.User.controller;

import lombok.RequiredArgsConstructor;
import org.example.schedulerdev.User.dto.UpdatePasswordDto;
import org.example.schedulerdev.User.dto.UserRequestDto;
import org.example.schedulerdev.User.dto.UserResponseDto;
import org.example.schedulerdev.User.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    // User 생성 / 조회 / 수정 / 삭제 만들기

    // 유저 생성
    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> signup(@RequestBody UserRequestDto userRequestDto){
        UserResponseDto signupResponseDto = userService.signup(userRequestDto.getUsername(), userRequestDto.getPassword(), userRequestDto.getEmail());

        return new ResponseEntity<>(signupResponseDto, HttpStatus.CREATED);
    }

    // 전체 유저 조회
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findAll(){
        List<UserResponseDto> responseDtoList = userService.findAll();

        return new ResponseEntity<>(responseDtoList,HttpStatus.OK);
    }

    // 특정 유저 조회
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long id){
        UserResponseDto userResponseDto = userService.findById(id);

        return new ResponseEntity<>(userResponseDto,HttpStatus.OK);
    }

    // 비밀번호 수정
    @PatchMapping("/{id}")
    public ResponseEntity<Void> updatePassword(
            @PathVariable Long id,
            @RequestBody UpdatePasswordDto updatePasswordDto
            ){
        userService.updatePassword(id,updatePasswordDto.getOldPassword(), updatePasswordDto.getNewPassword());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 유저 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(
            @PathVariable Long id,
            @RequestBody UserRequestDto userRequestDto
    ){
        userService.deleteUser(id, userRequestDto.getPassword());

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
