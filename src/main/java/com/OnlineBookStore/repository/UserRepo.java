package com.OnlineBookStore.repository;

import com.OnlineBookStore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface UserRepo extends JpaRepository<User,Long> {
    User findByEmail(String email);
}
