package com.example.e_dechet;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnvoyerAlerte_Activity extends AppCompatActivity  implements  LocationListener{
    FirebaseFirestore db;
    LocationManager locationManager;
    FirebaseStorage storage;
    StorageReference storageReference;
    private static final int REQUEST_ID_IMAGE = 100;
    ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activy_alerts);
        imageView = (ImageView) findViewById(R.id.image_alerte);
        Button btn_valider = (Button)findViewById(R.id.btn_valider_alerte);
        EditText description = (EditText) findViewById(R.id.edit_description);
        db = FirebaseFirestore.getInstance();
        Double lat = 0.0;
        Double log = 0.0;
        CollectionReference ref = db.collection("Captures");
        // localisation
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE) ;
        Criteria cri = new Criteria();
        String provider = locationManager.getBestProvider(cri, false);

        if (provider != null & !provider.equals("")) {


            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            Location location = locationManager.getLastKnownLocation(provider);

            locationManager.requestLocationUpdates(provider, 2000, 1, (LocationListener) this);
            if(location != null){

                onLocationChanged(location, lat, log);
                lat = location.getLatitude();
                log = location.getLongitude();
            }else {
                Toast.makeText(getApplicationContext(), "Impossible de locaiser la zonre",Toast.LENGTH_LONG).show();
            }
        }
        else{
            Toast.makeText(getApplicationContext(), "Impossible de locaiser la zonre",Toast.LENGTH_LONG).show();
        }
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                startActivityForResult(intent, REQUEST_ID_IMAGE);
            }
        });
        Double finalLat = lat;
        Double finalLog = log;
        btn_valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String desc = description.getText().toString();
                Map<String, Object> capt = new HashMap<>();
                capt.put("desciption", desc);
                capt.put("latitude", finalLat);
                capt.put("longitude", finalLog);

                System.out.println("merci");
                ref.document().set(capt).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                        Toast.makeText(getApplicationContext(), "L'image a bien été envoyée, merci de votre collaboration",Toast.LENGTH_LONG).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }

                });
            }
        });
    }
    public void onLocationChanged(Location location, Double lat, Double log) {
        lat = location.getLatitude();
        log = location.getLongitude();
    }
    // This method will help to retrieve the image
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Match the request 'pic id with requestCode
        if (requestCode == REQUEST_ID_IMAGE ) {


            // BitMap is data structure of image file which store the image in memory
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            // Set the image in imageview for display
            imageView.setImageBitmap(photo);
        }
    }


    @Override
    public void onLocationChanged(@NonNull Location location) {

    }

    @Override
    public void onLocationChanged(@NonNull List<Location> locations) {
        LocationListener.super.onLocationChanged(locations);
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
        LocationListener.super.onProviderEnabled(provider);
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
        LocationListener.super.onProviderDisabled(provider);
    }
}
