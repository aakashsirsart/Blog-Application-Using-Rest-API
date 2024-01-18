package com.as.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.as.blog.paylods.ApiResponse;
import com.as.blog.paylods.CategoryDto;
import com.as.blog.services.CategoryServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/asb/categories")
public class CategoryController {
	
	@Autowired
	private CategoryServices categoryServices;

	@PostMapping("/")
	public ResponseEntity<CategoryDto> creatCategory(@Valid @RequestBody CategoryDto categoryDto) {
		CategoryDto createCategory = this.categoryServices.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(createCategory, HttpStatus.CREATED);
	}

	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,
			@PathVariable Integer catId) {
		CategoryDto updatedCategory = this.categoryServices.updateCategory(categoryDto, catId);
		return new ResponseEntity<CategoryDto>(updatedCategory, HttpStatus.OK);
	}

	@DeleteMapping("/{catId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer catId) {
		this.categoryServices.deleteCategory(catId);

		return new ResponseEntity<ApiResponse>(new ApiResponse("Catory is deleted Successfully", true), HttpStatus.OK);

	}

	@GetMapping("/{catId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer catId) {
		CategoryDto categoryDto = this.categoryServices.getCategory(catId);

		return new ResponseEntity<CategoryDto>(categoryDto, HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity <List<CategoryDto>> getAllCategory() {
		List<CategoryDto> categories = this.categoryServices.getAllcategory();
		return ResponseEntity.ok(categories);
	}

}
