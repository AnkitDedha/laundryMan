package com.laundryman.laundrymanager.service;

import com.laundryman.laundrymanager.model.User;

public interface UserService {
    User saveUser(User user);
    User findByUsername(String username);
    void deleteUser(Long userId);
    User updateUser(User user);
}
