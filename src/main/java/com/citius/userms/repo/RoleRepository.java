package com.citius.userms.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.citius.userms.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	Role findRoleByName(String name);

}
