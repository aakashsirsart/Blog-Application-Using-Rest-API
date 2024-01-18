package com.as.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.as.blog.Model.Comment;
import com.as.blog.Model.Post;
import com.as.blog.exceptions.ResourceNorFoundException;
import com.as.blog.paylods.CommentDto;
import com.as.blog.repository.CommentRepo;
import com.as.blog.repository.PostRepo;
import com.as.blog.services.CommentServices;

@Service
public class CommentServicesImpl implements CommentServices {

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNorFoundException("Post", "post id", postId));
		Comment comment = this.modelMapper.map(commentDto, Comment.class);

		comment.setPost(post);
		Comment saveComment = this.commentRepo.save(comment);

		return this.modelMapper.map(saveComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		Comment comments = this.commentRepo.findById(commentId)
				.orElseThrow(() -> new ResourceNorFoundException("Comment", "comment id", commentId));
		this.commentRepo.delete(comments);
	}

}
