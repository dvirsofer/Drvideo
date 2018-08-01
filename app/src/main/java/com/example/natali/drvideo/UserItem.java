package com.example.natali.drvideo;

public class UserItem {

    public String name;
    public String email;
    public int img;

    public UserItem(String name, String email, int img) {
        this.name = name;
        this.email = email;
        this.img = img;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public int getImg() {
        return img;
    }
    public void setImg(int img) {
        this.img = img;
    }

}
