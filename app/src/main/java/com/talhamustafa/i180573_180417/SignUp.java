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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUp extends AppCompatActivity {

    EditText email, pw, confirm;
    Button signup, login;
    private FirebaseAuth mAuth;

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

        mAuth = FirebaseAuth.getInstance();

        signup.setOnClickListener( v -> {
            mAuth.createUserWithEmailAndPassword(email.getText().toString(),pw.getText().toString())
                    .addOnCompleteListener(task -> {
                        if(task.isSuccessful()){
                            createProfile(mAuth.getCurrentUser().getUid(),email.getText().toString());
                            Intent intent=new Intent(SignUp.this,CreateAccount.class);
                            intent.putExtra("id",mAuth.getCurrentUser().getUid());
                            intent.putExtra("email",email.getText().toString());
                            startActivity(intent);
                            finish();

                        }
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(
                                SignUp.this,
                                "Failed...!",
                                Toast.LENGTH_SHORT
                        ).show();
                    });
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp.this, Login.class);
                startActivity(intent);
            }
        });

    }

    public void createProfile(String id, String email){
        Intent intent = new Intent(SignUp.this,CreateAccount.class);
        intent.putExtra("id",id);
        intent.putExtra("email",email);
        startActivity(intent);
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null){
//            Toast.makeText(
//                    RegisterActivity.this,
//                    currentUser.getUid()+"",
//                    Toast.LENGTH_SHORT
//            ).show();
        }
    }
}