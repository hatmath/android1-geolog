package com.example.geolog;

import static android.app.PendingIntent.getActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

// Cours 27 sept
// récupérer toutes les variables de la vue
// Stocker ces variables dans un tableau
// Informer l'utilisateur que les valeurs ont été enregistrées correctement en les affichant
//      Toast, Log
// Lecture: Spinner

// Cours 28 sept
// Théorie
// Stockage dans les préférences
//      android.content.SharedPreferences
// Stockage interne: utiliser un fichier local
//      ouvrir,lire,écrire,fermer,récupéré l'info
// Stocakge externe
//      périphérique externe
// Les bases de données (si on désinstalle l'app on ne pert pas l'info)
//      SQL
// Connexion au réseau
//      Azure, AWS
//
// À faire
// Classe Voyage
// Bouton avec état Démarrer bleu, Stop rouge, Arriver vert
// La longueur du tableau n'est pas connu à l'avance
// Le spinner nous permet d'avoir les lieux visités
// Les voyages doivent être enregistrée dans un fichier
//

enum EtatVue {STOPPE,ENCOURS}
public class MainActivity extends AppCompatActivity {
    ArrayList<Voyage> voyages = new ArrayList<>();
    EtatVue etat;
    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Lecture de la liste d'objets GeologInfo depuis le fichier JSON
//        geologInfos = JsonFileHandler.readGeologInfoListFromJson(this);

//        Spinner spinner = (Spinner) findViewById(R.id.spinner);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.mode_de_deplacement, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);

        etat = EtatVue.ENCOURS;
        Button actionBtn = (Button) findViewById(R.id.action_btn);

        actionBtn.setOnClickListener(view -> {

            if (etat == EtatVue.ENCOURS) {
                Voyage voyage = new Voyage();
                EditText etNumberHolder;
                String strNumberHolder;
                try {
                    etNumberHolder = (EditText) findViewById(R.id.jour_et);
                    strNumberHolder = etNumberHolder.getText().toString();
                    voyage.setJour(strNumberHolder.isEmpty() ? 0 : Integer.parseInt(strNumberHolder));

                    etNumberHolder = (EditText) findViewById(R.id.mois_et);
                    strNumberHolder = etNumberHolder.getText().toString();
                    voyage.setMois(strNumberHolder.isEmpty() ? 0 : Integer.parseInt(strNumberHolder));

                    etNumberHolder = (EditText) findViewById(R.id.annee_et);
                    strNumberHolder = etNumberHolder.getText().toString();
                    voyage.setAnnee(strNumberHolder.isEmpty() ? 0 : Integer.parseInt(strNumberHolder));

                    etNumberHolder = (EditText) findViewById(R.id.kilometre_depart_et);
                    strNumberHolder = etNumberHolder.getText().toString();
                    voyage.setKilometreDepart(strNumberHolder.isEmpty() ? 0 : Integer.parseInt(strNumberHolder));

                    etNumberHolder = (EditText) findViewById(R.id.kilometre_arrivee_et);
                    strNumberHolder = etNumberHolder.getText().toString();
                    voyage.setKilometreArrivee(strNumberHolder.isEmpty() ? 0 : Integer.parseInt(strNumberHolder));

                } catch (NumberFormatException e) {
                    Toast.makeText(this, R.string.nombre_invalide, Toast.LENGTH_SHORT).show();
                }

                etNumberHolder = (EditText) findViewById(R.id.lieu_depart_et);
                strNumberHolder = etNumberHolder.getText().toString();
                voyage.setLieuDepart(strNumberHolder);

                etNumberHolder = (EditText) findViewById(R.id.lieu_arrivee_et);
                strNumberHolder = etNumberHolder.getText().toString();
                voyage.setLieuArrivee(strNumberHolder);

                voyages.add(voyage);
                Voyage lastItem = voyages.get(voyages.size() - 1);

                // Ajoutez vos objets Voyage à la liste voyages
                VoyageFileUtil.writeVoyages(this, voyages);

                Toast myToast = Toast.makeText(this, lastItem.toString(), Toast.LENGTH_SHORT);
                myToast.show();
                changerEtat();
            } else {
                changerEtat();
            }

        });

        Button listeBtn = (Button) findViewById(R.id.voyages_btn);

        listeBtn.setOnClickListener(view -> {

            // Lire des voyages depuis un fichier
            voyages = (ArrayList)VoyageFileUtil.readVoyages(this);

            String voyagesStr = "";
            for (Voyage item : voyages) {
                voyagesStr += item.toString();
            }

            new AlertDialog.Builder(view.getContext())
                    .setTitle(R.string.voyages_list_title)
                    .setMessage(voyagesStr)
                    .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                        // Faites quelque chose lorsque le bouton est cliqué
                    })
                    .show();

        });
    }

    private void changerEtat() {

        Button action = (Button) findViewById(R.id.action_btn);

        if (etat == EtatVue.ENCOURS) {
            etat = EtatVue.STOPPE;
            action.setText(R.string.etat_stoppe);
            action.setGravity(Gravity.LEFT);
        } else if (etat == EtatVue.STOPPE) {
            etat = EtatVue.ENCOURS;
            action.setText(R.string.etat_encours);
            action.setGravity(Gravity.LEFT);
        }

    }

}

