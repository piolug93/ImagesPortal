package dao;

import model.Post;
import model.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import util.ConnectionProvider;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostDAOImpl implements PostDAO {
    private static final String CREATE_POST = "INSERT INTO `posts` (`author`, `title`, `content`, `source`, `date`, `password`, `secured`, `imageName`) VALUES (:author, :title, :content, :source, :date, :password, :secured, :imageName);";
    private static final String READ_POST = "SELECT * FROM `posts` LEFT JOIN `users` ON posts.author = users.id WHERE posts.id = :id;";
    private static final String READ_ALL_POST = "SELECT * FROM `posts` LEFT JOIN `users` ON posts.author = users.id";
    NamedParameterJdbcTemplate template;

    public PostDAOImpl() {
        template = new NamedParameterJdbcTemplate(ConnectionProvider.getDataSource());
    }

    @Override
    public Post create(Post post) {
        Post resultPost = new Post(post);
        KeyHolder holder = new GeneratedKeyHolder();
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("author", post.getAuthor().getId());
        paramMap.put("title", post.getTitle());
        paramMap.put("content", post.getSource());
        paramMap.put("source", post.getSource());
        paramMap.put("date", post.getDate());
        paramMap.put("password", post.getPassword());
        paramMap.put("secured", post.getSecured());
        paramMap.put("imageName", post.getImageName());
        SqlParameterSource paramSource = new MapSqlParameterSource(paramMap);
        int update = template.update(CREATE_POST, paramSource, holder);
        if(update > 0) {
            resultPost.setId(holder.getKey().longValue());
        }
        return resultPost;
    }

    @Override
    public Post read(Long id) {
        SqlParameterSource paramSource = new MapSqlParameterSource("id", id);
        Post post = template.queryForObject(READ_POST, paramSource, new PostRowMapper());
        return post;
    }

    @Override
    public boolean update(Post updateObject) {
        return false;
    }

    @Override
    public boolean delete(Long key) {
        return false;
    }

    @Override
    public List<Post> getAll() {
        List<Post> posts = template.query(READ_ALL_POST, new PostRowMapper());
        return posts;
    }

    private class PostRowMapper implements RowMapper<Post> {
        @Override
        public Post mapRow(ResultSet resultSet, int i) throws SQLException {
            User user = BeanPropertyRowMapper.newInstance(User.class).mapRow(resultSet, i);
            Post post = new Post();
            post.setId(resultSet.getLong("id"));
            post.setTitle(resultSet.getString("title"));
            post.setDate(resultSet.getTimestamp("date"));
            post.setContent(resultSet.getString("content"));
            post.setPassword(resultSet.getString("password"));
            post.setSecured(resultSet.getBoolean("secured"));
            post.setSource(resultSet.getString("source"));
            post.setVoteUp(resultSet.getLong("voteUp"));
            post.setVoteDown(resultSet.getLong("voteDown"));
            post.setImageName(resultSet.getString("imageName"));
            post.setMainPage(resultSet.getBoolean("mainPage"));
            post.setAuthor(user);
            return post;
        }
    }
}
