package filter;

import model.User;
import service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpReq = (HttpServletRequest) req;
        if(httpReq.getUserPrincipal() != null && httpReq.getSession().getAttribute("user") == null) {
            saveUserInSession(httpReq);
        }
        chain.doFilter(req, resp);
    }

    private void saveUserInSession(HttpServletRequest request) {
        UserService userService = new UserService();
        long id = Long.parseLong(request.getUserPrincipal().getName());
        User userByLogin = userService.getUserById(id);
        request.getSession(true).setAttribute("user", userByLogin);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}