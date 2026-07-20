package com.example.movieapp.user.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class GetUserResponse {

    private final Long id;
    private final String username;
    private final String email;
}
