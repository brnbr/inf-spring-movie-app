package com.example.movieapp.comment.controller;

import com.example.movieapp.comment.dto.CreateCommentRequest;
import com.example.movieapp.comment.dto.CreateCommentResponse;
import com.example.movieapp.comment.dto.GetCommnetResponse;
import com.example.movieapp.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    //CREATE
    @PostMapping("/schedules/{scheduleId}/comments")
    public ResponseEntity<CreateCommentResponse> create(
            @PathVariable Long scheduleId,
            @RequestBody CreateCommentRequest request
    ) {
        return ResponseEntity.ok(commentService.create(scheduleId, request));
    }

    //READ All
    @GetMapping("/schedules/{scheduleId}/comments")
    public ResponseEntity<List<GetCommnetResponse>> getAll(@PathVariable Long scheduleId) {
        return ResponseEntity.ok(commentService.getAll(scheduleId));
    }
}
