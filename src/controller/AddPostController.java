package controller;

import model.Post;
import model.User;
import org.apache.commons.io.FilenameUtils;
import service.PostService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

@WebServlet("/addpost")
@MultipartConfig(maxFileSize=1024*1024*50,
        maxRequestSize=1024*1024*100)
public class AddPostController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String title = request.getParameter("title");
        String source = request.getParameter("source");
        boolean secured  = "on".equals(request.getParameter("secured"));
        String password = request.getParameter("password");
        String content = request.getParameter("content");
        User authenticatedUser = (User) request.getSession().getAttribute("user");
        if(request.getUserPrincipal() != null) {
            PostService postService = new PostService();
            Post post = postService.createPost(title, source, secured, password, content, authenticatedUser);
            getFile(request, post.getImageName());
            response.sendRedirect("/post?id="+post.getId());
        } else {
            response.sendError(403);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getUserPrincipal() != null) {
            request.getRequestDispatcher("WEB-INF/addpost.jsp").forward(request, response);
        } else {
            response.sendError(403);
        }
    }

    private void getFile(HttpServletRequest request, String imageName) throws IOException, ServletException {
        String uploadFilePath = "D:\\AAA";
        File fileSaveDir = new File(uploadFilePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }
        Part part = request.getPart("image");
        part.write(uploadFilePath + File.separator + imageName);
    }
}
