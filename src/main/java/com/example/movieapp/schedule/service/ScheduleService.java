package com.example.movieapp.schedule.service;

import com.example.movieapp.exception.UserNotFoundException;
import com.example.movieapp.schedule.dto.CreateScheduleRequest;
import com.example.movieapp.schedule.dto.CreateScheduleResponse;
import com.example.movieapp.schedule.dto.GetScheduleResponse;
import com.example.movieapp.schedule.entity.Schedule;
import com.example.movieapp.schedule.repository.ScheduleRepository;
import com.example.movieapp.user.entity.User;
import com.example.movieapp.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    //CREATE
    @Transactional
    public CreateScheduleResponse create(Long userId, CreateScheduleRequest request) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("없는 유저입니다.")
        );

        Schedule schedule = new Schedule(request.getUsername(), request.getTitle(), request.getContent(), user);
        Schedule saved = scheduleRepository.save(schedule);

        return new CreateScheduleResponse(
                saved.getId(),
                saved.getUsername(),
                saved.getTitle(),
                saved.getContent(),
                saved.getCreatedAt()
        );
    }

    //READ All
    @Transactional(readOnly = true)
    public List<GetScheduleResponse> getAll(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("없는 유저입니다.")
        );

        List<Schedule> schedules = scheduleRepository.findAll(user.getId());
        if (schedules.isEmpty()) {
            throw new ScheduleNotFoundException("일정이 없습니다.");
        }

        return schedules.stream().map( schedule -> new GetScheduleResponse(
                schedule.getId(),
                schedule.getUsername(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        )).toList();
    }
}
