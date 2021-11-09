package com.talhamustafa.i180573_180417;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

//THIS IS HOME PAGE: ITS THE PAGE OF HOME
public class Home extends AppCompatActivity {

    ImageButton call, camera, message, contacts;
    ImageView edit;
    ImageView test, test1;

    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerViewAdapter recyclerViewAdapter;
    ArrayList<ContactList> lst = add_values();

    String email;
    String one, two, three;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        this.getSupportActionBar().hide();

        call = (ImageButton) findViewById(R.id.call);
        camera = (ImageButton) findViewById(R.id.camera);
        message = (ImageButton) findViewById(R.id.message);
        contacts = (ImageButton) findViewById(R.id.contacts);
        edit = findViewById(R.id.edit);

        email = getIntent().getStringExtra("email");

        one = getIntent().getStringExtra("one");
        two = getIntent().getStringExtra("two");
        three = getIntent().getStringExtra("three");

        if(ContextCompat.checkSelfPermission(Home.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(Home.this, new String[]{Manifest.permission.CAMERA},100);
        }


        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Login.class);
                startActivity(intent);
            }
        });

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Call_History.class);

                intent.putExtra("one", one);
                intent.putExtra("two", two);
                intent.putExtra("three", three);

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

        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(Home.this, Chat.class);
                //startActivity(intent);
            }
        });

        contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Contacts.class);
                intent.putExtra("one", one);
                intent.putExtra("two", two);
                intent.putExtra("three", three);
                startActivity(intent);

            }
        });

        recyclerView = findViewById(R.id.recycler);
        recyclerViewAdapter = new RecyclerViewAdapter(lst, getApplicationContext());
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setHasFixedSize(true);

    }

    public ArrayList<ContactList> add_values() {
        ArrayList<ContactList> lst = new ArrayList<>();
        ContactList list;

        list = new ContactList("Tayyab Abbas","Hmm","08:00",0,1,R.drawable.dp);
        lst.add(list);

        list = new ContactList("Talha Mustafa","Hmm","10:00",1,0,R.drawable.tla);
        lst.add(list);

        list = new ContactList("Momin","Hmm","12:00",0,0,R.drawable.momo);
        lst.add(list);

        list = new ContactList("Tayyab Ali","Hmm","13:00",0,1,R.drawable.tyab2);
        lst.add(list);

        list = new ContactList("Shahzeb","Hmm","14:00",1,0,R.drawable.shahzeb);
        lst.add(list);

        list = new ContactList("Osama Malik","Hmm","14:15",0,1,R.drawable.omama);
        lst.add(list);

        list = new ContactList("Yumna","Hmm","16:10",1,1,R.drawable.pata_nai_kon_yai_yai);
        lst.add(list);

        return lst;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            Bitmap captureImage = (Bitmap) data.getExtras().get("data");

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            captureImage.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();

            Intent in1 = new Intent(Home.this, SendPicture.class);
            in1.putExtra("image",byteArray);
            startActivity(in1);
        }
    }
}