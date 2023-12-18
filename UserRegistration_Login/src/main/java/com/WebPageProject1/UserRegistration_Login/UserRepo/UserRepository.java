package com.WebPageProject1.UserRegistration_Login.UserRepo;

import com.WebPageProject1.UserRegistration_Login.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
}

