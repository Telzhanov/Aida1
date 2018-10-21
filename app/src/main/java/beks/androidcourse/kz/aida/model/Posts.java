package beks.androidcourse.kz.aida.model;
public class Posts {
    String sid;
    String text;
    String date;
    public Posts() {
    }

    public Posts(String sid, String text, String date) {
        this.sid = sid;
        this.text = text;
        this.date = date;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
