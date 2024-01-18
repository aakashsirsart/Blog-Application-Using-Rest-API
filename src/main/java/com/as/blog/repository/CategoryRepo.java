package com.as.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.as.blog.Model.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
