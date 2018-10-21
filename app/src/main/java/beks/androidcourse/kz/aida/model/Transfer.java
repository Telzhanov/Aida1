package beks.androidcourse.kz.aida.model;

public class Transfer {
    String id;
    String sid;
    String amountMoney;
    String date;
    public Transfer(){

    }
    public Transfer(String id, String sid, String amountMoney, String date) {
        this.id = id;
        this.sid = sid;
        this.amountMoney = amountMoney;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getAmountMoney() {
        return amountMoney;
    }

    public void setAmountMoney(String amountMoney) {
        this.amountMoney = amountMoney;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
