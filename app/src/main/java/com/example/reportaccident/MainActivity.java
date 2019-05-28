package com.example.reportaccident;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final int ERROR_DIALOG_REQUEST =9001 ;
    private static final int MY_CAMERA_REQUEST_CODE =111 ;
    TextView btnclick,btnlocation,btncontact;
    TextView tvhiddenLocation;
    TextView btnhiddenViewLocation,tvName,tvContact;
    Button btnreport;
    ImageView ivclick,ivlocation,ivcontact,ivhidden1,ivhidden2,ivhidden3;
    LinearLayout llclick,lllocation,llcontact,llhiddenPhoto,llhiddenLocation,llhiddencontact;

    private int RETURN_FROM_ACTIVITY_PHOTO=31;
    private int RETURN_FROM_ACTIVTY_MAPS=21;
    private int RETURN_FROM_ACTIVITY_CALLER_INFO=21;

    Bitmap Img1,Img2,Img3;
    byte[] byteArrayImage1,byteArrayImage2,byteArrayImage3;
    String finalLocation;
    String Name,ContactNumber;
    int Readytoreport=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


       //initializing the views
        btnclick=findViewById(R.id.btnclick);
        btnlocation=findViewById(R.id.btnlocation);
        btncontact=findViewById(R.id.btncontact);
        btnreport=findViewById(R.id.btnreport);
        ivclick=findViewById(R.id.ivclick);
        ivlocation=findViewById(R.id.ivlocation);
        ivcontact=findViewById(R.id.ivcontact);
        ivhidden1=findViewById(R.id.ivhidden1);
        ivhidden2=findViewById(R.id.ivhidden2);
        ivhidden3=findViewById(R.id.ivhidden3);
        tvName=findViewById(R.id.tvName);
        tvContact=findViewById(R.id.tvContact);
        llclick=findViewById(R.id.llclick);
        lllocation=findViewById(R.id.lllocation);
        llcontact=findViewById(R.id.llcontact);
        llhiddenPhoto=findViewById(R.id.llhiddenPhoto);
        llhiddenLocation=findViewById(R.id.llhiddenLocation);
        llhiddencontact=findViewById(R.id.llhiddencontact);

        btnhiddenViewLocation=findViewById(R.id.btnhiddenViewLocation);
        tvhiddenLocation=findViewById(R.id.tvhiddenLocation);

        llclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//Checking for Camera Permissions
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(MainActivity.this, "Please Give Camera Permissions",
                            Toast.LENGTH_SHORT).show();
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[] {Manifest.permission.CAMERA}, MY_CAMERA_REQUEST_CODE);

                }
                else {
                    Intent intent = new Intent(MainActivity.this, Photo.class);
                    startActivityForResult(intent, RETURN_FROM_ACTIVITY_PHOTO);
                }

            }
        });

        btnhiddenViewLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,MapsActivity.class);
                startActivity(intent);
            }
        });

        llcontact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,CallerInfo.class);
                startActivityForResult(intent,RETURN_FROM_ACTIVITY_CALLER_INFO);

            }
        });

        btnreport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Readytoreport>=2)
                {
                    Intent intent=new Intent(MainActivity.this,SplashScreen.class);
                    startActivity(intent);
                    MainActivity.this.finish();
                }
            }
        });


        if(isServicesOK()){
            init();
        }

    }

    private void Camerapermissions()
    {

    }

    private void init(){
        lllocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,MapsActivity.class);
                startActivityForResult(intent,RETURN_FROM_ACTIVTY_MAPS);
            }
        });
    }

    public boolean isServicesOK(){
        Log.d(TAG, "isServicesOK: checking google services version");

        int available = GoogleApiAvailability.getInstance().
                isGooglePlayServicesAvailable(MainActivity.this);

        if(available == ConnectionResult.SUCCESS){
            //everything is fine and the user can make map requests
            Log.d(TAG, "isServicesOK: Google Play Services is working");
            return true;
        }
        else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            //an error occured but we can resolve it
            Log.d(TAG, "isServicesOK: an error occured but we can fix it");
            Dialog dialog = GoogleApiAvailability.getInstance()
                    .getErrorDialog(MainActivity.this, available,
                    ERROR_DIALOG_REQUEST);
            dialog.show();
        }else{
            Toast.makeText(this, "You can't make map requests", Toast.LENGTH_SHORT).show();
        }
        return false;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

//        Preventing from crash on backpressed from Photo activity
        if (data != null) {

            if (requestCode==RETURN_FROM_ACTIVTY_MAPS && resultCode==RESULT_OK){
                finalLocation=data.getStringExtra("AddressLine");
                lllocation.setVisibility(View.GONE);
                llhiddenLocation.setVisibility(View.VISIBLE);
                tvhiddenLocation.setText(finalLocation);
                btnhiddenViewLocation.setBackgroundResource(R.drawable.roundbuttonblue);
                Readytoreport++;
            }

            if (requestCode == RETURN_FROM_ACTIVITY_PHOTO && resultCode == RESULT_OK) {
                llclick.setVisibility(View.GONE);
                llhiddenPhoto.setVisibility(View.VISIBLE);
                byteArrayImage1 = data.getByteArrayExtra("Image1");
                Img1 = BitmapFactory.decodeByteArray(byteArrayImage1, 0, byteArrayImage1.length);
                ivhidden1.setImageBitmap(Img1);
                byteArrayImage2 = data.getByteArrayExtra("Image2");
                Img2 = BitmapFactory.decodeByteArray(byteArrayImage2, 0, byteArrayImage2.length);
                ivhidden2.setImageBitmap(Img2);
                byteArrayImage3 = data.getByteArrayExtra("Image3");
                Img3 = BitmapFactory.decodeByteArray(byteArrayImage3, 0, byteArrayImage3.length);
                ivhidden3.setImageBitmap(Img3);
                Readytoreport++;
            }

            if (requestCode==RETURN_FROM_ACTIVITY_CALLER_INFO && resultCode==RESULT_OK){
                llcontact.setVisibility(View.GONE);
                llhiddencontact.setVisibility(View.VISIBLE);
                Name=data.getStringExtra("Name");
                ContactNumber=data.getStringExtra("ContactNumber");
                tvName.setText(Name);
                tvContact.setText(ContactNumber);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == MY_CAMERA_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }



}
