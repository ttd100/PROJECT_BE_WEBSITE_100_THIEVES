package soixam.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soixam.model.User;
import soixam.repository.IUserRepository;
import soixam.service.IGeneric;
import soixam.service.IUser;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceIMPL implements IUser {
    @Autowired
    private IUserRepository userRepository;


    @Override
    public Optional<User> findByUsername(String name) {
        return userRepository.findByUsername(name);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}
