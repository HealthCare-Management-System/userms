package com.citius.userms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.citius.userms.entity.User;
import com.model.UserDto;

@Service
public interface UserService {

	public UserDto create(UserDto e);

	public UserDto getUserByName(String name);

	public List<UserDto> fetchAll();

	public List<UserDto> fetchAllByRoles(String role);

	public UserDto update(int id, User e);

	public void delete(int id);

	public UserDto update(int id, String status);

	public UserDto findEmployee(int id);

	public UserDto convertEntityToDto(User us);

	public User convertDtoToEntity(UserDto us);

	public List<UserDto> fetchAllByRolesAndStatus(String role, String status);

	public List<UserDto> fetchCorporateUsersByStatus(String status);

}
