package com.talhamustafa.i180573_180417;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;

public class CreateAccount extends AppCompatActivity {

    EditText first_name, last_name, bio;
    CircleImageView display_pic;
    Button save;
    MyDBHelper helper;
    String email = "";
    String one, two, three;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        this.getSupportActionBar().hide();

        first_name = findViewById(R.id.first_name);
        last_name = findViewById(R.id.last_name);
        bio = findViewById(R.id.bio);
        display_pic = findViewById(R.id.display_pic);
        save = (Button) findViewById(R.id.save);

        email = getIntent().getStringExtra("email");
        //getData();
        one = getIntent().getStringExtra("one");
        two = getIntent().getStringExtra("two");
        three = getIntent().getStringExtra("three");
        first_name.setText(one);
        last_name.setText(two);
        bio.setText(three);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String fname = first_name.getText().toString();
                String lname = last_name.getText().toString();
                String bioo = bio.getText().toString();

                /*
                Boolean insert = helper.insertInfo(email, fname, lname, bioo);
                if(insert){
                    Toast.makeText(CreateAccount.this, "ADDED", Toast.LENGTH_SHORT).show();
                    //New Intent
                }
                else{
                    int update = helper.updateInfo(email, fname, lname, bioo);
                    if(update == 0) {
                        Toast.makeText(CreateAccount.this, "NOT ADDED", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(CreateAccount.this, "EDITED", Toast.LENGTH_SHORT).show();
                    }
                }

                 */
                Intent intent = new Intent(CreateAccount.this, Home.class);
                intent.putExtra("email",email);
                intent.putExtra("one", fname);
                intent.putExtra("two", lname);
                intent.putExtra("three", bioo);

                startActivity(intent);


            }
        });

    }

    public void getData(){
        MyDBHelper db = new MyDBHelper(CreateAccount.this);
        email = getIntent().getStringExtra("email");
        Cursor c = db.getInfo(email);
        String temp_fname = "", temp_lname = "", temp_bio = "";

        if(c.getCount() == 0){
            return;
        }
        while (c.moveToNext()){
            temp_fname = c.getString(2);
            temp_lname = c.getString(0);
            temp_bio = c.getString(1);
        }

        first_name.setText(temp_fname);
        last_name.setText(temp_lname);
        bio.setText(temp_bio);

    }
}