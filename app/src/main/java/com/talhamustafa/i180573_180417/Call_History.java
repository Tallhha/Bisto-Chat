package com.talhamustafa.i180573_180417;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class Call_History extends AppCompatActivity {

    ImageButton call, camera, message, contacts;

    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    CallLogsAdapter CallLogsAdapter;
    ArrayList<CallLogs> lst = add_values();

    String one, two ,three;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_history);
        this.getSupportActionBar().hide();


        call = (ImageButton) findViewById(R.id.call);
        camera = (ImageButton) findViewById(R.id.camera);
        message = (ImageButton) findViewById(R.id.message);
        contacts = (ImageButton) findViewById(R.id.contacts);

        one = getIntent().getStringExtra("one");
        two = getIntent().getStringExtra("two");
        three = getIntent().getStringExtra("three");


        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(Call_History.this, Chat.class);
                //startActivity(intent);
            }
        });

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 101);
            }
        });

        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Call_History.this, Home.class);
                intent.putExtra("one", one);
                intent.putExtra("two", two);
                intent.putExtra("three", three);

                startActivity(intent);
            }
        });

        contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Call_History.this, Contacts.class);
                intent.putExtra("one", one);
                intent.putExtra("two", two);
                intent.putExtra("three", three);
                startActivity(intent);
            }
        });


        recyclerView = findViewById(R.id.recycler1);
        CallLogsAdapter = new CallLogsAdapter(lst, getApplicationContext());
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(CallLogsAdapter);
        recyclerView.setHasFixedSize(true);

    }

    public ArrayList<CallLogs> add_values() {
        ArrayList<CallLogs> lst = new ArrayList<>();
        CallLogs list;

        list = new CallLogs("Tayyab Abbas","los - 12:00",R.drawable.ic_incoming_call,0, R.drawable.dp);
        lst.add(list);


        list = new CallLogs("Momin","In Bound - 12:15",R.drawable.ic_outgoing_call,1, R.drawable.momo);
        lst.add(list);


        list = new CallLogs("Tayyab Ali","lost - 13:00",R.drawable.ic_incoming_call,1, R.drawable.tyab2);
        lst.add(list);


        list = new CallLogs("Shahzeb","In Bound - 13:15",R.drawable.ic_outgoing_call,0, R.drawable.shahzeb);
        lst.add(list);

        list = new CallLogs("Osama","In Bound - 14:00",R.drawable.ic_outgoing_call,0, R.drawable.omama);
        lst.add(list);

        list = new CallLogs("Yumna","lost - 15:30",R.drawable.ic_incoming_call,1, R.drawable.pata_nai_kon_yai_yai);
        lst.add(list);


        return lst;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101) {
            Bitmap captureImage = (Bitmap) data.getExtras().get("data");

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            captureImage.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();

            Intent in1 = new Intent(Call_History.this, SendPicture.class);
            in1.putExtra("image",byteArray);
            startActivity(in1);
        }
    }

}