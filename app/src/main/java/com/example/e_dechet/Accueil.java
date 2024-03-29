package com.example.e_dechet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Accueil extends AppCompatActivity {
    String nomcompletglobal ="";
    String emailglobal=" ";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acueil_activity);
        Intent informationpersonelle = getIntent();
        TextView message = (TextView)findViewById(R.id.textView4);
        ImageButton zonesale = (ImageButton)findViewById(R.id.imageButton2);
        ImageButton zonepropre = (ImageButton)findViewById(R.id.imageButton3);

        User u = new User();
        if(informationpersonelle!=null){
            u.setNom_complet(informationpersonelle.getStringExtra(nomcompletglobal));
            u.setEmail(informationpersonelle.getStringExtra(emailglobal));
            message.setText("hello "+ u.getNom_complet());

        }
        FloatingActionButton btn_add_alert = (FloatingActionButton)findViewById(R.id.fab_alerte);
        btn_add_alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent order_add = new Intent(getApplicationContext(),EnvoyerAlerte_Activity.class);
                startActivity(order_add);
            }
        });

        zonesale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent order_zone_sale = new Intent(getApplicationContext(),MapsFragment.class);
                startActivity(order_zone_sale);
            }
        });

        zonepropre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent order_zone_propre = new Intent(getApplicationContext(),MapsZonePropre.class);
                startActivity(order_zone_propre);
            }
        });
    }
}
