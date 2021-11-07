package com.talhamustafa.i180573_180417;

import android.graphics.Bitmap;

public class ChatMsg {
    String msg, time, reply, rtime, id;
    int dp, ss;
    Bitmap bitmap;

    public ChatMsg(String msg, String time, String reply, String rtime, String id, int dp, int ss, Bitmap bitmap) {
        this.msg = msg;
        this.time = time;
        this.reply = reply;
        this.rtime = rtime;
        this.id = id;
        this.dp = dp;
        this.ss = ss;
        this.bitmap = bitmap;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public int getSs() {
        return ss;
    }

    public void setSs(int ss) {
        this.ss = ss;
    }

    public int getDp() {
        return dp;
    }

    public void setDp(int dp) {
        this.dp = dp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getRtime() {
        return rtime;
    }

    public void setRtime(String rtime) {
        this.rtime = rtime;
    }
}
