package com.as.blog.paylods;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {

	private Integer categoryId;
	
	@NotBlank
	@Size(min = 3, message = "Min Size of Caregory Title is : 3")
	private String categoryTitle;
	
	@NotBlank
	@Size(min = 9, message = "Min Size of Caregory Description is : 9")
	private String categoryDescription;

}
