package com.as.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.as.blog.Model.Category;
import com.as.blog.Model.Post;
import com.as.blog.Model.User;

public interface PostRepo extends JpaRepository<Post, Integer> {
	
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	
	List<Post> findByTitleContaining(String keyword);

}
