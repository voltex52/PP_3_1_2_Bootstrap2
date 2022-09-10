package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String pageForUser (Model model,
                               @CurrentSecurityContext(expression = "authentication.principal") User principal) {
        model.addAttribute("user",principal);
        return "user-info";
    }
    @GetMapping(value = "/login")
    public String loginPage() {
        return "redirect:/login";
    }
}
