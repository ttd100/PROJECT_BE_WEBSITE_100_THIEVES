package soixam.service;

import soixam.model.Role;
import soixam.model.RoleName;

import java.util.Optional;

public interface IRole {
    Optional<Role> findByName(RoleName name);
}
