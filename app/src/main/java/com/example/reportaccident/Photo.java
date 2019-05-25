package com.example.reportaccident;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Photo extends AppCompatActivity {

    ImageView imageView1,imageView2,imageView3;
    Button buttonattach;
    private final int requestCode1 = 1;
    private final int requestCode2 = 2;
    private final int requestCode3 = 3;

    Bitmap bitmap1,bitmap2,bitmap3;

    byte[] byteArray1,byteArray2,byteArray3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        imageView1=findViewById(R.id.imageView1);
        imageView2=findViewById(R.id.imageView2);
        imageView3=findViewById(R.id.imageView3);
        buttonattach=findViewById(R.id.buttonattach);


        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoCaptureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(photoCaptureIntent, requestCode1);
                }

        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoCaptureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(photoCaptureIntent, requestCode2);
            }
        });
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoCaptureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(photoCaptureIntent, requestCode3);
            }
        });

        buttonattach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toActivityMain = new Intent(Photo.this,MainActivity.class);
                toActivityMain.putExtra("Image1",byteArray1);
                toActivityMain.putExtra("Image2",byteArray2);
                toActivityMain.putExtra("Image3",byteArray3);
                setResult(RESULT_OK,toActivityMain);
                Photo.this.finish();

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
          super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK){
            bitmap1 = (Bitmap)data.getExtras().get("data");
            imageView1.setImageBitmap(bitmap1);
            ByteArrayOutputStream bStream = new ByteArrayOutputStream();
            bitmap1.compress(Bitmap.CompressFormat.PNG, 100, bStream);
            byteArray1 = bStream.toByteArray();

        }
        else if (requestCode == 2 && resultCode == RESULT_OK){
            bitmap2 = (Bitmap)data.getExtras().get("data");
            imageView2.setImageBitmap(bitmap2);
            ByteArrayOutputStream bStream = new ByteArrayOutputStream();
            bitmap2.compress(Bitmap.CompressFormat.PNG, 100, bStream);
            byteArray2 = bStream.toByteArray();
        }
        else if (requestCode == 3 && resultCode == RESULT_OK){
             bitmap3 = (Bitmap)data.getExtras().get("data");
            imageView3.setImageBitmap(bitmap3);
            ByteArrayOutputStream bStream = new ByteArrayOutputStream();
            bitmap3.compress(Bitmap.CompressFormat.PNG, 100, bStream);
            byteArray3 = bStream.toByteArray();
        }
    }

}
