package com.caensup.jcalculette.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.caensup.jcalculette.R;
import com.caensup.jcalculette.modele.Calculette;
import com.caensup.jcalculette.modele.CalculetteException;
import com.caensup.jcalculette.observateurs.IObservateur;
import com.caensup.jcalculette.observateurs.Observable;

public class VueCalculette extends AppCompatActivity implements IObservateur {

    /** Le modele Calculette est connue par la vue.
     *  Le modele Calculette ne connait pas la vue et peut être distribue sans elle
     *  La communication du modèle vers la vue se fait grace au patron Obervable/Observateurs
     */
    private Calculette calculette = new Calculette();
    private EditText etOperande;
    private TextView tvPile;
    private Button btnEmpiler;
    private Button btnPlus;
    private Button btnMinus;
    private Button btnMultiply;
    private Button btnDivide;
    private Button btnClear;

    private ProgressBar pbTaillePile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculette_layout);
        // On branche le mécanisme d'observation :
        // la vue this observe le modele observable calculette
        calculette.ajouterObservateur( this );

        etOperande = findViewById( R.id.etOperande );
        tvPile = findViewById( R.id.tvPile );
        btnEmpiler = findViewById( R.id.btnEmpiler );
        btnPlus = findViewById( R.id.btnPlus );
        btnMinus = findViewById( R.id.btnMinus );
        btnMultiply = findViewById( R.id.btnMultiply );
        btnDivide = findViewById( R.id.btnDivide );
        btnClear = findViewById( R.id.btnClear );
        pbTaillePile = findViewById( R.id.progressBar );

        btnEmpiler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Si etOperande ne contient pas un entier on empile pas et on
                // previent l'utilisateur avec un Toast Long
                try {
                    int operande = Integer.parseInt(etOperande.getText().toString());
                    calculette.enter(operande);
                } catch (CalculetteException ce) {
                    Toast.makeText( VueCalculette.this,"Exception du modele Calculette", Toast.LENGTH_LONG).show();
                } catch (NumberFormatException nfe) {
                    Toast.makeText( VueCalculette.this,"Entier Attendu\nMauvais format", Toast.LENGTH_LONG).show();
                }
                etOperande.setText("");
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculette.clear();
            }
        });

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    calculette.add();
                } catch (CalculetteException e) {
                    Toast.makeText( VueCalculette.this,"Exception du modele Calculette", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    calculette.sub();
                } catch (CalculetteException e) {
                    Toast.makeText( VueCalculette.this,"Exception du modele Calculette", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    calculette.mul();
                } catch (CalculetteException e) {
                    Toast.makeText( VueCalculette.this,"Exception du modele Calculette", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    calculette.div();
                } catch (CalculetteException e) {
                    Toast.makeText( VueCalculette.this,"Exception du modele Calculette", Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    @Override
    public void ecouterChangementEtat(Observable obs, Object etat) {
        Log.i("CALCUL", etat.toString());
        if ( etat instanceof  String ) {
            this.tvPile.setText((String) etat);
            pbTaillePile.setProgress( calculette.size());
        }
    }
}