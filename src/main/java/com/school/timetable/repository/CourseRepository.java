package com.school.timetable.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.school.timetable.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
