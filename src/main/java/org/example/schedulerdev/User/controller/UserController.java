package org.example.schedulerdev.User.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.schedulerdev.User.dto.LoginRequestDto;
import org.example.schedulerdev.User.dto.UpdatePasswordDto;
import org.example.schedulerdev.User.dto.UserRequestDto;
import org.example.schedulerdev.User.dto.UserResponseDto;
import org.example.schedulerdev.User.service.UserService;
import org.example.schedulerdev.common.Const;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

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

    @PostMapping("/login")
    public ResponseEntity<LoginRequestDto> login(
            @RequestBody LoginRequestDto loginRequestDto,
            HttpServletRequest httpServletRequest
    ){
        // 유저 검색 (email을 통해)
//        LoginRequestDto loginUser = userService.findByEmail(loginRequestDto.getEmail());

        // 로그인 로직
        LoginRequestDto login = userService.login(loginRequestDto.getEmail(), loginRequestDto.getPassword());

        // 로그인 성공시 로직
        // Session의 Default Value는 true이다.
        // Session이 request에 존재하면 기존의 Session을 반환하고,
        // Session이 request에 없을 경우에 새로 Session을 생성한다.
        HttpSession httpSession = httpServletRequest.getSession();
        httpSession.setAttribute(Const.LOGIN_USER,login);

        // 세션값 로그 찍어보기
//         log.info("session.getId()={}", httpSession.getId());


        return new ResponseEntity<>(login,HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest httpServletRequest){
        HttpSession httpSession = httpServletRequest.getSession(false);

        if(httpSession != null){
            httpSession.invalidate();
//            log.info("session.getId()={}", httpSession.getId());
        }


        return new ResponseEntity<>(HttpStatus.OK);
    }
}

