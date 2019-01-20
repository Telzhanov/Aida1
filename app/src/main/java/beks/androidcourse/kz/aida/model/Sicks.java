package beks.androidcourse.kz.aida.model;

import android.media.Image;
import android.os.Parcel;
import android.os.Parcelable;

import java.text.ParseException;

public class Sicks extends User implements Parcelable {
    private String needMoney;
    private String diagnosis;
    private String parentName;
    private String sickId;
    private String parentSurname;
    private String relative;

    public Sicks() {

    }

    public Sicks(String id, String name, String surname, String phoneNumber, String needMoney, String diagnosis, String parentName, String parentSurname, String relative,String sickId) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.needMoney = needMoney;
        this.diagnosis = diagnosis;
        this.parentName = parentName;
        this.parentSurname = parentSurname;
        this.relative = relative;
        this.sickId = sickId;
    }
    public Sicks(String id){
        this.id = id;
    }

    public void setParentSurname(String parentSurname) {
        this.parentSurname = parentSurname;
    }

    public String getNeedMoney() {
        return needMoney;
    }
    public String getParentSurname() {
        return parentSurname;
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

    public String getSickId() {
        return sickId;
    }

    public void setSickId(String sickId) {
        this.sickId = sickId;
    }

    @Override
    public int describeContents() {
        return 0;
    }
    public static final Creator<Sicks> CREATOR = new Creator<Sicks>() {
        @Override
        public Sicks createFromParcel(Parcel source) {
            String id = source.readString();
            return new Sicks(id);
        }

        @Override
        public Sicks[] newArray(int size) {
            return new Sicks[size];
        }
    };
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(sickId);
    }
}
