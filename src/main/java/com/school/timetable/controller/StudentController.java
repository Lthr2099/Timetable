package com.school.timetable.controller;

import com.school.timetable.entity.Absence;
import com.school.timetable.entity.Student;
import com.school.timetable.repository.AbsenceRepository;
import com.school.timetable.repository.StudentRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
@RequestMapping("/student/absence")
public class StudentController {

        private final StudentRepository studentRepo;
        private final AbsenceRepository absenceRepo;

        public StudentController(StudentRepository studentRepo,
                        AbsenceRepository absenceRepo) {
                this.studentRepo = studentRepo;
                this.absenceRepo = absenceRepo;
        }

        // ============================
        // GET — Afficher la justification
        // ============================
        @GetMapping("/{id}/justify")
        public String showJustifyForm(@PathVariable Long id,
                        Authentication auth,
                        Model model) {

                String username = auth.getName();

                Student student = studentRepo.findByUserUsername(username)
                                .orElseThrow(() -> new RuntimeException("Student not found"));

                Absence absence = absenceRepo.findByIdAndStudent(id, student)
                                .orElseThrow(() -> new RuntimeException(
                                                "Access denied: absence does not belong to this student"));

                model.addAttribute("absence", absence);
                return "justify-absence";
        }

        // ============================
        // POST — Confirmer justification avec texte et document
        // ============================
        @PostMapping("/{id}/justify")
        public String justifyAbsence(@PathVariable Long id,
                        @RequestParam("justificationText") String justificationText,
                        @RequestParam(value = "document", required = false) MultipartFile document,
                        Authentication auth) {

                String username = auth.getName();

                Student student = studentRepo.findByUserUsername(username)
                                .orElseThrow(() -> new RuntimeException("Student not found"));

                Absence absence = absenceRepo.findByIdAndStudent(id, student)
                                .orElseThrow(() -> new RuntimeException(
                                                "Access denied: absence does not belong to this student"));

                // Set justification text
                absence.setJustificationText(justificationText);
                absence.setJustified(true);

                // Handle document upload if provided
                if (document != null && !document.isEmpty()) {
                        try {
                                // Create uploads directory if it doesn't exist
                                File uploadsDir = new File("uploads");
                                if (!uploadsDir.exists()) {
                                        uploadsDir.mkdirs();
                                }

                                // Generate unique filename
                                String originalFilename = document.getOriginalFilename();
                                if (originalFilename == null || originalFilename.isEmpty()) {
                                        originalFilename = "document.pdf";
                                }
                                String extension = originalFilename.contains(".")
                                                ? originalFilename.substring(originalFilename.lastIndexOf("."))
                                                : ".pdf";
                                String filename = UUID.randomUUID().toString() + extension;

                                // Save file
                                Path filepath = Paths.get("uploads", filename);
                                Files.write(filepath, document.getBytes());

                                // Store relative path in database
                                absence.setDocumentPath("uploads/" + filename);

                        } catch (IOException e) {
                                throw new RuntimeException("Failed to store document: " + e.getMessage());
                        }
                }

                absenceRepo.save(absence);

                return "redirect:/student";
        }
}
