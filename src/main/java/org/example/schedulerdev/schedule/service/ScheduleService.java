package org.example.schedulerdev.schedule.service;

import lombok.RequiredArgsConstructor;
import org.example.schedulerdev.User.entity.User;
import org.example.schedulerdev.User.repository.UserRepository;
import org.example.schedulerdev.schedule.dto.ScheduleResponseDto;
import org.example.schedulerdev.schedule.entity.Schedule;
import org.example.schedulerdev.schedule.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    private final UserRepository userRepository;

    // 스케줄 생성 API
    public ScheduleResponseDto save(String username, String title, String contents) {

        User findUser = userRepository.findMemberByUsernameOrElseThrow(username);

        Schedule schedule = new Schedule(username,title,contents);
        schedule.setUser(findUser);
        Schedule savedSchedule = scheduleRepository.save(schedule);

        return new ScheduleResponseDto(savedSchedule.getId(),savedSchedule.getTitle(),savedSchedule.getContents());
    }

    // 스케줄 전체 조회
    public List<ScheduleResponseDto> findAll() {
        return scheduleRepository.findAll()
                .stream()
                .map(ScheduleResponseDto :: toDto)
                .toList();
    }

    // 특정 스케줄 조회
    public ScheduleResponseDto findById(Long id) {
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);

        return new ScheduleResponseDto(findSchedule.getId(),findSchedule.getTitle(), findSchedule.getContents());
    }

    // 스케줄 내용 수정
    @Transactional
    public void updateSchedule(Long id, String contents) {
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);

        findSchedule.updateContents(contents);
    }

    // 스케줄 삭제
    public void deleteSchedule(Long id) {
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);
        scheduleRepository.delete(findSchedule);
    }
}
