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
        List<Post> posts = null;
        if(request.getRequestURI().equals("/waiting")) {
            posts = postService.getWaitingPosts();
        }
        else
            posts = postService.getMainPosts();
        request.setAttribute("posts", posts);
        request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
    }
}
