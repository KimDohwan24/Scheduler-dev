package org.example.schedulerdev.schedule.dto;

import lombok.Getter;

@Getter
public class UpdateScheduleDto {
    private final String contents;

    public UpdateScheduleDto(String contents) {
        this.contents = contents;
    }
}
