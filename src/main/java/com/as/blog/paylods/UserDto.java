package com.as.blog.paylods;

import java.util.HashSet;
import java.util.Set;

import com.as.blog.Model.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	
	private int id;
	
	@NotEmpty
	@Size(min = 4,message = "Name Should Be Min Of 4 Charaters!!" )
	private String name;
	
	@Email(message = "Email Address is Not Vaild!!")
	private String email;
	
	@NotEmpty
	@Size(min = 6,max = 10,message = "Pasword Must Be Min 4 Char and Max of 10 Char!!")
	private String password;
	
	@NotEmpty
	private String about;
	
	private Set<RoleDto> roles = new HashSet<>();

}
