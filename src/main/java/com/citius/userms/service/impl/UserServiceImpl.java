package com.citius.userms.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citius.userms.entity.Role;
import com.citius.userms.entity.User;
import com.citius.userms.repo.UserRepository;
import com.citius.userms.service.RoleService;
import com.citius.userms.service.UserService;
import com.model.UserDto;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository repo;

	@Autowired
	private RoleService roleService;

	@Override
	public UserDto create(UserDto e) {

		User nUser = convertDtoToEntity(e);

		return convertEntityToDto(repo.save(nUser));

	}

	@Override
	public List<UserDto> fetchAll() {

		List<User> users = repo.findAll();
		List<UserDto> userdtos = new ArrayList<>();
		for (User us : users) {
			UserDto dto = new UserDto();
			dto.setId(us.getId());
			dto.setDob(us.getDob());
			dto.setEmail(us.getEmail());
			dto.setEmpid(us.getEmpid());
			dto.setLname(us.getLname());
			dto.setName(us.getName());
			dto.setPassword((us.getPassword()));
			dto.setPhone(us.getPhone());
			dto.setStatus(us.getStatus());
			dto.setDoj(us.getDoj());
			for (Role role : us.getRoles()) {
				if (null == dto.getRole()) {
					dto.setRole(role.getName());
				}

			}
			dto.setTitle(us.getTitle());

			userdtos.add(dto);
		}

		return userdtos;
	}

	@Override
	public UserDto update(int userid, String status) {

		User user = repo.getById(userid);

		user.setStatus(status);
		User us = repo.save(user);
		UserDto dto = convertEntityToDto(us);
		return dto;

	}

	@Override
	public void delete(int id) {
		User e = repo.getById(id);
		repo.delete(e);
	}

	@Override
	public UserDto findEmployee(int id) {
		return convertEntityToDto(repo.findById(id).get());

	}

	@Override
	public UserDto update(int id, User e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDto getUserByName(String name) {
		User us = repo.findByEmail(name);
		UserDto dto = convertEntityToDto(us);
		return dto;
	}

	public UserDto convertEntityToDto(User us) {
		UserDto dto = new UserDto();
		dto.setId(us.getId());
		dto.setDob(us.getDob());
		dto.setEmail(us.getEmail());
		dto.setEmpid(us.getEmpid());
		dto.setLname(us.getLname());
		dto.setName(us.getName());
		dto.setPassword((us.getPassword()));
		dto.setPhone(us.getPhone());
		dto.setStatus(us.getStatus());
		dto.setDoj(us.getDoj());
		for (Role role : us.getRoles()) {
			if (null == dto.getRole()) {
				dto.setRole(role.getName());
			}
		}
		dto.setTitle(us.getTitle());
		return dto;
	}

	public User convertDtoToEntity(UserDto us) {
		User user = new User();
		user.setName(us.getName());
		user.setPassword(us.getPassword());
		user.setEmail(us.getEmail());
		user.setPhone(us.getPhone());
		user.setDob(us.getDob());
		user.setEmpid(us.getEmpid());
		user.setLname(us.getLname());
		user.setStatus(us.getStatus());
		user.setTitle(us.getTitle());
		user.setDoj(us.getDoj());
		if (null == us.getDoj()) {
			user.setDoj(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")).toString());
		}
		Role roleobj = roleService.findByName(us.getRole().toUpperCase());
		Set<Role> roleSet = new HashSet<>();
		roleSet.add(roleobj);
		user.setRoles(roleSet);
		return user;
	}

	@Override
	public List<UserDto> fetchAllByRoles(String role) {
		List<User> users = repo.findAllByRolesName(role);
		List<UserDto> dtos = new ArrayList<>();
		for (User us : users) {
			dtos.add(convertEntityToDto(us));
		}

		return dtos;
	}

	@Override
	public List<User> fetchAllByRolesAndStatus(String role, String status) {
		List<User> users = repo.findAllByRolesName(role);
		List userList = users.stream().filter(p -> p.getStatus().equalsIgnoreCase(status)).collect(Collectors.toList());
		return userList;
	}

	@Override
	public List<User> fetchCorporateUsersByStatus(String status) {
		List<User> nurseUsers = repo.findAllByRolesName("CT_NURSE");
		List userList1 = nurseUsers.stream().filter(p -> p.getStatus().equalsIgnoreCase(status))
				.collect(Collectors.toList());
		List<User> physicianUsers = repo.findAllByRolesName("CT_PHYSICIAN");
		List userList2 = physicianUsers.stream().filter(p -> p.getStatus().equalsIgnoreCase(status))
				.collect(Collectors.toList());
		userList2.addAll(userList1);
		return userList2;
	}

}
