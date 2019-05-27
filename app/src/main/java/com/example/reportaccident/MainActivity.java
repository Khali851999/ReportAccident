package com.example.reportaccident;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    TextView btnclick,btnlocation,btncontact;
    Button btnreport;
    ImageView ivclick,ivlocation,ivcontact,ivhidden1,ivhidden2,ivhidden3;
    LinearLayout llclick,lllocation,llcontact,llhidden;

    private int RETURN_FROM_ACTIVITY_PHOTO=31;

    Bitmap Img1,Img2,Img3;
    byte[] byteArrayImage1,byteArrayImage2,byteArrayImage3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


       //initializing the views
        btnclick=findViewById(R.id.btnclick);
        btnlocation=findViewById(R.id.btnlocation);
        btncontact=findViewById(R.id.btncontact);
        ivclick=findViewById(R.id.ivclick);
        ivlocation=findViewById(R.id.ivlocation);
        ivcontact=findViewById(R.id.ivcontact);
        ivhidden1=findViewById(R.id.ivhidden1);
        ivhidden2=findViewById(R.id.ivhidden2);
        ivhidden3=findViewById(R.id.ivhidden3);
        llclick=findViewById(R.id.llclick);
        lllocation=findViewById(R.id.lllocation);
        llcontact=findViewById(R.id.llcontact);
        llhidden=findViewById(R.id.llhidden);

        llclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Photo.class);
                startActivityForResult(intent,RETURN_FROM_ACTIVITY_PHOTO);

            }
        });

        lllocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,MapsActivity.class);
                startActivity(intent);
            }
        });

        llcontact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RETURN_FROM_ACTIVITY_PHOTO && resultCode==RESULT_OK)
            llclick.setVisibility(View.GONE);
            llhidden.setVisibility(View.VISIBLE);
            byteArrayImage1=data.getByteArrayExtra("Image1");
            Img1 = BitmapFactory.decodeByteArray(byteArrayImage1, 0, byteArrayImage1.length);
            ivhidden1.setImageBitmap(Img1);
            byteArrayImage2=data.getByteArrayExtra("Image2");
            Img2 = BitmapFactory.decodeByteArray(byteArrayImage2, 0, byteArrayImage2.length);
            ivhidden2.setImageBitmap(Img2);
            byteArrayImage3=data.getByteArrayExtra("Image3");
            Img3 = BitmapFactory.decodeByteArray(byteArrayImage3, 0, byteArrayImage3.length);
            ivhidden3.setImageBitmap(Img3);

    }
}
