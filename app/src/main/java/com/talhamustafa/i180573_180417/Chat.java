package com.talhamustafa.i180573_180417;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.ContentObserver;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class Chat extends ScreenshotDetectionActivity{

    EditText input_message;
    ImageView camera, msg_sent;
    AppCompatImageView call_button;
    TextView text_name;
    ImageView back;
    String timeStamp = new SimpleDateFormat("HH:mm").format(new Date());

    ArrayList<ChatMsg> msg = add_values();
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ChatAdapter chatAdapter;
    int pic;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        this.getSupportActionBar().hide();


        input_message = findViewById(R.id.input_message);
        camera = findViewById(R.id.camera);
        call_button = findViewById(R.id.call_button);
        msg_sent = findViewById(R.id.sendd);
        text_name = findViewById(R.id.text_name);
        back = findViewById(R.id.back_button);

        name = getIntent().getStringExtra("name");
        text_name.setText(name);
        pic = getIntent().getIntExtra("photo",0);

        recyclerView = findViewById(R.id.chat_recycler_view);
        chatAdapter = new ChatAdapter(msg, getApplicationContext());
        layoutManager = new LinearLayoutManager(Chat.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(chatAdapter);
        //recyclerView.setHasFixedSize(true);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Chat.this, Home.class);
                startActivity(intent);
            }
        });


        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 100);

            }
        });


        call_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Chat.this, Call.class);
                intent.putExtra("name",name);
                intent.putExtra("photo",pic);
                startActivity(intent);

            }
        });

        msg_sent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChatMsg list;
                list = new ChatMsg(input_message.getText().toString(),timeStamp,"Hmm",timeStamp,"1",pic, 0, null);
                msg.add(list);
                String name = getIntent().getStringExtra("name");

                MyDBHelper db = new MyDBHelper(Chat.this);
                Boolean check = db.insertMsg(name, list);
                if(check){
                    //Toast.makeText(Chat.this, "ADDED MSG", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Chat.this, "FAILED", Toast.LENGTH_SHORT).show();
                }

                input_message.setText("");
                chatAdapter = new ChatAdapter(msg, Chat.this);
                recyclerView.setAdapter(chatAdapter);
            }
        });

        /*
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("SCREEN_","SS_",NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
         */
    }

    @Override
    public void onScreenCaptured(String path) {
        //Toast.makeText(this, path, Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, "SCREENSHOT CAPTURED", Toast.LENGTH_SHORT).show();

        /*
        NotificationCompat.Builder builder = new NotificationCompat.Builder(Chat.this,"ScreenShot Taken");
        builder.setContentTitle("SS");
        builder.setContentText("USER TOOK SS");
        builder.setSmallIcon(R.id.dp);
        builder.setAutoCancel(true);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(Chat.this);
        managerCompat.notify(1, builder.build());
         */

        // build notification

        msg.get(msg.size() - 1).setSs(1);
        MyDBHelper helper = new MyDBHelper(this);
        helper.updateMsg1(msg.get(msg.size() - 1).getMsg(),1);

        chatAdapter = new ChatAdapter(msg, Chat.this);
        recyclerView.setAdapter(chatAdapter);

    }

    @Override
    public void onScreenCapturedWithDeniedPermission() {
        Toast.makeText(this, "Please grant read external storage permission for screenshot detection", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.print("ON RESUME CALLED");
        getData();
    }


    public ArrayList<ChatMsg> add_values() {
        ArrayList<ChatMsg> lst = new ArrayList<>();
        ChatMsg list;

        //list = new ChatMsg("Hello",timeStamp,"Hmmm",timeStamp,"1",pic, 0, null);
        //lst.add(list);

        return lst;
    }

    public void getData(){
        MyDBHelper db = new MyDBHelper(Chat.this);
        String name = getIntent().getStringExtra("name");
        System.out.println(name);
        Cursor c = db.getMessage(name);

        if(c.getCount() == 0){
            return;
        }
        //GET
        msg.clear();
        while (c.moveToNext()){
            msg.add(new ChatMsg(c.getString(5), c.getString(2), c.getString(3), c.getString(4),c.getString(0),pic, c.getInt(6), null));
        }
        chatAdapter = new ChatAdapter(msg, Chat.this);
        recyclerView.setAdapter(chatAdapter);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            Bitmap captureImage = (Bitmap) data.getExtras().get("data");

            msg.get(msg.size() - 1).setBitmap(captureImage);
            chatAdapter = new ChatAdapter(msg, Chat.this);
            recyclerView.setAdapter(chatAdapter);

        }
    }
}