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

    Button btnclick,btnlocation,btncontact,btnreport;
    ImageView ivclick,ivlocation,ivcontact,ivhidden1,ivhidden2,ivhidden3;
    LinearLayout llclick,lllocation,llcontact,llhidden;

    private int RETURN_FROM_ACTIVITY_PHOTO=31;

    Bitmap Image1,Image2,Image3;
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
                llclick.setVisibility(View.GONE);
                llhidden.setVisibility(View.VISIBLE);
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
        {
            byteArrayImage1=getIntent().getByteArrayExtra("Image1");
            Image1 = BitmapFactory.decodeByteArray(byteArrayImage1, 0, 1);
            ivhidden1.setImageBitmap(Image1);
            byteArrayImage2=getIntent().getByteArrayExtra("Image2");
            Image2 = BitmapFactory.decodeByteArray(byteArrayImage2, 0, 1);
            ivhidden2.setImageBitmap(Image2);
            byteArrayImage3=getIntent().getByteArrayExtra("Image3");
            Image3 = BitmapFactory.decodeByteArray(byteArrayImage3, 0, 1);
            ivhidden3.setImageBitmap(Image3);

        }
    }
}
