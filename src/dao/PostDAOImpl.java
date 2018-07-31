package dao;

import model.Post;
import model.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import util.ConnectionProvider;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PostDAOImpl implements PostDAO {
    private static final String CREATE_POST = "INSERT INTO `posts` (`author`, `title`, `content`, `source`, `date`, `password`, `secured`, `imageName`) VALUES (:author, :title, :content, :source, :date, :password, :secured, :imageName);";
    private static final String READ_POST = "SELECT * FROM `posts` LEFT JOIN `users` ON posts.author = users.id WHERE posts.id = :id;";
    private static final String READ_ALL_POST = "SELECT * FROM `posts` LEFT JOIN `users` ON posts.author = users.id";
    NamedParameterJdbcTemplate template;
    private static final String UPDATE_POST = "UPDATE `posts` SET author = :author, title = :title, content = :content, source = :source, date = :date, password = :password, secured = :secured, voteUp = :voteUp, voteDown = :voteDown, views = :views, imageName = :imageName, mainPage = :mainPage WHERE id = :id;";

    public PostDAOImpl() {
        template = new NamedParameterJdbcTemplate(ConnectionProvider.getDataSource());
    }

    @Override
    public Post create(Post post) {
        Post resultPost = new Post(post);
        KeyHolder holder = new GeneratedKeyHolder();
        PostSqlParameterSource paramSource = new PostSqlParameterSource(post);
        paramSource.addValue("author", post.getAuthor().getId());
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
    public boolean update(Post post) {
        PostSqlParameterSource paramSource = new PostSqlParameterSource(post);
        paramSource.addValue("author", post.getAuthor().getId());
        int update = template.update(UPDATE_POST, paramSource);
        if(update > 0)
            return true;
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

    private class PostSqlParameterSource extends AbstractSqlParameterSource {
        private BeanPropertySqlParameterSource beanPropertySqlParameterSource;
        private MapSqlParameterSource mapSqlParameterSource;

        public PostSqlParameterSource(Post post) {
            beanPropertySqlParameterSource = new BeanPropertySqlParameterSource(post);
            mapSqlParameterSource = new MapSqlParameterSource();
        }

        public void addValue(String paramName, Object value) {
            mapSqlParameterSource.addValue(paramName, value);
        }

        @Override
        public boolean hasValue(String paramName) {
            return beanPropertySqlParameterSource.hasValue(paramName) || mapSqlParameterSource.hasValue(paramName);
        }

        @Override
        public Object getValue(String paramName) throws IllegalArgumentException {
            return mapSqlParameterSource.hasValue(paramName) ? mapSqlParameterSource.getValue(paramName) : beanPropertySqlParameterSource.getValue(paramName);
        }
    }
}
