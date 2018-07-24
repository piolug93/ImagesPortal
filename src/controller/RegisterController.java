package controller;

import exception.ErrorRegistrationException;
import model.EmailValidator;
import model.LoginValidate;
import model.PasswordValidate;
import service.UserService;
import Enum.RegistrationErrors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String login = request.getParameter("login");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String repassword = request.getParameter("confirm-password");
        try {
            PasswordValidate.validate(password, repassword);
            EmailValidator.validate(email);
            LoginValidate.validate(login);
        } catch (ErrorRegistrationException e) {
            if(e.getTypeError() == RegistrationErrors.REPASSWORD_INCORRECT) {
                request.setAttribute("error", "repassword_incorrect");
            }
            else if(e.getTypeError() == RegistrationErrors.EMAIL_EXIST) {
                request.setAttribute("error", "email_exist");
            }
            else if(e.getTypeError() == RegistrationErrors.EMAIL_IS_INCORRECT) {
                request.setAttribute("error", "email_incorrect");
            }
            else if(e.getTypeError() == RegistrationErrors.LOGIN_EXIST) {
                request.setAttribute("error", "login_exist");
            }
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }
        UserService userService = new UserService();
        userService.createUser(login, email, password);
        request.setAttribute("error", "false");
        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
    }
}
