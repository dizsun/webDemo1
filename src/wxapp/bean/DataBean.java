package wxapp.bean;

/**
 * 用code从微信服务器获取的登陆信息
 */
public class DataBean {
    private Integer errcode;
    private String errmsg;
    private String session_key;
    private Integer expires_in;
    private String openid;

    public DataBean() {
    }

    public DataBean(Integer errcode, String errmsg, String session_key, Integer expires_in, String openid) {
        this.errcode = errcode;
        this.errmsg = errmsg;
        this.session_key = session_key;
        this.expires_in = expires_in;
        this.openid = openid;
    }

    public Integer getErrcode() {
        return errcode;
    }

    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getSession_key() {
        return session_key;
    }

    public void setSession_key(String session_key) {
        this.session_key = session_key;
    }

    public Integer getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(Integer expires_in) {
        this.expires_in = expires_in;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    @Override
    public String toString() {
        return "DataBean{" +
                "errcode=" + errcode +
                ", errmsg='" + errmsg + '\'' +
                ", session_key='" + session_key + '\'' +
                ", expires_in=" + expires_in +
                ", openid='" + openid + '\'' +
                '}';
    }
}
