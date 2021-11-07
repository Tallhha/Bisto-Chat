package com.talhamustafa.i180573_180417;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import android.os.Bundle;

import de.hdodenhof.circleimageview.CircleImageView;

public class Call extends AppCompatActivity {

    public ImageButton plus_button;

    public ImageButton video_button;
    public ImageButton call_button;
    CircleImageView display;
    TextView n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);
        this.getSupportActionBar().hide();

        n = findViewById(R.id.name);
        display = findViewById(R.id.display_pic);

        String name = getIntent().getStringExtra("name");
        int pic = getIntent().getIntExtra("photo",0);
        n.setText(name);
        display.setImageResource(pic);

        plus_button = (ImageButton) findViewById(R.id.plus_button);

        plus_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(), "Add Friend", Toast.LENGTH_SHORT).show();
            }
        });

        video_button = (ImageButton) findViewById(R.id.video_button);

        video_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(), "Turn on Video", Toast.LENGTH_SHORT).show();
            }
        });

        call_button = (ImageButton) findViewById(R.id.call_button);

        call_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(), "End Call", Toast.LENGTH_SHORT).show();

                Intent intent_chat = new Intent(Call.this, Chat.class);
                startActivity(intent_chat);

            }
        });
    }
}
