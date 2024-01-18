package com.as.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.as.blog.Model.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
