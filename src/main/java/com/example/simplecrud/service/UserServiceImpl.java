package com.example.simplecrud.service;

import com.example.simplecrud.dto.UserRequest;
import com.example.simplecrud.dto.UserResponse;
import com.example.simplecrud.entity.User;
import com.example.simplecrud.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserResponse create(UserRequest request) {
        if(userRepository.existsByEmail(request.email())) {
            throw new RuntimeException("Email already exists");
        }

        User user = User.builder()
                .username(request.username())
                .email(request.email())
                .build();

        return map(userRepository.save(user));
    }

    @Override
    public UserResponse findById(Long id) {
        return userRepository.findById(id)
                .map(this::map)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public List<UserResponse> getAll() {
        return userRepository.findAll()
                .stream()
                .map(this::map)
                .toList();
    }

    public UserResponse update(Long id, UserRequest userRequest) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setUsername(userRequest.username());
        user.setEmail(userRequest.email());

        return map(userRepository.save(user));
    }

    @Override
    public void delete(Long id) {
        if(!userRepository.existsById(id)) throw new RuntimeException("User not found");

        userRepository.deleteById(id);
    }

    private UserResponse map(User user) {
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail()
        );
    }
}
