package com.school.timetable.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.school.timetable.entity.Teacher;
import com.school.timetable.entity.User;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    
    Optional<Teacher> findByUser(User user);
}
