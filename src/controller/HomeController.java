package controller;

import model.Post;
import service.PostService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet({"", "/waiting"})
public class HomeController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PostService postService = new PostService();
        List<Post> posts = postService.getAllPost();
        request.setAttribute("posts", posts);
        if(request.getRequestURI().equals("/waiting")) {
            request.setAttribute("waiting", true);
        }
        request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
    }
}
