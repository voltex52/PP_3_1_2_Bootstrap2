package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService{
    private final RoleDao roleDAO;

    public RoleServiceImpl(RoleDao roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Role> getAllRoles() {
        return roleDAO.getAllRoles();
    }

    @Transactional
    @Override
    public void addRole(Role role) {
        roleDAO.addRole(role);
    }

    @Transactional(readOnly = true)
    @Override
    public Role findById(long id) {
        return roleDAO.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Set<Role> findByIdRoles(List<Long> roles) {
        return roleDAO.findByIdRoles(roles);
    }

}
