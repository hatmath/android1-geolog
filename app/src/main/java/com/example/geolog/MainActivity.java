package com.example.geolog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

// Cours du 5 oct
//      Création de /main/res/menu
//          Création de /main/res/menu/menu_main.xml
//              Ajout de menu (ou ActionBar) avec des items
//              Création d'un Drawable pour que un item du menu soit représenté par un icone

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_parametres) {
            Toast.makeText(this, "Les paramètres", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.action_mail) {
            /*
            Ouvrir une autre activité
            Envoyer un courriel (zone de texte + bouton Envoyer)
            Mettre un message dans les log et sur l'app que le mail a été envoyé
            Revenir à l'activité principale
             */
            Toast.makeText(this, "Courriel envoyé", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}

