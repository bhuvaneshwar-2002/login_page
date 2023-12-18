package com.WebPageProject1.UserRegistration_Login.Service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
public class CustomSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        var authorities = authentication.getAuthorities();

        if (authorities.stream().anyMatch(auth -> auth.getAuthority().equals("ADMIN"))) {
            response.sendRedirect("/admin-page");
        } else if (authorities.stream().anyMatch(auth -> auth.getAuthority().equals("USER"))) {
            response.sendRedirect("/user");
        } else {
            response.sendRedirect("/error");
        }
    }
}
