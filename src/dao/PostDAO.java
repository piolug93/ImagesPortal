package dao;

import model.Post;

import java.util.List;

public interface PostDAO extends GenericDAO<Post, Long> {
    List<Post> getAll();
}
