package com.as.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.as.blog.Model.Role;

public interface RoleRepo extends JpaRepository<Role, Integer> {

}
