package beks.androidcourse.kz.aida.model;

public class Applications {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getNeedMoney() {
        return needMoney;
    }

    public void setNeedMoney(String needMoney) {
        this.needMoney = needMoney;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getRelative() {
        return relative;
    }

    public void setRelative(String relative) {
        this.relative = relative;
    }

    private String id;
    private String diagnosis;
    private String name;
    private String surname;
    private String needMoney;
    private String parentName;
    private String relative;
}
