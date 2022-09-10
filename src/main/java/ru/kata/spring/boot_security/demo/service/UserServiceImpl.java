package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDAO;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserDao userDAO, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = true)
    @Override
    public User passwordCoder(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return user;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserById(int id) {
        return userDAO.getUserById(id);
    }

    @Transactional
    @Override
    public void addUser(User user) {
        userDAO.addUser(passwordCoder(user));
    }

    @Transactional
    @Override
    public void removeUser(int id) {
        userDAO.removeUser(id);
    }

    @Transactional
    @Override
    public void updateUser(User user) {
        userDAO.updateUser(passwordCoder(user));
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserByLogin(String username) {
        return userDAO.getUserByLogin(username);
    }

    @Transactional
    @Override
    public void update(int id, User updateUser) {
        userDAO.update(id, updateUser);
    }


}
