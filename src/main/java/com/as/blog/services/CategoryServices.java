package com.as.blog.services;

import java.util.List;

import com.as.blog.paylods.CategoryDto;

public interface CategoryServices {

	CategoryDto createCategory(CategoryDto categoryDto);

	CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);

	void deleteCategory(Integer categoryId);

	CategoryDto getCategory(Integer categoryId);

	List<CategoryDto> getAllcategory();

}
