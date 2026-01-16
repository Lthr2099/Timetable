package com.school.timetable.repository;

import com.school.timetable.entity.Student;
import com.school.timetable.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByUserUsername(String username);
    
    Optional<Student> findByUser(User user);
}
