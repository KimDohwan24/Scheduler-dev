package org.example.schedulerdev.schedule.controller;

import lombok.RequiredArgsConstructor;
import org.example.schedulerdev.schedule.dto.ScheduleRequestDto;
import org.example.schedulerdev.schedule.dto.ScheduleResponseDto;
import org.example.schedulerdev.schedule.dto.UpdateScheduleDto;
import org.example.schedulerdev.schedule.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    // 스케줄 생성 API
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> save(@RequestBody ScheduleRequestDto scheduleRequestDto) {
        ScheduleResponseDto scheduleResponseDto = scheduleService.save(scheduleRequestDto.getUsername(), scheduleRequestDto.getTitle(), scheduleRequestDto.getContents());

        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.CREATED);
    }

    // 스케줄 전체 조회
    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAll(){
        List<ScheduleResponseDto> findAllSchedule = scheduleService.findAll();

        return new ResponseEntity<>(findAllSchedule,HttpStatus.OK);
    }

    // 특정 스케줄 조회
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findById(@PathVariable Long id){
        ScheduleResponseDto findschedule = scheduleService.findById(id);

        return new ResponseEntity<>(findschedule,HttpStatus.OK);
    }

    // 스케줄 수정
    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateschedule(
            @PathVariable Long id,
            @RequestBody UpdateScheduleDto updateScheduleDto
    ){
        scheduleService.updateSchedule(id,updateScheduleDto.getContents());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 스케줄 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id){
        scheduleService.deleteSchedule(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
