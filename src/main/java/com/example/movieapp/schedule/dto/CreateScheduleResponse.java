package com.example.movieapp.schedule.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class CreateScheduleResponse {

    private final Long id;
    private final String username;
    private final String title;
    private final String content;
    private final LocalDateTime createdAt;
}
