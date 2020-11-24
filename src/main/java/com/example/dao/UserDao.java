package com.example.dao;

import com.example.entity.Role;
import com.example.entity.User;
import com.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserDao {

  private SessionFactory sessionFactory;

  public UserDao() {
    sessionFactory = HibernateUtil.getSessionFactory();
  }

  public boolean create(User user) {
    if (read(user) != null) {
      Role role = new Role("Guest");
      role.setId(3);
      Session session = sessionFactory.openSession();
      session.beginTransaction();
      session.update(role);
      user.addRole(role);
      session.persist(user);
      session.getTransaction().commit();
      session.close();
      return true;
    }
    return false;
  }

  public User read(int id) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    User user = session.get(User.class, id);
    session.getTransaction().commit();
    session.close();
    return user;
  }

  public User read(String email) {
    User user = null;
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<User> users = session.createQuery("SELECT u from User u " +
        "WHERE u.email = :email", User.class)
        .setParameter("email", email).list();
    if (users != null && !users.isEmpty()) {
      user = users.get(0);
    }
    session.getTransaction().commit();
    session.close();
    return user;
  }

  public User read(User user) {
    return read(user.getEmail());
  }

  public List<User> readAll() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<User> users = session.createQuery("from User ", User.class).getResultList();
    session.getTransaction().commit();
    session.close();
    return users;
  }

  public int update(User user) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    int affected = session.createQuery("UPDATE User set email = :email, password = :pass " +
        "WHERE id = :id")
        .setParameter("email", user.getEmail())
        .setParameter("pass", user.getPassword())
        .setParameter("id", user.getId()).executeUpdate();
    session.getTransaction().commit();
    session.close();
    return affected;
  }

  public int delete(int id) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    int affected = session.createQuery("delete from User where id = :id")
        .setParameter("id", id)
        .executeUpdate();
    session.getTransaction().commit();
    session.close();
    return affected;
  }

  public Set<User> getUsersByRole(String roleName) {
    Set<User> users = new HashSet<>();
    Role role = new RoleDao().read(roleName);
    if (role != null) {
      users = role.getUsers();
    }
    return users;
  }

  public void addRole(int id, Role role) {
    User user = read(id);
    if (user != null) {
      user.addRole(role);
      Session session = sessionFactory.openSession();
      session.beginTransaction();
      session.update(user);
      session.getTransaction().commit();
      session.close();
    }
  }
}
