package com.WebPageProject1.UserRegistration_Login.Service;

import com.WebPageProject1.UserRegistration_Login.Dto.UserDto;
import com.WebPageProject1.UserRegistration_Login.model.User;

public interface UserService {
    User save(UserDto userDto);
}

