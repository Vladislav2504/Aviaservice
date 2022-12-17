package com.example.aviaservice.service;

import com.example.aviaservice.dto.AuthDto;
import com.example.aviaservice.entity.Flight;
import com.example.aviaservice.entity.Role;
import com.example.aviaservice.entity.User;
import com.example.aviaservice.exception.UserNotFoundException;
import com.example.aviaservice.repository.UserRepository;
import com.example.aviaservice.service.maper.UserMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        return null;
    }

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public boolean exists(String email, String password) throws UsernameNotFoundException {
        Optional<User> byEmail = userRepository.findByEmail(email);
        if (byEmail.isPresent()) {
            User user = byEmail.get();
            return password.equals(user.getPassword());
        } else {
            return false;
        }
    }

    public boolean emailExists (String email){
        Optional<User> userFromDB = userRepository.findByEmail(email);
        if (userFromDB != null) {
            return false;
        }
        return true;
    }

    public void registration(User user) {
        List<Role> roles = new ArrayList<>();
        Role role = new Role();
        role.setRole("USER");
        roles.add(role);
        user.setRoleList(roles);

        user.setPassword(user.getPassword());
        user.setRoleList(roles);
        role.setUser(user);
        userRepository.save(user);
    }

    public Optional<User> findUserById(Long id) {
        Optional<User> userById = userRepository.findById(id);
        if (userById.isPresent()) {
            return userById;
        } else {
            throw new UserNotFoundException("User not found");
        }
    }

    public void save(User user) {
        userRepository.save(user);
    }

}
