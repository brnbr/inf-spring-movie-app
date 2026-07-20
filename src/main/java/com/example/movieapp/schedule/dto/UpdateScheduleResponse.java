package com.example.movieapp.schedule.dto;

import com.example.movieapp.schedule.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UpdateScheduleResponse {

    private final String title;
    private final String content;
    private final LocalDateTime modifiedAt;

    public UpdateScheduleResponse(Schedule schedule) {
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.modifiedAt = schedule.getModifiedAt();
    }
}
