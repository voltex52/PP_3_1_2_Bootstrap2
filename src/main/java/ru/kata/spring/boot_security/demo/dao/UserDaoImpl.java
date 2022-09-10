package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("select u from User u").getResultList();
    }

    @Override
    public User getUserById(int id) {
        return entityManager.find(User.class,id);
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void removeUser(int id) {
//        Query q = entityManager.createQuery("delete from User User where userId = :id");
//        q.setParameter("id",id);
//        q.executeUpdate();
        entityManager.remove(entityManager.find(User.class, id));
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public void update(int id, User updatedUser) {
        entityManager.merge(updatedUser);
    }

    @Override
    public User getUserByLogin(String username) {
        TypedQuery<User> q = (entityManager.createQuery("select u from User u " +
                "join fetch u.roles where u.username = :username",User.class));
        q.setParameter("username",username);
        return q.getResultList().stream().findFirst().orElse(null);
    }
}