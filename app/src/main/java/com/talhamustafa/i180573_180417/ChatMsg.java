package com.talhamustafa.i180573_180417;

import android.graphics.Bitmap;

public class ChatMsg {
    String senderId;
    String receiverId;
    String text;
    Long timestamp;
    String imgSrc;
    Boolean containsImage;

    public ChatMsg(String senderId, String receiverId, String text,String imgSrc,Boolean containsImage) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.text = text;
        this.timestamp=System.currentTimeMillis()/1000;
        this.imgSrc=imgSrc;
        this.containsImage=containsImage;
    }

    public ChatMsg(){

    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public Boolean getContainsImage() {
        return containsImage;
    }

    public void setContainsImage(Boolean containsImage) {
        this.containsImage = containsImage;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}