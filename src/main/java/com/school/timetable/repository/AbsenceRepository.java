package com.school.timetable.repository;

import com.school.timetable.entity.Absence;
import com.school.timetable.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AbsenceRepository extends JpaRepository<Absence, Long> {

    List<Absence> findByStudent(Student student);

    Optional<Absence> findByIdAndStudent(Long id, Student student);

    // 👉 ADMIN
    List<Absence> findByJustifiedFalse();
}
