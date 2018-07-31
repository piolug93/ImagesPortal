package model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Post implements Serializable {
    long id;
    User author;
    String title;
    String content;
    String source;
    Timestamp date;
    String password;
    Boolean secured;
    long voteUp;
    long voteDown;
    long views;
    String imageName;
    boolean mainPage;

    public Post() { }

    public Post(Post post) {
        this.id = post.id;
        this.author = post.author;
        this.title = post.title;
        this.content = post.content;
        this.source = post.source;
        this.date = post.date;
        this.password = post.password;
        this.secured = post.secured;
        this.voteUp = post.voteUp;
        this.voteDown = post.voteDown;
        this.views = post.views;
        this.imageName = post.imageName;
        this.mainPage = post.mainPage;
    }

    public boolean isMainPage() {
        return mainPage;
    }

    public void setMainPage(boolean mainPage) {
        this.mainPage = mainPage;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getSecured() {
        return secured;
    }

    public void setSecured(Boolean secured) {
        this.secured = secured;
    }

    public long getVoteUp() {
        return voteUp;
    }

    public void setVoteUp(long voteUp) {
        this.voteUp = voteUp;
    }

    public long getVoteDown() {
        return voteDown;
    }

    public void setVoteDown(long voteDown) {
        this.voteDown = voteDown;
    }

    public long getViews() {
        return views;
    }

    public void setViews(long views) {
        this.views = views;
    }
}
