package com.talhamustafa.i180573_180417;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyDBHelper extends SQLiteOpenHelper {

    String CREATE_CONTACTS_TABLE="CREATE TABLE " +
            MyContacts.Contact.TABLENAME + "(" +
            MyContacts.Contact._EMAIL+" TEXT PRIMARY KEY, "+
            MyContacts.Contact._PWD+" TEXT)";

    String CREATE_INFO_TABLE="CREATE TABLE " +
            MyContacts.MyInfo.TABLENAME + "(" +
            MyContacts.MyInfo._EMAIL+" TEXT PRIMARY KEY, "+
            MyContacts.MyInfo._LNAME+" TEXT, "+
            MyContacts.MyInfo._BIO+" TEXT, "+
            MyContacts.MyInfo._FNAME+" TEXT)";

    String CREATE_CHAT_TABLE="CREATE TABLE " +
            MyContacts.Chats.TABLENAME + "(" +
            MyContacts.Chats._ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            MyContacts.Chats._NAME+" TEXT,"+
            MyContacts.Chats._TIME+" TEXT,"+
            MyContacts.Chats._REPLY_MSG+" TEXT,"+
            MyContacts.Chats._REPLY_TIME+" TEXT,"+
            MyContacts.Chats._MSG+" TEXT,"+
            MyContacts.Chats._SS+" NUMBER)";

    String DELETE_CONTACTS_TABLE="DROP TABLE IF EXISTS "+MyContacts.Contact.TABLENAME;
    String DELETE_CHAT_TABLE="DROP TABLE IF EXISTS "+MyContacts.Chats.TABLENAME;
    String DELETE_INFO_TABLE="DROP TABLE IF EXISTS "+MyContacts.MyInfo.TABLENAME;

    public MyDBHelper(@Nullable Context context) {
        super(context,MyContacts.DB_NAME,null,MyContacts.DB_VERSION);
        SQLiteDatabase db=this.getWritableDatabase();

        System.out.println("CREATE");
        System.out.println(CREATE_CONTACTS_TABLE);
        System.out.println(CREATE_CHAT_TABLE);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        System.out.println("CREATE TABLE");

        sqLiteDatabase.execSQL(CREATE_CONTACTS_TABLE);
        sqLiteDatabase.execSQL(CREATE_CHAT_TABLE);
        sqLiteDatabase.execSQL(CREATE_INFO_TABLE);


        //sqLiteDatabase.execSQL("create Table contactsInfo(user_email TEXT PRIMARY KEY, password TEXT)");
        System.out.println("CREATE TABLE");

        //sqLiteDatabase.execSQL(CREATE_CONTACTSINFO_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DELETE_CONTACTS_TABLE);
        sqLiteDatabase.execSQL(DELETE_CHAT_TABLE);
        sqLiteDatabase.execSQL(DELETE_INFO_TABLE);


        //sqLiteDatabase.execSQL("drop table if exists contactsInfo");
        System.out.println("DROP TABLE");

        //sqLiteDatabase.execSQL(DELETE_CONTACTSINFO_TABLE);
        onCreate(sqLiteDatabase);
    }

    public Boolean insertData(String email, String pw){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(MyContacts.Contact._EMAIL, email);
        cv.put(MyContacts.Contact._PWD, pw);
        long result = database.insert(MyContacts.Contact.TABLENAME,null,cv);
        System.out.println("INSERTED? " + result);
        //database.close();
        return result != -1;
    }

    public Boolean insertInfo(String email, String fname, String lname, String bio){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(MyContacts.MyInfo._EMAIL, email);
        cv.put(MyContacts.MyInfo._FNAME, fname);
        cv.put(MyContacts.MyInfo._LNAME, lname);
        cv.put(MyContacts.MyInfo._BIO, bio);

        long result = database.insert(MyContacts.MyInfo.TABLENAME,null,cv);
        System.out.println("INSERTED? " + result);
        //database.close();
        return result != -1;
    }

    public Boolean insertMsg(String name, ChatMsg list){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(MyContacts.Chats._NAME, name);
        cv.put(MyContacts.Chats._MSG, list.getMsg());
        cv.put(MyContacts.Chats._TIME, list.getTime());
        cv.put(MyContacts.Chats._REPLY_TIME, list.getRtime());
        cv.put(MyContacts.Chats._REPLY_MSG, list.getReply());
        cv.put(MyContacts.Chats._SS, list.getSs());

        long result = database.insert(MyContacts.Chats.TABLENAME,null,cv);
        System.out.println("INSERTED? " + result);
        //database.close();
        return result != -1;
    }

    public Cursor getMessage(String name){
        System.out.println("HERE");

        SQLiteDatabase database = this.getReadableDatabase();
        String q = "select * from "+MyContacts.Chats.TABLENAME+" where "+MyContacts.Chats._NAME+" = ? order by "+MyContacts.Chats._TIME;

        Cursor c = database.rawQuery(q,new String[]{name});
        return c;
    }

    public Cursor getInfo(String email){
        System.out.println("HERE");

        SQLiteDatabase database = this.getReadableDatabase();
        String q = "select * from "+MyContacts.MyInfo.TABLENAME+" where "+MyContacts.MyInfo._EMAIL+" = ?";

        Cursor c = database.rawQuery(q,new String[]{email});
        return c;
    }

    public int updateInfo(String email, String fname, String lname, String bio){
        System.out.println("HERE");

        SQLiteDatabase database = this.getReadableDatabase();
        String q = "UPDATE "+MyContacts.MyInfo.TABLENAME+" SET "+MyContacts.MyInfo._FNAME+"= "+fname+", "+MyContacts.MyInfo._LNAME+"="+lname+", "+MyContacts.MyInfo._BIO+"= "+bio+" where "+MyContacts.MyInfo._EMAIL+" = ?";

        Cursor c = database.rawQuery(q,new String[]{email});
        return c.getCount();
    }

    public void updateMsg(String old_msg, String time, String new_msg){

        SQLiteDatabase database = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(MyContacts.Chats._MSG, new_msg);
        cv.put(MyContacts.Chats._TIME, time);

        database.update(MyContacts.Chats.TABLENAME, cv, " msg = ?", new String[]{old_msg});
    }

    public void updateMsg1(String old_msg, int ss){

        SQLiteDatabase database = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(MyContacts.Chats._SS, ss);

        database.update(MyContacts.Chats.TABLENAME, cv, " msg = ?", new String[]{old_msg});
    }

    public Boolean checkEmail(String email){
        System.out.println("HERE");

        SQLiteDatabase database = this.getReadableDatabase();
        String q = "select * from "+MyContacts.Contact.TABLENAME+" where "+MyContacts.Contact._EMAIL+" = ?";

        Cursor c = database.rawQuery(q,new String[]{email});
        return c.getCount() > 0;
    }

    public Boolean checkEmailandPw(String email, String pw){
        SQLiteDatabase database = this.getReadableDatabase();
        String q = "select * from "+MyContacts.Contact.TABLENAME+" where "+MyContacts.Contact._EMAIL+" = ? and "+MyContacts.Contact._PWD+" = ?";

        Cursor c = database.rawQuery(q,new String[]{email, pw});
        return c.getCount() > 0;
    }



}
