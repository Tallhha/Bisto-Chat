package com.talhamustafa.i180573_180417;

import androidx.annotation.NonNull;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    EditText email, pw;
    Button login, signup;
    private FirebaseAuth.AuthStateListener authStateListener;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.getSupportActionBar().hide();

        email = findViewById(R.id.email1);
        pw = findViewById(R.id.password1);
        login = (Button) findViewById(R.id.button_login1);
        signup = (Button) findViewById(R.id.button_register1);
        firebaseAuth=FirebaseAuth.getInstance();

        authStateListener=new FirebaseAuth.AuthStateListener(){
            FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();



            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseUser!=null){
                    //Toast.makeText(LoginActivity.this,"You are logged in",Toast.LENGTH_SHORT).show();
//                    Intent i=new Intent(LoginActivity.this,ContactListActivity.class);
//                    startActivity(i);
                }
                else{
//                Toast.makeText(Login.this,"Please Login",Toast.LENGTH_SHORT).show();
                }
            }
        };

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email1=email.getText().toString().trim();
                final String password1=pw.getText().toString().trim();
                if(email1.isEmpty()){
                    email.setError("Provide email id");
                    email.requestFocus();
                }

                else if(password1.isEmpty()){
                    pw.setError("Provide password");
                    pw.requestFocus();
                }

                else if(!email1.isEmpty() && !password1.isEmpty()){
                    firebaseAuth.signInWithEmailAndPassword(email1,password1).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                FirebaseAuthException e = (FirebaseAuthException )task.getException();
                                e.printStackTrace();
                                System.out.println(email1 + "---------" + password1);
                                Toast.makeText(Login.this,"Login error",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Intent toHome=new Intent(Login.this,Home.class);
                                startActivity(toHome);
                                finish();
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(Login.this,"Error occured",Toast.LENGTH_SHORT).show();
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
    @Override
    protected void onStart(){
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

}