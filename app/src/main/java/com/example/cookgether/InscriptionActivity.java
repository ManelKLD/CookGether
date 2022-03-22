package com.example.cookgether;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InscriptionActivity extends AppCompatActivity {

    EditText editNom, editPrenom, editMail, editMdp;
    Button DBins;

    DBHandler DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        //editNom = (EditText) findViewById(R.id.nomIns);
        //editPrenom = (EditText) findViewById(R.id.prenomIns);
        editMail = (EditText) findViewById(R.id.mailIns);
        editMdp = (EditText) findViewById(R.id.mdpIns);
        DBins = (Button) findViewById(R.id.btnIns);
        DB = new DBHandler(this);

        DBins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String nom = editNom.getText().toString();
               // String prenom = editPrenom.getText().toString();
                String mail = editMail.getText().toString();
                String mdp = editMdp.getText().toString();

                if (/*nom.equals("") || prenom.equals("") || */ mail.equals("") || mdp.equals("")) {
                    Toast.makeText(InscriptionActivity.this, "Veuillez remplir les champs", Toast.LENGTH_SHORT).show();
                }else{
                    Boolean checkClient = DB.checkclientMail(mail);
                    if (checkClient==false) {
                        Boolean insert = DB.insertData(mail, mdp);
                        if (insert == true) {
                            Toast.makeText(InscriptionActivity.this, "Inscription réussi", Toast.LENGTH_SHORT).show();
                            Intent intentAccueil = new Intent(getApplicationContext(), AccueilActivity.class);
                            startActivity(intentAccueil);
                        }
                    }else{
                        Toast.makeText(InscriptionActivity.this, "Cet utilsateur existe déja", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });

    }

}