package com.example.servlet;

import com.example.dao.UserDao;
import com.example.entity.Role;
import com.example.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

  private final String PATH = "/WEB-INF/view";

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String email = req.getParameter("email");
    String password = req.getParameter("password");
    UserDao dao = new UserDao();
    User user = dao.read(email);
    checkUserRole(user, password, req, resp);
  }

  private void checkUserRole(User user, String password, HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    if (user != null && user.getPassword().equals(password)) {
      Role role = user.getRole();
      req.getSession().setAttribute("admin", false);
      if (role.equals(Role.USER) || role.equals(Role.ADMIN)) {
        if (role.equals(Role.ADMIN)) {
          req.getSession().setAttribute("admin", true);
        }
        req.getRequestDispatcher(PATH + "/user_page.jsp").forward(req, resp);
      }
      if (role.equals(Role.GUEST)) {
        req.getRequestDispatcher(PATH + "/guest_page.html").forward(req, resp);
      }
    }
    req.setAttribute("error", "Incorrect email or password");
    req.getRequestDispatcher(PATH + "/login.jsp").forward(req, resp);
  }
}
