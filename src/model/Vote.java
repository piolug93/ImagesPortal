package model;
import Enum.VoteType;

public class Vote {
    private long postId;
    private long userId;
    private VoteType type;

    public Vote() {}

    public Vote(Vote vote) {
        this.postId = vote.postId;
        this.userId = vote.userId;
        this.type = vote.type;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public VoteType getType() {
        return type;
    }

    public void setType(VoteType type) {
        this.type = type;
    }
}
