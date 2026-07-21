package com.example.movieapp.comment.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class GetCommnetResponse {

    private final Long id;
    private final String content;
    private final LocalDateTime createdAt;
}
