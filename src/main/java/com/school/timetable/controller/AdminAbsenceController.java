package com.school.timetable.controller;

import com.school.timetable.entity.Absence;
import com.school.timetable.repository.AbsenceRepository;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class AdminAbsenceController {

    private final AbsenceRepository absenceRepository;

    public AdminAbsenceController(AbsenceRepository absenceRepository) {
        this.absenceRepository = absenceRepository;
    }

    @GetMapping("/admin/absences")
    public String listAbsences(Model model) {
        model.addAttribute("absences", absenceRepository.findAll());
        return "admin-absences";
    }

    @GetMapping("/admin/absence/{id}/details")
    public String viewAbsenceDetails(@PathVariable Long id, Model model) {
        Absence absence = absenceRepository.findById(id).orElseThrow();
        model.addAttribute("absence", absence);
        return "absence-details";
    }

    @GetMapping("/admin/absence/validate/{id}")
    public String validateAbsence(@PathVariable Long id) {
        Absence absence = absenceRepository.findById(id).orElseThrow();

        // Cannot validate if already rejected
        if (!absence.isRejected()) {
            absence.setValidated(true);
            absenceRepository.save(absence);
        }

        return "redirect:/admin/absences";
    }

    @GetMapping("/admin/absence/reject/{id}")
    public String rejectAbsence(@PathVariable Long id) {
        Absence absence = absenceRepository.findById(id).orElseThrow();

        absence.setRejected(true);
        absence.setValidated(false); // Cannot be both rejected and validated
        absence.setRejectionReason("Justification not acceptable"); // Default reason

        absenceRepository.save(absence);
        return "redirect:/admin/absences";
    }

    @GetMapping("/admin/absence/download/{id}")
    public ResponseEntity<Resource> downloadDocument(@PathVariable Long id) {
        Absence absence = absenceRepository.findById(id).orElseThrow();

        if (absence.getDocumentPath() == null) {
            return ResponseEntity.notFound().build();
        }

        try {
            Path filePath = Paths.get(absence.getDocumentPath());
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() && resource.isReadable()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION,
                                "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
