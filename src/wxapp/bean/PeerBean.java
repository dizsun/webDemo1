package wxapp.bean;

public class PeerBean {
    private String user_nickname;
    private String user_avatarUrl;

    public PeerBean() {
    }

    public PeerBean(String user_nickname, String user_avatarUrl) {
        this.user_nickname = user_nickname;
        this.user_avatarUrl = user_avatarUrl;
    }

    public String getUser_nickname() {
        return user_nickname;
    }

    public void setUser_nickname(String user_nickname) {
        this.user_nickname = user_nickname;
    }

    public String getUser_avatarUrl() {
        return user_avatarUrl;
    }

    public void setUser_avatarUrl(String user_avatarUrl) {
        this.user_avatarUrl = user_avatarUrl;
    }
}
