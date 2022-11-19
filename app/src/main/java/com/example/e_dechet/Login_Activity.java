package com.example.e_dechet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Login_Activity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button btnConnexion = (Button) findViewById(R.id.btn_connexion);
        Button btninscrivezvous = (Button) findViewById(R.id.btn_inscrivez_vous2);

        btnConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent order =  new Intent(getApplicationContext(),Accueil.class);
                startActivity(order);
            }
        });

        btninscrivezvous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent order2 = new Intent(getApplicationContext(),InscriptionActivity.class);
                startActivity(order2);
            }
        });

    }
}
