package com.example.dao;

import com.example.entity.Role;
import com.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class RoleDao {

  private SessionFactory sessionFactory;

  public RoleDao() {
    sessionFactory = HibernateUtil.getSessionFactory();
  }

  public void create(Role role) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    session.persist(role);
    session.getTransaction().commit();
    session.close();
  }

  public Role read(int id) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    Role role = session.get(Role.class, id);
    session.getTransaction().commit();
    session.close();
    return role;
  }

  public Role read(String roleName) {
    Role role = null;
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<Role> roles = session.createQuery("from Role where role = :roleName ", Role.class)
        .setParameter("roleName", roleName)
        .list();
    if (roles != null && !roles.isEmpty()) {
      role = roles.get(0);
    }
    session.getTransaction().commit();
    session.close();
    return role;
  }

  public List<Role> readAll() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<Role> roles = session.createQuery("from Role ", Role.class)
        .getResultList();
    session.getTransaction().commit();
    session.close();
    return roles;
  }

  public int update(Role role) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    int affected = session.createQuery("UPDATE Role SET role = :role where id = :id")
        .setParameter("role", role.getRole())
        .setParameter("id", role.getId())
        .executeUpdate();
    session.getTransaction().commit();
    session.close();
    return affected;
  }

  public int delete(int id) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    int affected = session.createQuery("delete from Role where id = :id")
        .setParameter("id", id)
        .executeUpdate();
    session.getTransaction().commit();
    session.close();
    return affected;
  }
}
