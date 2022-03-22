package com.example.cookgether;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class connexionActivity extends AppCompatActivity implements View.OnClickListener {

    TextView redirectIns;
    Intent redirectIntentIns;

    //database Variables
    EditText editMail, editMdp;
    Button DBcon;

    SQLiteDatabase database;
    DBHandler DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        redirectIns = (TextView) findViewById(R.id.redirectToIns);
        redirectIns.setOnClickListener(this);

        editMail = (EditText) findViewById(R.id.mailCo);
        editMdp = (EditText) findViewById(R.id.mdpCo);
        DBcon = (Button) findViewById(R.id.btnCon);

        DB = new DBHandler(this);


        DBcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail = editMail.getText().toString();
                String mdp = editMdp.getText().toString();


                if (mail.equals("")||mdp.equals("")){
                    Toast.makeText(connexionActivity.this, "Veuillez remplir tout les champs", Toast.LENGTH_SHORT).show();
                }
                else {
                    Boolean checkClientExist = DB.checkclientMailMdp(mail,mdp);
                    if (checkClientExist==true) {
                        Toast.makeText(connexionActivity.this, "Connexion réussie", Toast.LENGTH_SHORT).show();
                        Intent intentAccueil = new Intent(getApplicationContext(), AccueilActivity.class);
                        startActivity(intentAccueil);

                    }
                    else{
                        Toast.makeText(connexionActivity.this, "Votre mail ou mot de passe sont incorrects", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.redirectToIns) {
            redirectIntentIns = new Intent(connexionActivity.this, InscriptionActivity.class);
            startActivity(redirectIntentIns);
        }
    }
}