package com.example.movieapp.schedule.controller;

import com.example.movieapp.schedule.dto.*;
import com.example.movieapp.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @GetMapping("/schedules")
    public ResponseEntity<Page<GetScheduleResponse>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {

        Page<GetScheduleResponse> scheduleResponses = scheduleService.findAll(page, size);
        return ResponseEntity.ok(scheduleResponses);
    }

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

    @PutMapping("/users/{userId}/schedules/{scheduleId}")
    public ResponseEntity<UpdateScheduleResponse> update(@PathVariable Long scheduleId, @RequestBody UpdateScheduleRequest request) {
        return ResponseEntity.ok(scheduleService.update(scheduleId, request));
    }

    @DeleteMapping("/users/{userId}/schedules/{scheduleId}")
    public ResponseEntity<Void> delete(@PathVariable Long scheduleId) {
        scheduleService.delete(scheduleId);
        return ResponseEntity.noContent().build();
    }
}
