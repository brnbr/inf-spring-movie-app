package com.example.movieapp.schedule.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class UpdateScheduleResponse {

    private final String title;
    private final String content;
    private final LocalDateTime modifiedAt;
}
