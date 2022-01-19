package com.citius.userms.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citius.userms.entity.User;
import com.citius.userms.service.UserService;
import com.model.UserDto;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping("/users")
public class UserController {
	private UserService userService;

	
	@Autowired
	public UserController(UserService employeeService) {
		super();
		this.userService = employeeService;
	}

	@CrossOrigin
	@ApiOperation(value = "Search All Users", notes = "default method for searching Users")
	@ApiResponses({ @ApiResponse(code = 200, message = "Fetched All Users", response = User.class) })
	@GetMapping
	public List<UserDto> allEmployees() {
		return userService.fetchAll();
	}

	@CrossOrigin
	@ApiOperation(value = "Search All Users By Role", notes = "default method for searching Users")
	@ApiResponses({ @ApiResponse(code = 200, message = "Fetched All Users By Users", response = User.class) })
	@GetMapping("/role/{role}")
	public List<String> allUserByRoles(@PathVariable String role) {
		System.out.println("Inside Users By Role *******");
		List<UserDto> users = userService.fetchAllByRoles(role);
		List<String> names = new ArrayList<String>();
		for (UserDto us : users) {
			names.add(us.getName());
		}
		return names;
	}

	
//	@ApiOperation(value = "Search Users By Email", notes = "default method for searching User")
//	@ApiResponses({ @ApiResponse(code = 200, message = "Fetched All Users", response = User.class) })
	@CrossOrigin
	@GetMapping("/{userName}")
	public UserDto getUserByEmail(@PathVariable String userName) {
		System.out.println("Inside USER MS FOR EMAIL");
		return userService.getUserByName(userName);
	}

	@CrossOrigin
	@PatchMapping("/{id}/{status}")
	public UserDto updateStatusOfUser(@PathVariable int id, @PathVariable String status) {
		return userService.update(id, status);
	}

//	@PostMapping
//	public User createEmployee(@Valid @RequestBody User e) {
//		return userService.create(e);
//	}

	@CrossOrigin
	@GetMapping("/users/{userId}")
	public UserDto getById(@PathVariable("userId") int userId) {

		UserDto us = userService.findEmployee(userId);

		return us;
	}

//	@CrossOrigin
//	@RequestMapping(value = "/signup", 
//    consumes = MediaType.APPLICATION_JSON_VALUE, 
//    produces = MediaType.APPLICATION_JSON_VALUE,
//    method = {RequestMethod.POST})
//	public ResponseEntity<UserDto> createEmployee(@RequestBody UserDto user) {		
//		return new ResponseEntity<>(userService.create(user), HttpStatus.OK);
//	}
//
	@CrossOrigin
	@PostMapping("/signup")
	public ResponseEntity<UserDto> createUser( @RequestBody UserDto user)  {
		UserDto newUser = userService.create(user);
		return new ResponseEntity<>(newUser, HttpStatus.OK);
	}
	
	@CrossOrigin
	@GetMapping("/ping")
	public ResponseEntity<String>  greeting() {
		return new ResponseEntity<>("Application Up!!!", HttpStatus.OK);
	}

//
//	@GetMapping("/users/{userName}")
//	public User getById(@PathVariable("useName") String name) {
//		return userRepo.findByName(name);
//	}
}
