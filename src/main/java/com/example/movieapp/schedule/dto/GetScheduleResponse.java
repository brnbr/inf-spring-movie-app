package com.example.movieapp.schedule.dto;

import com.example.movieapp.schedule.entity.Schedule;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@JsonPropertyOrder
public class GetScheduleResponse {

    private final Long id;
    private final String username;
    private final String title;
    private final String content;
    private final int commentCount;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public GetScheduleResponse(Schedule schedule) {
        this.id = schedule.getId();
        this.username = schedule.getUser().getUsername();
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.commentCount = schedule.getComment().size();
        this.createdAt = schedule.getCreatedAt();
        this.modifiedAt = schedule.getModifiedAt();
    }
}
