package wxapp.bean;

import java.sql.Timestamp;

public class BillBean {
    private int id;
    private String brief_intro;
    private String creator;
    private String money;
    private Timestamp timestamp;

    public BillBean() {
    }

    public BillBean(int id, String brief_intro, String creator, String money, Timestamp timestamp) {
        this.id = id;
        this.brief_intro = brief_intro;
        this.creator = creator;
        this.money = money;
        this.timestamp = timestamp;
    }

    public String getId() {
        return String.valueOf(id);
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrief_intro() {
        return brief_intro;
    }

    public void setBrief_intro(String brief_intro) {
        this.brief_intro = brief_intro;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getTimestamp() {
        return String.valueOf(timestamp).replace(".0","");
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
