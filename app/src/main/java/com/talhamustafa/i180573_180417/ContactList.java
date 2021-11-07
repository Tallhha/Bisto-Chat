package com.talhamustafa.i180573_180417;

public class ContactList {
    String name, msg, time, email;
    int new_text, online, img;

    public ContactList(String name, String msg, String time, int new_text, int online, int img) {
        this.name = name;
        this.msg = msg;
        this.time = time;
        this.new_text = new_text;
        this.online = online;
        this.img = img;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getNew_text() {
        return new_text;
    }

    public void setNew_text(int new_text) {
        this.new_text = new_text;
    }

    public int getOnline() {
        return online;
    }

    public void setOnline(int online) {
        this.online = online;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
