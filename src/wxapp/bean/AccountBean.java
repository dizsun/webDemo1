package wxapp.bean;

import java.sql.Timestamp;

public class AccountBean {
    private int id;
    private String name;
    private String brief_introduction;
    private Timestamp date;
    private int code;
    private String creator;
    private int bills;
    private int peers;
    private String total;

    public AccountBean() {
    }

    public AccountBean(int id, String name, String brief_introduction, Timestamp date, int code, String creator, int bills, int peers, String total) {
        this.id = id;
        this.name = name;
        this.brief_introduction = brief_introduction;
        this.date = date;
        this.code = code;
        this.creator = creator;
        this.bills = bills;
        this.peers = peers;
        this.total = total;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
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

    public String getBrief_introduction() {
        return brief_introduction;
    }

    public void setBrief_introduction(String brief_introduction) {
        this.brief_introduction = brief_introduction;
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
