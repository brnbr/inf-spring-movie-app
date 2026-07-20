package com.example.movieapp.user.service;

import com.example.movieapp.user.dto.CreateUserRequest;
import com.example.movieapp.user.dto.CreateUserResponse;
import com.example.movieapp.user.dto.GetUserResponse;
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

    //CREATE
    @Transactional
    public CreateUserResponse create(CreateUserRequest request) {
        User user = new User(request.getUsername(), request.getEmail());
        User saved = userRepository.save(user);

        return new CreateUserResponse(
                saved.getId(),
                saved.getUsername(),
                saved.getEmail()
        );
    }

    //READ All
    @Transactional(readOnly = true)
    public List<GetUserResponse> getAllUsers() {
        List<User> user = userRepository.findAll();
        return user.stream().map( u -> new GetUserResponse(
                u.getId(),
                u.getUsername(),
                u.getEmail()
        )).toList();
    }
}
