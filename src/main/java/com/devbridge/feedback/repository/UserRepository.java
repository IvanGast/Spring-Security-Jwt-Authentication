package com.devbridge.feedback.repository;

import com.devbridge.feedback.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

public interface UserRepository extends Repository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.username = :username")
    User findByUsername(String username);

    User save(User user);
}
