package model;

import java.sql.Timestamp;

public class User {
    private long id;
    private String login;
    private String password;
    private String email;
    private Timestamp registredDate;
    private boolean activated;
    private boolean blocked;
    private String lastIp;

    public User() {
    }

    public User(String login, String password, String email, Timestamp registredDate, boolean activated, boolean blocked, String lastIp) {

        this.login = login;
        this.password = password;
        this.email = email;
        this.registredDate = registredDate;
        this.activated = activated;
        this.blocked = blocked;
        this.lastIp = lastIp;
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getRegistredDate() {
        return registredDate;
    }

    public void setRegistredDate(Timestamp registredDate) {
        this.registredDate = registredDate;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public String getLastIp() {
        return lastIp;
    }

    public void setLastIp(String lastIp) {
        this.lastIp = lastIp;
    }
}
