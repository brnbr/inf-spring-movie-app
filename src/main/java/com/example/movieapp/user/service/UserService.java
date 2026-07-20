package com.example.movieapp.user.service;

import com.example.movieapp.exception.InvalidPasswordException;
import com.example.movieapp.exception.UserNotFoundException;
import com.example.movieapp.user.dto.*;
import com.example.movieapp.user.entity.User;
import com.example.movieapp.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public CreateUserResponse create(CreateUserRequest request) {
        User user = new User(request.getUsername(), request.getEmail(), request.getPassword());
        User saved = userRepository.save(user);

        return new CreateUserResponse(
                saved.getId(),
                saved.getUsername(),
                saved.getEmail(),
                saved.getCreatedAt()
        );
    }

    @Transactional(readOnly = true)
    public List<GetUserResponse> getAll() {
        List<User> user = userRepository.findAll();
        return user.stream().map( u -> new GetUserResponse(
                u.getId(),
                u.getUsername(),
                u.getEmail(),
                u.getCreatedAt(),
                u.getModifiedAt()
        )).toList();
    }

    @Transactional(readOnly = true)
    public GetUserResponse getOne(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("없는 유저입니다.")
        );

        return new GetUserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getModifiedAt()
        );
    }

    @Transactional
    public UpdateUserResponse update(Long userId, UpdateUserReqeust reqeust, Integer password) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("없는 유저입니다.")
        );

        if (!user.getPassword().equals(password)) {
            throw new InvalidPasswordException("비밀번호가 틀렸습니다.");
        }

        user.update(reqeust.getUsername(), reqeust.getEmail());
        return new UpdateUserResponse(user.getId(), user.getUsername(), user.getEmail(), user.getCreatedAt(), user.getModifiedAt());
    }

    @Transactional
    public void delete(Long userId, Integer password) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("없는 유저입니다.")
        );

        if (!user.getPassword().equals(password)) {
            throw new InvalidPasswordException("비밀번호가 틀렸습니다.");
        }

        userRepository.deleteById(userId);
    }
}
