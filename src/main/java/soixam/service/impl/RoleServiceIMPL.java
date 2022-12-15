package soixam.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soixam.model.Role;
import soixam.model.RoleName;
import soixam.repository.IRoleRepository;
import soixam.service.IRole;

import java.util.Optional;
@Service
public class RoleServiceIMPL implements IRole {
    @Autowired
    private IRoleRepository roleRepository;
    @Override
    public Optional<Role> findByName(RoleName name) {
        return roleRepository.findByName(name);
    }
}
