package com.microsoft.tutorial.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microsoft.tutorial.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{

}
