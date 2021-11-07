package com.talhamustafa.i180573_180417;

import android.graphics.Bitmap;

public class Add_Contacts {
    String name, phone;
    Bitmap img;

    public Add_Contacts(String name, String phone, Bitmap img) {
        this.name = name;
        this.phone = phone;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }
}
