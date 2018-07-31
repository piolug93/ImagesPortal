package dao;

import model.Vote;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import util.ConnectionProvider;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import Enum.VoteType;

public class VoteDAOImpl implements VoteDAO {
    private static final String CREATE_VOTE = "INSERT INTO vote(postId, userId, type) VALUES (:postId, :userId, :type);";
    private static final String READ_VOTE_BY_DISCOVERY_USE_IDS = "SELECT * FROM vote WHERE userId = :userId AND postId = :postId;";
    private static final String UPDATE_VOTE = "UPDATE vote SET type = :type WHERE userId = :userId AND postId = :postId;";

    private NamedParameterJdbcTemplate template;

    public VoteDAOImpl() {
        template = new NamedParameterJdbcTemplate(ConnectionProvider.getDataSource());
    }
    @Override
    public Vote create(Vote vote) {
        Vote voteCopy = new Vote(vote);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("postId", voteCopy.getPostId());
        paramMap.put("userId", voteCopy.getUserId());
        paramMap.put("type", voteCopy.getType().toString());
        SqlParameterSource paramSource = new MapSqlParameterSource(paramMap);
        template.update(CREATE_VOTE, paramSource);
        return voteCopy;
    }

    @Override
    public Vote read(Long primaryKey) {
        return null;
    }

    @Override
    public boolean update(Vote vote) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("postId", vote.getPostId());
        paramMap.put("userId", vote.getUserId());
        paramMap.put("type", vote.getType().toString());
        SqlParameterSource paramSource = new MapSqlParameterSource(paramMap);
        if(template.update(UPDATE_VOTE, paramSource) > 0)
            return true;
        else
            return false;
    }

    @Override
    public boolean delete(Long key) {
        return false;
    }

    @Override
    public List<Vote> getAll() {
        return null;
    }

    @Override
    public Vote getVoteByUserIdPostId(long postId, long userId) {
        Vote resultVote = null;
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("userId", userId);
        paramMap.put("postId", postId);
        SqlParameterSource paramSource = new MapSqlParameterSource(paramMap);
        try {
            resultVote = template.queryForObject(READ_VOTE_BY_DISCOVERY_USE_IDS, paramSource, new VoteRowMapper());
        } catch (EmptyResultDataAccessException e) {
            //vote not found
        }
        return resultVote;
    }

    private class VoteRowMapper implements RowMapper<Vote> {
        @Override
        public Vote mapRow(ResultSet resultSet, int i) throws SQLException {
            Vote vote = new Vote();
            vote.setPostId(resultSet.getLong("userId"));
            vote.setUserId(resultSet.getLong("postId"));
            vote.setType(VoteType.valueOf(resultSet.getString("type")));
            return vote;
        }
    }
}


