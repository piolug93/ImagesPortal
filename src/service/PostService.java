package service;

import dao.DAOFactory;
import dao.PostDAO;
import model.Post;
import model.User;
import util.PasswordUtil;

import java.util.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

public class PostService {

    public Post createPost(String title, String source, boolean secured, String password, String content, User user) {
        Post post = new Post();
        User usercopy = new User(user);
        post.setAuthor(usercopy);
        post.setTitle(title);
        post.setSource(source);
        post.setSecured(secured);
        String md5password = PasswordUtil.encryptPassword(password);
        post.setPassword(md5password);
        post.setContent(content);
        post.setDate(new Timestamp(new Date().getTime()));
        post.setImageName(UUID.randomUUID().toString());
        PostDAO postDAO = getPostDAO();
        Post result = postDAO.create(post);
        return result;
    }

    public boolean updatePost(Post post) {
        PostDAO postDAO = getPostDAO();
        return postDAO.update(post);
    }

    public Post getPostById(long id) {
        PostDAO postDAO = getPostDAO();
        Post post = postDAO.read(id);
        return post;
    }

    public List<Post> getAllPost() {
        PostDAO postDAO = getPostDAO();
        List<Post> posts = postDAO.getAll();
        return posts;
    }

    private PostDAO getPostDAO() {
        DAOFactory factory = DAOFactory.getDAOFactory();
        PostDAO postDAO = factory.getPostDAO();
        return postDAO;
    }

    public List<Post> getMainPosts() {
        PostDAO postDAO = getPostDAO();
        List<Post> posts = postDAO.getMainPosts();
        return posts;
    }

    public List<Post> getWaitingPosts() {
        PostDAO postDAO = getPostDAO();
        List<Post> posts = postDAO.getWaitingPosts();
        return posts;
    }
}
