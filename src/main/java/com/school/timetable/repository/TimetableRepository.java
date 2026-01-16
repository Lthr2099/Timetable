package com.school.timetable.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.school.timetable.entity.Timetable;

public interface TimetableRepository extends JpaRepository<Timetable, Long> {
}
