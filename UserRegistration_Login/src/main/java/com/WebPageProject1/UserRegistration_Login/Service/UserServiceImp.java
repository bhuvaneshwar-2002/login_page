package com.WebPageProject1.UserRegistration_Login.Service;

import com.WebPageProject1.UserRegistration_Login.Dto.UserDto;
import com.WebPageProject1.UserRegistration_Login.UserRepo.UserRepository;
import com.WebPageProject1.UserRegistration_Login.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;


    @Override
    public User save(UserDto userDto) {
        User user = new User(userDto.getEmail(),
                passwordEncoder.encode(userDto.getPassword()),
                userDto.getFullname(),
                userDto.getRole());
        return userRepository.save(user);
    }


}

