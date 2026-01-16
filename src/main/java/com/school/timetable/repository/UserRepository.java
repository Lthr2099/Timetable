package com.school.timetable.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.school.timetable.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
