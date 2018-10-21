package beks.androidcourse.kz.aida.model;

import android.media.Image;

public class Sicks extends User {
    private String needMoney;
    private String diagnosis;
    private String parentName;
    private String parentSurname;
    private String relative;

    public Sicks() {

    }

    public Sicks(String id, String name, String surname, String phoneNumber, String needMoney, String diagnosis, String parentName, String parentSurname, String relative) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.needMoney = needMoney;
        this.diagnosis = diagnosis;
        this.parentName = parentName;
        this.parentSurname = parentSurname;
        this.relative = relative;
    }

    public void setParentSurname(String parentSurname) {
        this.parentSurname = parentSurname;
    }

    public String getNeedMoney() {
        return needMoney;
    }

    public void setNeedMoney(String needMoney) {
        this.needMoney = needMoney;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
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
}
