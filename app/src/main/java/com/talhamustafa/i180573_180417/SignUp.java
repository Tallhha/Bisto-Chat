package com.talhamustafa.i180573_180417;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    EditText email, pw, confirm;
    Button signup, login;
    MyDBHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        this.getSupportActionBar().hide();

        email = findViewById(R.id.email);
        pw = findViewById(R.id.password);
        confirm = findViewById(R.id.password_confirm);

        signup = (Button) findViewById(R.id.button_signup);
        login = (Button) findViewById(R.id.button_login);
        helper = new MyDBHelper(SignUp.this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password = pw.getText().toString();
                String cnfrm = confirm.getText().toString();
                String em = email.getText().toString();

                if (em.equals("") || password.equals("") || cnfrm.equals("")) {
                    Toast.makeText(SignUp.this, "Enter all fields", Toast.LENGTH_SHORT).show();
                } else {
                    if (password.equals(cnfrm)) {
                        Boolean chkuser = helper.checkEmail(em);
                        if(!chkuser) {
                            Boolean insert = helper.insertData(em, password);
                            if(insert){
                                Toast.makeText(SignUp.this, "ADDED", Toast.LENGTH_SHORT).show();
                                //New Intent
                                Intent intent = new Intent(SignUp.this, CreateAccount.class);
                                intent.putExtra("email",em);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(SignUp.this, "NOT ADDED", Toast.LENGTH_SHORT).show();

                            }
                        }
                        else{
                            Toast.makeText(SignUp.this, "ALREADY EXISTS", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SignUp.this, Login.class);
                            startActivity(intent);
                        }
                        helper.close();
                        finish();
                    }
                    else{
                        Toast.makeText(SignUp.this, "MISMATCH", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp.this, Login.class);
                startActivity(intent);
            }
        });

    }
}