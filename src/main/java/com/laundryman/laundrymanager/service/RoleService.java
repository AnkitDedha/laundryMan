package com.laundryman.laundrymanager.service;

import com.laundryman.laundrymanager.model.Role;

public interface RoleService {
    Role saveRole(Role role);
    Role findByName(String name);
}
