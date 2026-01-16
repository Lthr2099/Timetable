package com.school.timetable.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.school.timetable.entity.Course;
import com.school.timetable.entity.Role;
import com.school.timetable.entity.Student;
import com.school.timetable.entity.Teacher;
import com.school.timetable.entity.User;
import com.school.timetable.repository.CourseRepository;
import com.school.timetable.repository.RoleRepository;
import com.school.timetable.repository.StudentRepository;
import com.school.timetable.repository.TeacherRepository;
import com.school.timetable.repository.UserRepository;

import java.util.Set;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initData(
            RoleRepository roleRepo,
            UserRepository userRepo,
            StudentRepository studentRepo,
            TeacherRepository teacherRepo,
            CourseRepository courseRepo,
            PasswordEncoder encoder) {

        return args -> {

            // 🔹 ROLES (créés UNE SEULE FOIS - idempotent)
            Role adminRole = roleRepo.findByName("ADMIN");
            if (adminRole == null) {
                adminRole = roleRepo.save(new Role("ADMIN"));
            }

            Role teacherRole = roleRepo.findByName("TEACHER");
            if (teacherRole == null) {
                teacherRole = roleRepo.save(new Role("TEACHER"));
            }

            Role studentRole = roleRepo.findByName("STUDENT");
            if (studentRole == null) {
                studentRole = roleRepo.save(new Role("STUDENT"));
            }

            // 🔹 ADMIN USER
            User admin = userRepo.findByUsername("admin1");
            if (admin == null) {
                admin = new User();
                admin.setUsername("admin1");
                admin.setPassword(encoder.encode("admin123"));
                admin.setRoles(Set.of(adminRole));
                userRepo.save(admin);
            }

            // 🔹 TEACHER USERS
            User teacherUser1 = userRepo.findByUsername("teacher1");
            if (teacherUser1 == null) {
                teacherUser1 = new User();
                teacherUser1.setUsername("teacher1");
                teacherUser1.setPassword(encoder.encode("teacher123"));
                teacherUser1.setRoles(Set.of(teacherRole));
                userRepo.save(teacherUser1);
            }

            User teacherUser2 = userRepo.findByUsername("teacher2");
            if (teacherUser2 == null) {
                teacherUser2 = new User();
                teacherUser2.setUsername("teacher2");
                teacherUser2.setPassword(encoder.encode("teacher123"));
                teacherUser2.setRoles(Set.of(teacherRole));
                userRepo.save(teacherUser2);
            }

            // 🔹 STUDENT USERS (6 étudiants)
            User studentUser1 = userRepo.findByUsername("student1");
            if (studentUser1 == null) {
                studentUser1 = new User();
                studentUser1.setUsername("student1");
                studentUser1.setPassword(encoder.encode("student123"));
                studentUser1.setRoles(Set.of(studentRole));
                userRepo.save(studentUser1);
            }

            User studentUser2 = userRepo.findByUsername("student2");
            if (studentUser2 == null) {
                studentUser2 = new User();
                studentUser2.setUsername("student2");
                studentUser2.setPassword(encoder.encode("student123"));
                studentUser2.setRoles(Set.of(studentRole));
                userRepo.save(studentUser2);
            }

            User studentUser3 = userRepo.findByUsername("student3");
            if (studentUser3 == null) {
                studentUser3 = new User();
                studentUser3.setUsername("student3");
                studentUser3.setPassword(encoder.encode("student123"));
                studentUser3.setRoles(Set.of(studentRole));
                userRepo.save(studentUser3);
            }

            User studentUser4 = userRepo.findByUsername("student4");
            if (studentUser4 == null) {
                studentUser4 = new User();
                studentUser4.setUsername("student4");
                studentUser4.setPassword(encoder.encode("student123"));
                studentUser4.setRoles(Set.of(studentRole));
                userRepo.save(studentUser4);
            }

            User studentUser5 = userRepo.findByUsername("student5");
            if (studentUser5 == null) {
                studentUser5 = new User();
                studentUser5.setUsername("student5");
                studentUser5.setPassword(encoder.encode("student123"));
                studentUser5.setRoles(Set.of(studentRole));
                userRepo.save(studentUser5);
            }

            User studentUser6 = userRepo.findByUsername("student6");
            if (studentUser6 == null) {
                studentUser6 = new User();
                studentUser6.setUsername("student6");
                studentUser6.setPassword(encoder.encode("student123"));
                studentUser6.setRoles(Set.of(studentRole));
                userRepo.save(studentUser6);
            }

            // 🔹 CREATE TEACHER ENTITIES
            Teacher teacher1 = teacherRepo.findByUser(teacherUser1).orElse(null);
            if (teacher1 == null) {
                teacher1 = new Teacher();
                teacher1.setFirstName("Marie");
                teacher1.setLastName("Dupont");
                teacher1.setUser(teacherUser1);
                teacher1 = teacherRepo.save(teacher1);
            }

            Teacher teacher2 = teacherRepo.findByUser(teacherUser2).orElse(null);
            if (teacher2 == null) {
                teacher2 = new Teacher();
                teacher2.setFirstName("Pierre");
                teacher2.setLastName("Martin");
                teacher2.setUser(teacherUser2);
                teacher2 = teacherRepo.save(teacher2);
            }

            // 🔹 CREATE STUDENT ENTITIES
            Student student1 = studentRepo.findByUser(studentUser1).orElse(null);
            if (student1 == null) {
                student1 = new Student();
                student1.setUser(studentUser1);
            }
            student1.setFirstName("Jean");
            student1.setLastName("Dubois");
            studentRepo.save(student1);

            Student student2 = studentRepo.findByUser(studentUser2).orElse(null);
            if (student2 == null) {
                student2 = new Student();
                student2.setFirstName("Sophie");
                student2.setLastName("Leroy");
                student2.setUser(studentUser2);
                studentRepo.save(student2);
            }

            Student student3 = studentRepo.findByUser(studentUser3).orElse(null);
            if (student3 == null) {
                student3 = new Student();
                student3.setFirstName("Thomas");
                student3.setLastName("Bernard");
                student3.setUser(studentUser3);
                studentRepo.save(student3);
            }

            Student student4 = studentRepo.findByUser(studentUser4).orElse(null);
            if (student4 == null) {
                student4 = new Student();
                student4.setFirstName("Emma");
                student4.setLastName("Petit");
                student4.setUser(studentUser4);
                studentRepo.save(student4);
            }

            Student student5 = studentRepo.findByUser(studentUser5).orElse(null);
            if (student5 == null) {
                student5 = new Student();
                student5.setFirstName("Lucas");
                student5.setLastName("Robert");
                student5.setUser(studentUser5);
                studentRepo.save(student5);
            }

            Student student6 = studentRepo.findByUser(studentUser6).orElse(null);
            if (student6 == null) {
                student6 = new Student();
                student6.setFirstName("Léa");
                student6.setLastName("Moreau");
                student6.setUser(studentUser6);
                studentRepo.save(student6);
            }

            // 🔹 CREATE COURSES (Matières)
            if (courseRepo.count() == 0) {
                Course math = new Course();
                math.setName("Mathématiques");
                math.setTeacher(teacher1);
                courseRepo.save(math);

                Course physics = new Course();
                physics.setName("Physique");
                physics.setTeacher(teacher1);
                courseRepo.save(physics);

                Course chemistry = new Course();
                chemistry.setName("Chimie");
                chemistry.setTeacher(teacher2);
                courseRepo.save(chemistry);

                Course history = new Course();
                history.setName("Histoire");
                history.setTeacher(teacher2);
                courseRepo.save(history);

                Course french = new Course();
                french.setName("Français");
                french.setTeacher(teacher1);
                courseRepo.save(french);

                System.out.println("✅ 5 courses créés avec succès !");
            }

            System.out.println("✅ Initialisation complète : 6 étudiants, 2 professeurs, 5 matières");
        };
    }
}
