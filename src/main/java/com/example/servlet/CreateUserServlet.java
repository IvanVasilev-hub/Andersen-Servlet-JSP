package com.example.servlet;

import com.example.dao.UserDao;
import com.example.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/create"})
public class CreateUserServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.getRequestDispatcher("/WEB-INF/view/create_user.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String email = req.getParameter("email");
    String password = req.getParameter("password");
    UserDao dao = new UserDao();
    User user = new User(email, password);

    if (dao.isExists(user)) {
      req.setAttribute("error", "User already exists");
      req.getRequestDispatcher("/WEB-INF/view/create_user.jsp").forward(req, resp);
    } else {
      dao.create(user);
      req.setAttribute("success", "User created");
      resp.sendRedirect("/");
    }
  }
}
