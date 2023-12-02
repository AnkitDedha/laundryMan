package com.laundryman.laundrymanager.repository;

import com.laundryman.laundrymanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
