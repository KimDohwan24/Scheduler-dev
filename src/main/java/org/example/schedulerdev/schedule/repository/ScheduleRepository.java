package org.example.schedulerdev.schedule.repository;

import org.example.schedulerdev.User.entity.User;
import org.example.schedulerdev.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    default Schedule findByIdOrElseThrow(Long id){
        return findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "스케줄을 찾을 수 없습니다." + id));
    }
}
