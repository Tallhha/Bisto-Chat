package com.talhamustafa.i180573_180417;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText email, pw;
    Button login, signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.getSupportActionBar().hide();

        email = findViewById(R.id.email1);
        pw = findViewById(R.id.password1);
        login = (Button) findViewById(R.id.button_login1);
        signup = (Button) findViewById(R.id.button_register1);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String em = email.getText().toString();
                String pass = pw.getText().toString();
                if(em.equals("") || pass.equals("")){
                    Toast.makeText(Login.this, "FILL ALL", Toast.LENGTH_SHORT).show();
                }
                else{
                    MyDBHelper db = new MyDBHelper(Login.this);
                    Boolean check = db.checkEmailandPw(em, pass);
                    if(check){
                        Intent intent = new Intent(Login.this, Home.class);
                        intent.putExtra("email", em);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(Login.this, "INVALID", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, SignUp.class);
                intent.putExtra("one","Talha");
                intent.putExtra("two","Mustafa");
                intent.putExtra("three","oof");

                startActivity(intent);
            }
        });

    }


}