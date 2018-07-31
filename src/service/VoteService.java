package service;

import dao.DAOFactory;
import dao.VoteDAO;
import model.Vote;
import Enum.VoteType;

public class VoteService {
    public Vote addVote(long postId, long userId, VoteType type) {
        Vote vote = new Vote();
        vote.setPostId(postId);
        vote.setUserId(userId);
        vote.setType(type);
        VoteDAO voteDAO = getVoteDAO();
        vote = voteDAO.create(vote);
        return vote;
    }

    public Vote updateVote(long postId, long userId, VoteType type) {
        VoteDAO voteDAO = getVoteDAO();
        Vote voteToUpdate = voteDAO.getVoteByUserIdPostId(userId, postId);
        if(voteToUpdate != null) {
            voteToUpdate.setType(type);
            voteDAO.update(voteToUpdate);
        }
        return voteToUpdate;
    }

    public Vote addOrUpdateVote(long postId, long userId, VoteType type) {
        VoteDAO voteDAO = getVoteDAO();
        Vote vote = voteDAO.getVoteByUserIdPostId(userId, postId);
        Vote resultVote;
        if(vote == null)
            resultVote = addVote(postId, userId, type);
        else
            resultVote = updateVote(postId, userId, type);
        return resultVote;
    }

    public Vote getVoteByPostIdUserId(long postId, long userId) {
        VoteDAO voteDAO = getVoteDAO();
        Vote vote = voteDAO.getVoteByUserIdPostId(postId, userId);
        return vote;
    }

    private VoteDAO getVoteDAO() {
        DAOFactory factory = DAOFactory.getDAOFactory();
        VoteDAO voteDAO = factory.getVoteDAO();
        return voteDAO;
    }
}
