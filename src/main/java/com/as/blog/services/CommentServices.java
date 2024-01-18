package com.as.blog.services;

import com.as.blog.paylods.CommentDto;

public interface CommentServices {
	
	CommentDto createComment(CommentDto commentDto ,Integer postId);
	
	void deleteComment(Integer commentId);

}
