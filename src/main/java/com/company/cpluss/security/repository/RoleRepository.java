package com.company.cpluss.security.repository;

import com.company.cpluss.security.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
