package controller;

import model.Post;
import org.apache.commons.io.IOUtils;
import service.PostService;
import util.PasswordUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/post")
public class PostController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");

        PostService postService = new PostService();
        if(id != null) {
            Post post = postService.getPostById(Long.valueOf(id));
            request.setAttribute("post", post);
        }
        request.getRequestDispatcher("WEB-INF/post.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PostService postService = new PostService();
        String id = req.getParameter("id");
        String password = req.getParameter("password");
        String md5password = PasswordUtil.encryptPassword(password);
        Post post = postService.getPostById(Long.valueOf(id));
        req.setAttribute("post", post);
        if(md5password.equals(post.getPassword()))
            req.setAttribute("allow", true);
        req.getRequestDispatcher("WEB-INF/post.jsp").forward(req, resp);
    }
}
