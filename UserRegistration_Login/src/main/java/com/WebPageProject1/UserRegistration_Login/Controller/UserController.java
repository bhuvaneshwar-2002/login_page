package com.WebPageProject1.UserRegistration_Login.Controller;

import com.WebPageProject1.UserRegistration_Login.Dto.UserDto;
import com.WebPageProject1.UserRegistration_Login.Service.CustomUserDetailsService;
import com.WebPageProject1.UserRegistration_Login.Service.UserService;
import com.WebPageProject1.UserRegistration_Login.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @GetMapping("/registration")
    public String getRegistrationPage(@ModelAttribute("user") UserDto userDto) {

        return "registration";
    }

    @PostMapping("/registration")
    public String saveUser(@ModelAttribute("user") UserDto userDto, Model model) {
        userService.save(userDto);
        model.addAttribute("message", "Registered Successfully");
        return "redirect:/login?registered";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }
    @PostMapping("/login")
    public String processLogin(@ModelAttribute("userDto") UserDto userDto, Model model) {
        // Implement your authentication logic using Spring Security

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDto.getEmail(),
                userDto.getPassword()
        );

        Authentication authenticated = customUserDetailsService.authenticate(authentication);

        if (authenticated != null && authenticated.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authenticated);
            return "redirect:/user";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }
    @GetMapping("/user")
    public String getUserPage() {
        return "user"; // Assuming "user.html" is the user page template
    }

}
