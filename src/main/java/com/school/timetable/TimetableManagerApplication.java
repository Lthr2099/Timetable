package com.school.timetable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.school.timetable")
public class TimetableManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TimetableManagerApplication.class, args);
    }
}
