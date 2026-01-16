package com.school.timetable.controller;

import com.school.timetable.entity.Student;
import com.school.timetable.repository.AbsenceRepository;
import com.school.timetable.repository.StudentRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    private final StudentRepository studentRepository;
    private final AbsenceRepository absenceRepository;

    public DashboardController(StudentRepository studentRepository,
            AbsenceRepository absenceRepository) {
        this.studentRepository = studentRepository;
        this.absenceRepository = absenceRepository;
    }

    @GetMapping("/admin")
    public String adminDashboard() {
        return "admin";
    }

    @GetMapping("/teacher")
    public String teacherDashboard() {
        return "teacher";
    }

    @GetMapping("/student")
    public String studentDashboard(Authentication auth, Model model) {
        String username = auth.getName();

        // Récupérer l'étudiant connecté
        Student student = studentRepository.findByUserUsername(username)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        // Charger toutes les absences de cet étudiant
        model.addAttribute("absences", absenceRepository.findByStudent(student));

        return "student";
    }
}
