package controller;

import model.Post;
import Enum.VoteType;
import model.User;
import model.Vote;
import service.PostService;
import service.VoteService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/vote")
public class VoteController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if(user != null) {
            VoteType voteType = VoteType.valueOf(request.getParameter("vote"));
            long userId = user.getId();
            long postId = Long.parseLong(request.getParameter("postId"));
            updateVote(postId, userId, voteType);
            response.sendRedirect(request.getHeader("referer"));
        } else {
            response.sendRedirect("login");
        }
    }

    private void updateVote(long postId, long userId, VoteType voteType) {
        VoteService voteService = new VoteService();
        Vote existingVote = voteService.getVoteByPostIdUserId(postId, userId);
        Vote updateVote = voteService.addOrUpdateVote(postId, userId, voteType);
        if(existingVote != updateVote || !updateVote.equals(existingVote))
            updatePost(postId, existingVote, updateVote);
    }

    private void updatePost(long postId, Vote oldVote, Vote newVote) {
        PostService postService = new PostService();
        Post postById = postService.getPostById(postId);
        Post updatedPost = null;
        if(oldVote == null && newVote != null)
            updatedPost = addPostVote(postById, newVote.getType());
        else if(oldVote != null && newVote != null) {
            updatedPost = removePostVote(postById, oldVote.getType());
            updatedPost = addPostVote(updatedPost, newVote.getType());
        }
        postService.updatePost(updatedPost);
    }

    private Post addPostVote(Post post, VoteType voteType) {
        Post postCopy = new Post(post);
        if(voteType == VoteType.VOTE_UP)
            postCopy.setVoteUp(postCopy.getVoteUp() + 1);
        else if(voteType == VoteType.VOTE_DOWN)
            postCopy.setVoteDown(postCopy.getVoteDown() + 1);
        return postCopy;
    }

    private Post removePostVote(Post post, VoteType voteType) {
        Post postCopy = new Post(post);
        if(voteType == VoteType.VOTE_UP)
            postCopy.setVoteUp(postCopy.getVoteUp() - 1);
        else if(voteType == VoteType.VOTE_DOWN)
            postCopy.setVoteDown(postCopy.getVoteDown() - 1);
        return postCopy;
    }
}
