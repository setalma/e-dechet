package com.example.e_dechet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Accueil extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acueil_activity);
        FloatingActionButton btn_add_alert = (FloatingActionButton)findViewById(R.id.fab_alerte);
        btn_add_alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent order_add = new Intent(getApplicationContext(),EnvoyerAlerte_Activity.class);
                startActivity(order_add);
            }
        });
       // Button btn_add_alerte = (Button) findViewById(R.id.fab_alerte);

     /*   btn_add_alerte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/
    }
}
