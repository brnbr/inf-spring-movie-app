package com.example.movieapp.user.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UpdateUserResponse {

    private final Long id;
    private final String name;
    private final String email;
}
