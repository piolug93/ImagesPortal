package dao;

import model.Vote;

public interface VoteDAO extends GenericDAO<Vote, Long> {

    public Vote getVoteByUserIdPostId(long userId, long postId);
}
