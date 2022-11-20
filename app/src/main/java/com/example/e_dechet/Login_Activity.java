package com.example.e_dechet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class Login_Activity extends AppCompatActivity {
    FirebaseFirestore db;
    String nomcompletglobal ="";
    String emailglobal="";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db= FirebaseFirestore.getInstance();
        CollectionReference ref=db.collection("utilisateurs");
        Button btnConnexion = (Button) findViewById(R.id.btn_connexion);
        Button btninscrivezvous = (Button) findViewById(R.id.btn_inscrivez_vous2);

        EditText email = (EditText) findViewById(R.id.entrer_votre_email);
        EditText mdp = (EditText) findViewById(R.id.entrer_votre_mot_de_passe) ;

        btnConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent order =  new Intent(getApplicationContext(),Accueil.class);
                String emailstr= email.getText().toString();
                String mdpstr = mdp.getText().toString();
                final boolean[] trouve = {false};

                User user = new User(emailstr,mdpstr);
                ref.whereEqualTo("email",emailstr).whereEqualTo("password",mdpstr).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        int i=0;
                        String nomcomplet="";
                        String email="";
                        if (queryDocumentSnapshots != null) {
                            for (DocumentChange d : queryDocumentSnapshots.getDocumentChanges()) {
                                if (d.getType() == DocumentChange.Type.ADDED) {
                                    System.out.println(d.getDocument().getId());
                                    User u = d.getDocument().toObject(User.class);
                                    System.out.println(u.getEmail()+u.getNom_complet());
                                  trouve[0] =true;
                                  nomcomplet= u.getNom_complet();
                                  email = u.getEmail();


                                } else {
                                    Toast.makeText(getApplicationContext(), "Mot de passe ou email incorect", Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                        if (i==0) {
                            order.putExtra(nomcompletglobal,nomcomplet);
                            order.putExtra(emailglobal, email);
                            startActivity(order);
                        }

                }}).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),"Mot de passe ou email incorrect",Toast.LENGTH_LONG).show();
                    }
                });

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
