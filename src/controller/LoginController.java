package controller;

import model.User;
import org.springframework.dao.EmptyResultDataAccessException;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getUserPrincipal() != null) {
            response.sendRedirect(request.getContextPath()+"/");
        } else {
            request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        UserService userService = new UserService();
        User user = null;
        String id = null;
        try {
            user = userService.getUserByLogin(login);
            id = String.valueOf(user.getId());
        } catch (EmptyResultDataAccessException e) {
            request.setAttribute("error", "bad_login");
            request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
        }
        try {
            request.login(id, password);
            request.getSession().setAttribute("user", user);
            response.sendRedirect(request.getHeader("referer"));
        } catch(ServletException e) {
            request.setAttribute("error", "bad_login");
            request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
        }

    }
}

