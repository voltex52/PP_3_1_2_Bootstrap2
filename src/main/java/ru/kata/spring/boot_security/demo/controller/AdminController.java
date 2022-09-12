package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.validation.Valid;
import java.util.ArrayList;

@Controller
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String findAll(Model model,
                          @CurrentSecurityContext(expression = "authentication.principal") User principal) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("roles",roleService.getAllRoles());
        model.addAttribute("user",principal);
        return "user-list";
    }

    @PostMapping ("admin/new" )
    public String saveUser (@ModelAttribute("user") @Valid User user,
                            @RequestParam("role") ArrayList<Long> roles) {
        user.setRolesList(roleService.findByIdRoles(roles));
        userService.addUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/{id}")
    public String find(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "user-info";
    }

    @PatchMapping("/admin/{id}/edit")
    public String update(User user,
                         @PathVariable("id") int id,
                         @RequestParam("role") ArrayList<Long>roles) {
        user.setRolesList(roleService.findByIdRoles(roles));
        userService.update(id, user);
        return "redirect:/admin";
}


    @PostMapping ("/admin/{id}/delete")
    public String deleteUserById(@PathVariable("id") int id) {
        userService.removeUser(id);
        return "redirect:/admin";
    }
}
