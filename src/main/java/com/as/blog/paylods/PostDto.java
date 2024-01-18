package com.as.blog.paylods;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.as.blog.Model.Comment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
	
	private String pId;
	
	private String title;

	private String content;

	private String imageName;

	private Date addedDate;
		
	private CategoryDto category;

	private UserDto userDto;
	
	private Set<CommentDto> comments = new HashSet<>();

}
