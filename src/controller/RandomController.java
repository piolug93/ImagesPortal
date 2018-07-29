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
import java.util.Random;

@WebServlet("/random")
public class RandomController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        PostService postService = new PostService();
        List<Post> postList = postService.getAllPost();
        Random random = new Random();
        int randomPost = random.nextInt(postList.size());
        long id = postList.get(randomPost).getId();
        response.sendRedirect("/post?id="+id);
    }
}
