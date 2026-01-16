package com.school.timetable.controller;

import com.school.timetable.entity.Absence;
import com.school.timetable.entity.Course;
import com.school.timetable.entity.Student;
import com.school.timetable.repository.AbsenceRepository;
import com.school.timetable.repository.CourseRepository;
import com.school.timetable.repository.StudentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/teacher/absence")
public class AbsenceController {

    private final AbsenceRepository absenceRepo;
    private final StudentRepository studentRepo;
    private final CourseRepository courseRepo;

    public AbsenceController(
            AbsenceRepository absenceRepo,
            StudentRepository studentRepo,
            CourseRepository courseRepo) {
        this.absenceRepo = absenceRepo;
        this.studentRepo = studentRepo;
        this.courseRepo = courseRepo;
    }

    // 🔹 FORMULAIRE
    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("students", studentRepo.findAll());
        model.addAttribute("courses", courseRepo.findAll());
        return "absence-form";
    }

    // 🔹 SAUVEGARDE CORRECTE
    @PostMapping("/save")
    public String saveAbsence(
            @RequestParam("studentId") Long studentId,
            @RequestParam("courseId") Long courseId,
            @RequestParam("date") LocalDate date) {
        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Absence absence = new Absence();
        absence.setStudent(student);
        absence.setCourse(course);
        absence.setDate(date);
        absence.setJustified(false);
        absence.setValidated(false);

        absenceRepo.save(absence);

        return "redirect:/teacher";
    }
}
