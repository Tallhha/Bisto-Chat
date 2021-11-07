package com.talhamustafa.i180573_180417;

public class CallLogs {
    String name, msg;
    int incoming, online, img;

    public CallLogs(String name, String msg, int incoming, int online, int img) {
        this.name = name;
        this.msg = msg;
        this.incoming = incoming;
        this.online = online;
        this.img = img;
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

    public int getIncoming() {
        return incoming;
    }

    public void setIncoming(int incoming) {
        this.incoming = incoming;
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
