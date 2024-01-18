package com.as.blog.services;

import java.util.List;

import com.as.blog.Model.Post;
import com.as.blog.paylods.PostDto;
import com.as.blog.paylods.PostResponse;

public interface PostServices {

	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

	PostDto updatePost(PostDto postDto, Integer postId);

	void deletePost(Integer postId);

	PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

	PostDto getSingelPost(Integer postId);

	List<PostDto> getPostsByCategory(Integer categoryId);

	List<PostDto> getPostsByUser(Integer userId);

	// Search by keyword
	List<PostDto> searchPosts(String keyword);
}
