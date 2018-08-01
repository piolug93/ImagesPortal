package controller;

import model.Post;
import service.PostService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

@WebServlet({"", "/top", "/waiting"})
public class HomeController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PostService postService = new PostService();
        List<Post> posts = null;
        if(request.getRequestURI().equals("/waiting"))
            posts = postService.getWaitingPosts();
        else
            posts = postService.getMainPosts();
        if(request.getRequestURI().equals("/top"))
            posts.sort(new Comparator<Post>() {
                @Override
                public int compare(Post o1, Post o2) {
                    long o1Vote = o1.getVoteUp() - o1.getVoteDown();
                    long o2Vote = o2.getVoteUp() - o2.getVoteDown();
                    if(o1Vote < o2Vote)
                        return 1;
                    else if(o1Vote > o2Vote)
                        return -1;
                    return 0;
                }
            });
        request.setAttribute("posts", posts);
        request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
    }
}
