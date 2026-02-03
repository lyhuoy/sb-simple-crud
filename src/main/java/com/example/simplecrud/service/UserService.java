package com.example.simplecrud.service;

import com.example.simplecrud.dto.UserRequest;
import com.example.simplecrud.dto.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse create(UserRequest request);
    UserResponse findById(Long id);
    List<UserResponse> getAll();
    UserResponse update(Long id, UserRequest request);
    void delete(Long id);
}
