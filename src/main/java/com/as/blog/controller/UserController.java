package com.as.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.as.blog.paylods.ApiResponse;
import com.as.blog.paylods.UserDto;
import com.as.blog.services.UserServices;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/asb/user")
@Tag(name="UserController", description = "To perform Operation on Users")
public class UserController {

	@Autowired
	private UserServices userServices;
	
	@Operation(
			summary = "POST Operation on user",
			description = "It is used to create User"
	)
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
		UserDto createUserDto = this.userServices.createUser(userDto);
		return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
	}
	
	@Operation(
			summary = "PUT Operation on user",
			description = "It is used to Update User"
	)
	@PutMapping("{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Integer userId) {
		UserDto updatedUser = this.userServices.updateUser(userDto, userId);
		return ResponseEntity.ok(updatedUser);
	}
	
	
	@Operation(
			summary = "DELETE Operation on user",
			description = "It is used to Delete User(ONLY FOR ADMIN)"
	)
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId) {
		this.userServices.deleteUser(userId); 
		return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted Successfully", true), HttpStatus.OK);
	}
	
	@Operation(
			summary = "GET Operation on user",
			description = "It is used to Get-All User"
	)
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUser() {
		return ResponseEntity.ok(this.userServices.getAllUser());
	}
	
	@Operation(
			summary = "GET Operation on user",
			description = "It is used to Get-Single User by Id"
	)
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId) {
		return ResponseEntity.ok(this.userServices.gerUserById(userId));
	}

}
