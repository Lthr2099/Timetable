package com.school.timetable.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.school.timetable.entity.Student;
import com.school.timetable.entity.Teacher;
import com.school.timetable.repository.StudentRepository;
import com.school.timetable.repository.TeacherRepository;

@Service
public class SchoolService {

    private final StudentRepository studentRepo;
    private final TeacherRepository teacherRepo;

    public SchoolService(StudentRepository studentRepo, TeacherRepository teacherRepo) {
        this.studentRepo = studentRepo;
        this.teacherRepo = teacherRepo;
    }

    // ✅ UTILISATION RÉELLE DES REPOS

    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    public List<Teacher> getAllTeachers() {
        return teacherRepo.findAll();
    }
}
