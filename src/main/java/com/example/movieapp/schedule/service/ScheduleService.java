package com.example.movieapp.schedule.service;

import com.example.movieapp.exception.ScheduleNotFoundException;
import com.example.movieapp.exception.UserNotFoundException;
import com.example.movieapp.schedule.dto.*;
import com.example.movieapp.schedule.entity.Schedule;
import com.example.movieapp.schedule.repository.ScheduleRepository;
import com.example.movieapp.user.entity.User;
import com.example.movieapp.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public Page<GetScheduleResponse> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size > 0 ? size : 10,
                Sort.by(Sort.Direction.DESC, "modifiedAt"));

        Page<Schedule> schedules = scheduleRepository.findAll(pageable);

        return schedules.map(schedule -> new GetScheduleResponse(schedule));
    }

    @Transactional
    public CreateScheduleResponse create(Long userId, CreateScheduleRequest request) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("사용자가 없습니다.")
        );

        Schedule schedule = new Schedule(request.getTitle(), request.getContent(), user);
        Schedule saved = scheduleRepository.save(schedule);

        return new CreateScheduleResponse(schedule);
    }

    @Transactional(readOnly = true)
    public List<GetScheduleResponse> getAll(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("사용자가 없습니다.")
        );

        List<Schedule> schedules = scheduleRepository.findAllByUserId(userId);
        if (schedules.isEmpty()) {
            throw new ScheduleNotFoundException("일정이 없습니다.");
        }

        return schedules.stream().map( schedule -> new GetScheduleResponse(schedule)).toList();
    }

    @Transactional(readOnly = true)
    public GetScheduleResponse getOne(Long userId, Long scheduleId) {
        userRepository.findById(userId).orElseThrow(
            () -> new UserNotFoundException("사용자가 없습니다.")
        );

        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new ScheduleNotFoundException("일정이 없습니다.")
        );

        if (!schedule.getUser().getId().equals(userId)) {
            throw new ScheduleNotFoundException("일정이 없습니다.");
        }
        return new GetScheduleResponse(schedule);
    }

    @Transactional
    public UpdateScheduleResponse update(Long scheduleId, UpdateScheduleRequest request) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new ScheduleNotFoundException("일정이 없습니다.")
        );

        schedule.update(request.getTitle(), request.getContent());
        return new UpdateScheduleResponse(schedule);
    }

    @Transactional
    public void delete(Long scheduleId) {
        boolean exist = scheduleRepository.existsById(scheduleId);
        if (!exist) {
            throw new ScheduleNotFoundException("일정이 없습니다.");
        }

        scheduleRepository.deleteById(scheduleId);
    }
}
