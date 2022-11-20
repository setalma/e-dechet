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
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class InscriptionActivity  extends AppCompatActivity {
    FirebaseFirestore db;
    private int nbclics = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registre);
        Button btninscription = (Button) findViewById(R.id.btn_inscrire);
        Button btnconnectervous = (Button) findViewById(R.id.btn_connectez_vous);
        db= FirebaseFirestore.getInstance();
        CollectionReference ref=db.collection("utilisateurs");
        EditText nom=(EditText) findViewById(R.id.entrer_votre_nom_complet) ;
        EditText email=(EditText) findViewById(R.id.entre_votre_email) ;
        EditText password=(EditText) findViewById(R.id.entrer_votre_mot_de_pass) ;
        EditText conf=(EditText) findViewById(R.id.confirmer_votre_mot_de_passe);

        btninscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nbclics++;
                System.out.println("Vous avez clique[[[[[[[[[[[[[ "+nbclics+" ]]]]]]]]]]]]]]]]]]]]]]]]fois sur le bouton inscription");
                //register.setText(String.format("Vous avez cliqué %d fois", nbclics));

                String nomC = (String) nom.getText().toString();
                String mail =(String)email.getText().toString();
                String mdp=(String)password.getText().toString();
                String confirm =(String)conf.getText().toString();
                System.out.println("bqbqbqbqbqbbqbbqb" + nom+mail+mdp+conf);
                User u=new User(nomC,mail,mdp,confirm);
                Intent order = new Intent(getApplicationContext(),Accueil.class);
                if(mdp.equals(confirm)){
                    ref.document().set(u)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(getApplicationContext()," Vos donnees ont bien ete enregistrees",Toast.LENGTH_LONG).show();
                                    startActivity(order);

                                }
                                })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getApplicationContext()," Vos donnees ont bien ete enregistrees",Toast.LENGTH_LONG).show();
                                }
                            });
                }else{
                    Toast.makeText(getApplicationContext(),"Les mots de passe doivent etre identiques",Toast.LENGTH_LONG).show();
                }
            }
        });

        btnconnectervous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent order2 = new Intent(getApplicationContext(),Login_Activity.class);
                startActivity(order2);
            }
        });
    }
}
