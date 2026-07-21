package com.example.movieapp.comment.service;

import com.example.movieapp.comment.dto.CreateCommentRequest;
import com.example.movieapp.comment.dto.CreateCommentResponse;
import com.example.movieapp.comment.dto.GetCommnetResponse;
import com.example.movieapp.comment.entity.Comment;
import com.example.movieapp.comment.respository.CommentRepository;
import com.example.movieapp.exception.CommentNotFoundException;
import com.example.movieapp.exception.ScheduleNotFoundException;
import com.example.movieapp.schedule.entity.Schedule;
import com.example.movieapp.schedule.repository.ScheduleRepository;
import com.example.movieapp.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    @Transactional
    public CreateCommentResponse create(Long scheduleId, CreateCommentRequest request) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new ScheduleNotFoundException("일정이 없습니다.")
        );

        Comment comment = new Comment(request.getContent(), schedule);
        Comment saved = commentRepository.save(comment);

        return new CreateCommentResponse(saved.getId(), saved.getContent(), saved.getSchedule().getCreatedAt());
    }

    @Transactional(readOnly = true)
    public List<GetCommnetResponse> getAll(Long scheduleId) {
        List<Comment> comments = commentRepository.findAllByScheduleId(scheduleId);
        if (comments.isEmpty()) {
            throw new CommentNotFoundException("댓글이 없습니다.");
        }

        return comments.stream()
                .map(comment -> new GetCommnetResponse(comment.getId(), comment.getContent(),
                        comment.getSchedule().getCreatedAt()))
                .toList();
    }
}
