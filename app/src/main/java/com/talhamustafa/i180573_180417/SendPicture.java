package com.talhamustafa.i180573_180417;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class SendPicture extends AppCompatActivity {

    ImageView image;
    TextView delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_picture);
        this.getSupportActionBar().hide();


        image = findViewById(R.id.roundedImageView);
        delete = findViewById(R.id.delete);

        byte[] byteArray = getIntent().getByteArrayExtra("image");
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

        image.setImageBitmap(bmp);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SendPicture.this, Home.class);
                startActivity(intent);
            }
        });

    }
}