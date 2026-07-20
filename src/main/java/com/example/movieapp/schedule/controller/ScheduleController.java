package com.example.movieapp.schedule.controller;

import com.example.movieapp.schedule.dto.CreateScheduleRequest;
import com.example.movieapp.schedule.dto.CreateScheduleResponse;
import com.example.movieapp.schedule.dto.GetScheduleResponse;
import com.example.movieapp.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("/users/{userId}/schedules")
    public ResponseEntity<CreateScheduleResponse> create(@PathVariable Long userId, @RequestBody CreateScheduleRequest request) {
        return ResponseEntity.ok(scheduleService.create(userId, request));
    }

    @GetMapping("/users/{userId}/schedules")
    public ResponseEntity<List<GetScheduleResponse>> getAll(@PathVariable Long userId) {
        return ResponseEntity.ok(scheduleService.getAll(userId));
    }

    @GetMapping("/users/{userId}/schedules/{scheduleId}")
    public ResponseEntity<GetScheduleResponse> getOne(@PathVariable Long userId, @PathVariable Long scheduleId) {
        return ResponseEntity.ok(scheduleService.getOne(userId, scheduleId));
    }
}
