package com.as.blog.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.as.blog.Model.Post;
import com.as.blog.config.AppConstant;
import com.as.blog.paylods.ApiResponse;
import com.as.blog.paylods.PostDto;
import com.as.blog.paylods.PostResponse;
import com.as.blog.services.FileService;
import com.as.blog.services.PostServices;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/asb/")
@Tag(name="PostController",description = "To perform operation on Posts")
public class Postcontroller {

	@Autowired
	private PostServices postServices;

	@Autowired
	private FileService fileService;

	@Value("${project.image}")
	private String path;
	
	@Operation(
			summary = "POST operation on Posts",
			description = "It is use to Create Post"
	)
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId,
			@PathVariable Integer categoryId) {
		PostDto createPost = this.postServices.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);
	}

	// get by user
	@Operation(
			summary = "GET operation on Posts",
			description = "It is use to Get Post by user"
	)
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId) {
		List<PostDto> posts = this.postServices.getPostsByUser(userId);
		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
	}

	// get by category
	@Operation(
			summary = "GET operation on Posts",
			description = "It is use to Get Post by Category"
	)
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId) {
		List<PostDto> posts = this.postServices.getPostsByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
	}
	
	@Operation(
			summary = "GET operation on Posts",
			description = "It is use to Get All Posts"
	)
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value = "pageNumber", defaultValue = AppConstant.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstant.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstant.SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstant.SORT_DIR, required = false) String sortDir) {
		PostResponse postResponse = this.postServices.getAllPosts(pageNumber, pageSize, sortBy, sortDir);
		return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
	}
	
	@Operation(
			summary = "GET operation on Posts",
			description = "It is use to Get Posts by Post-Id"
	)
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId) {

		PostDto postDto = this.postServices.getSingelPost(postId);
		return new ResponseEntity<PostDto>(postDto, HttpStatus.OK);
	}
	
	@Operation(
			summary = "DELETE operation on Posts",
			description = "It is use to delete Post by Post-Id"
	)
	@DeleteMapping("/posts/{postId}")
	public ApiResponse deletePost(@PathVariable Integer postId) {
		this.postServices.deletePost(postId);
		return new ApiResponse("Post is Successfully Deleted!!", true);
	}
	
	@Operation(
			summary = "PUT operation on Posts",
			description = "It is use to Update Posts by Post-Id"
	)
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId) {
		PostDto updatePost = this.postServices.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);
	}

	// get by search
	@Operation(
			summary = "GET operation on Posts",
			description = "It is use to Search Posts by keyword"
	)
	@GetMapping("/posts/search/{keyword}")
	public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keyword") String keyword) {
		List<PostDto> result = this.postServices.searchPosts(keyword);

		return new ResponseEntity<List<PostDto>>(result, HttpStatus.OK);

	}
	
	@Operation(
			summary = "Post operation on Posts",
			description = "It is use to upload Image by Post-Id"
	)
	// image upload in post
	@PostMapping("/posts/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadImagePost(@RequestParam("image") MultipartFile image,
			@PathVariable Integer postId) throws IOException {
		PostDto postDto = this.postServices.getSingelPost(postId);
		String fileName = this.fileService.uploadImage(path, image);

		postDto.setImageName(fileName);
		PostDto updatePost = this.postServices.updatePost(postDto, postId);

		return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/posts/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable("imageName") String imageName, HttpServletResponse response)
			throws IOException {
		InputStream resource = this.fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
	}

}
