package com.example.sahil.gpsdemo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class MainActivity extends AppCompatActivity {
    private FusedLocationProviderClient client;
    Button getlocation;
    LocationManager locationManager;
    LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requstpermission();
        getlocation = findViewById(R.id.location);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        client = LocationServices.getFusedLocationProviderClient(this);
        getlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(MainActivity.this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {

                    return;
                }
                client.getLastLocation().addOnSuccessListener(MainActivity.this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
if (location!=null){
    TextView textView=findViewById(R.id.textview);
    textView.setText(location.toString());
}
                    }
                });
            }
        });


}
private  void requstpermission(){
        ActivityCompat.requestPermissions(this,new String[]{ACCESS_FINE_LOCATION},1);
}
    }
