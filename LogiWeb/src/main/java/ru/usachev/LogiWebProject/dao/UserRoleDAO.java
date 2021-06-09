package ru.usachev.LogiWebProject.dao;

import ru.usachev.LogiWebProject.entity.UserRole;

public interface UserRoleDAO {
    UserRole getUserRoleByUsername(String username);
}
