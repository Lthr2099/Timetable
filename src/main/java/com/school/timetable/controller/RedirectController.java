package com.school.timetable.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RedirectController {

    @GetMapping("/redirect")
    public String redirectByRole(Authentication authentication) {

        for (GrantedAuthority authority : authentication.getAuthorities()) {

            String role = authority.getAuthority();

            if (role.equals("ROLE_ADMIN")) {
                return "redirect:/admin";
            }

            if (role.equals("ROLE_TEACHER")) {
                return "redirect:/teacher";
            }

            if (role.equals("ROLE_STUDENT")) {
                return "redirect:/student";
            }
        }

        return "redirect:/login?error";
    }
}
