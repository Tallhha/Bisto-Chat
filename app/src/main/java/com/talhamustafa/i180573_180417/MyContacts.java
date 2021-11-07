package com.talhamustafa.i180573_180417;

import android.provider.BaseColumns;

public class MyContacts {
    public static String DB_NAME="myContacts.db";
    public static int DB_VERSION=1;

    public static class Contact implements BaseColumns{
        public static String TABLENAME="contacts";
        public static String _PWD="pwd";
        public static String _EMAIL="email";
    }

    public static class ContactsInfo implements BaseColumns{
        public static String TABLENAME="contactsInfo";
        public static String _NAME="name";
        public static String _MSG="msg";
        public static String _TIME="time";
    }

    public static class Chats implements BaseColumns{
        public static String TABLENAME="chat";
        public static String _NAME="name";
        public static String _MSG="msg";
        public static String _TIME="time";
        public static String _REPLY_TIME="reply_time";
        public static String _REPLY_MSG="reply_msg";
        public static String _SS="ss";

    }

    public static class MyInfo implements BaseColumns{
        public static String TABLENAME="myInfo";
        public static String _FNAME="fname";
        public static String _LNAME="lname";
        public static String _BIO="bio";
        public static String _EMAIL="email";
    }



}
