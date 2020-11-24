package com.example.servlet;

import com.example.dao.UserDao;
import com.example.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Login", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

  private UserDao userDao;

  @Override
  public void init() throws ServletException {
    userDao = new UserDao();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.getRequestDispatcher("/view/login.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String email = req.getParameter("email");
    String password = req.getParameter("password");
    User user = getUser(email, password);
    checkUserRole(user, req, resp);
  }

  private void checkUserRole(User user, HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    if (user != null) {
      req.getSession().setAttribute("admin", false);
      if (user.containsRole("User")) {
        if (user.containsRole("Admin")) {
          req.getSession().setAttribute("admin", true);
        }
        req.getRequestDispatcher("/view/user_menu.jsp").forward(req, resp);
      }
      if (user.containsRole("Guest")) {
        req.getRequestDispatcher("/view/guest_menu.jsp").forward(req, resp);
      }
    }
    req.setAttribute("errorMessage", "Incorrect email or password");
    req.getRequestDispatcher("/view/login.jsp").forward(req, resp);
  }

  private User getUser(String email, String password) {
    User user = userDao.read(email);
    return user != null && password.equals(user.getPassword()) ? user : null;
  }
}
