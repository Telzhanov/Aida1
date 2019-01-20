package beks.androidcourse.kz.aida.model;

public class User {
    String id;
    String name;
    String surname;
    String email;
    String phoneNumber;
    String password;
    String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public User(){}
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {

        return email;
    }

    public String getName() {

        return name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public User(String id, String name, String email, String phoneNumber, String password){
        this.id=id;
        this.name=name;
        this.email=email;
        this.phoneNumber=phoneNumber;
        this.password=password;
    }
}
