package soixam.service;

import soixam.model.User;

import java.util.Optional;

public interface IUser {
    Optional<User> findByUsername(String name);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    User save(User user);
}
