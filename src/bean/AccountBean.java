package bean;

import java.sql.Timestamp;

public class AccountBean {
    private int id;
    private String name;
    private String briefIntro;
    private Timestamp date;
    private int code;
    private String creator;
    private int bills;
    private int peers;

    public AccountBean() {
    }

    public AccountBean(int id, String name, String briefIntro, Timestamp date, int code, String creator, int bills, int peers) {
        this.id = id;
        this.name = name;
        this.briefIntro = briefIntro;
        this.date = date;
        this.code = code;
        this.creator = creator;
        this.bills = bills;
        this.peers = peers;
    }

    public String getId() {
        return String.valueOf(id);
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBriefIntro() {
        return briefIntro;
    }

    public void setBriefIntro(String briefIntro) {
        this.briefIntro = briefIntro;
    }

    public String getDate() {
        return String.valueOf(date).replace(".0","");
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getBills() {
        return String.valueOf(bills);
    }

    public void setBills(int bills) {
        this.bills = bills;
    }

    public String getPeers() {
        return String.valueOf(peers);
    }

    public void setPeers(int peers) {
        this.peers = peers;
    }
}
