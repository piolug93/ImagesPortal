package controller;

import model.Post;
import service.PostService;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@WebServlet("/post")
public class PostController extends HttpServlet {

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
}
