package com.citius.userms.service;

import com.citius.userms.entity.Role;

public interface RoleService {
	Role findByName(String name);
}
