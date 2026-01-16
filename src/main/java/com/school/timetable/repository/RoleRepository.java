package com.school.timetable.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.school.timetable.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
