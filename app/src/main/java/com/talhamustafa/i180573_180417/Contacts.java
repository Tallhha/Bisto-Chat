package com.talhamustafa.i180573_180417;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Contacts extends AppCompatActivity {

    ImageButton call, camera, message, contacts;
    TextView edit;
    ImageView add_contact, make_group;

    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ContactsAdapter contactsAdapter;
    ArrayList<Add_Contacts> lst;
    String one, two, three;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        this.getSupportActionBar().hide();


        call = (ImageButton) findViewById(R.id.call);
        camera = (ImageButton) findViewById(R.id.camera);
        message = (ImageButton) findViewById(R.id.message);
        contacts = (ImageButton) findViewById(R.id.contacts);
        edit = findViewById(R.id.Edit);

        make_group = findViewById(R.id.make_group);

        make_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Contacts.this, "Make Group CLicked", Toast.LENGTH_SHORT).show();
            }
        });
        add_contact = findViewById(R.id.add_contact);

        add_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Contacts.this, "Add Contact CLicked", Toast.LENGTH_SHORT).show();
            }
        });

        one = getIntent().getStringExtra("one");
        two = getIntent().getStringExtra("two");
        three = getIntent().getStringExtra("three");

        lst = new ArrayList<>();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS},1);
        }
        else{
            getContact();
        }

        recyclerView = findViewById(R.id.scrollView);
        contactsAdapter = new ContactsAdapter(lst, getApplicationContext());
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(contactsAdapter);
        recyclerView.setHasFixedSize(true);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Contacts.this, CreateAccount.class);
                intent.putExtra("one",one);
                intent.putExtra("two",two);
                intent.putExtra("three",three);
                startActivity(intent);
            }
        });

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Contacts.this, Call_History.class);
                startActivity(intent);
            }
        });

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 102);
            }
        });

        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Contacts.this, Home.class);
                startActivity(intent);
            }
        });

        contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(Contacts.this, Contacts.class);
                //startActivity(intent);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 102) {
            Bitmap captureImage = (Bitmap) data.getExtras().get("data");

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            captureImage.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();

            Intent in1 = new Intent(Contacts.this, SendPicture.class);
            in1.putExtra("image",byteArray);
            startActivity(in1);
        }
    }

    private void getContact(){
        Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);
        while(cursor.moveToNext()){
            int temp = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
            String n = cursor.getString(temp);

            temp = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            String num = cursor.getString(temp);

            String image_uri = "";
            Bitmap bitmap = null;

            temp = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI);
            image_uri = cursor.getString(temp);
            if (image_uri != null) {
                System.out.println(Uri.parse(image_uri));
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), Uri.parse(image_uri));
                    System.out.println(bitmap);

                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }

            Add_Contacts c = new Add_Contacts(n,num,bitmap);
            lst.add(c);

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 1){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                getContact();
            }
        }
    }
}