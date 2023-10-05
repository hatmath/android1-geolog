package com.example.geolog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

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
// Cours du 4 oct
// Cours du 5 oct

enum EtatVue {STOPPE,ENCOURS}
public class MainActivity extends AppCompatActivity {
    ArrayList<Voyage> voyages = new ArrayList<>();
    EtatVue etat;
    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fillSpinner();
        resetAllEditTextFields((ViewGroup) findViewById(R.id.root_layout));

        etat = EtatVue.ENCOURS;

        Button actionBtn = (Button) findViewById(R.id.action_btn);
        actionBtn.setOnClickListener(view -> {

            if (!oneEditTextFieldsIsEmpty((ViewGroup) findViewById(R.id.root_layout))) {

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
                    fillSpinner();
                    resetAllEditTextFields((ViewGroup) findViewById(R.id.root_layout));
                }
            } else {
                Toast myToast = Toast.makeText(this, R.string.edittext_empty, Toast.LENGTH_SHORT);
                myToast.show();
            }

        });

        Button effacerBtn = (Button) findViewById(R.id.effacer_btn);
        effacerBtn.setOnClickListener(view -> {
            resetAllEditTextFields((ViewGroup) findViewById(R.id.root_layout));
        });


        Button listeBtn = (Button) findViewById(R.id.voyages_btn);
        listeBtn.setOnClickListener(view -> {

            // Lire des voyages depuis un fichier
            voyages = (ArrayList) VoyageFileUtil.readVoyages(this);

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
            action.setBackgroundColor(ContextCompat.getColor(this, R.color.stoppe));
            action.setGravity(Gravity.LEFT);
        } else if (etat == EtatVue.STOPPE) {
            etat = EtatVue.ENCOURS;
            action.setText(R.string.etat_encours);
            action.setBackgroundColor(ContextCompat.getColor(this, R.color.encours));
            action.setGravity(Gravity.CENTER);
        }

    }

    private void resetAllEditTextFields(ViewGroup viewGroup) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View child = viewGroup.getChildAt(i);
            if (child instanceof EditText) {
                ((EditText) child).setText("");
            } else if (child instanceof ViewGroup) {
                resetAllEditTextFields((ViewGroup) child);
            }
        }
    }

    private boolean oneEditTextFieldsIsEmpty(ViewGroup viewGroup) {

        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View child = viewGroup.getChildAt(i);
            if (child instanceof EditText) {
                if (!((EditText) child).getText().toString().isEmpty()) {
                    return false;
                }
            } else if (child instanceof ViewGroup) {
                oneEditTextFieldsIsEmpty((ViewGroup) child);
            }
        }
        return true;
    }

    String previousSelection = "";

    private void fillSpinner() {

        voyages = (ArrayList) VoyageFileUtil.readVoyages(this);

        String[] datesArray = new String[voyages.size() + 1]; // +1 pour l'option non sélectionnée
        datesArray[0] = ""; // Option non sélectionnée

        for (int i = 0; i < voyages.size(); i++) {
            datesArray[i + 1] = voyages.get(i).date(); // Commencez à partir de l'index 1
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, datesArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner = findViewById(R.id.spinner);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedDate = (String) parentView.getItemAtPosition(position);
                if (position != 0 && !selectedDate.equals(previousSelection)) {
                    previousSelection = selectedDate;
                    remplirWidgetsAvecVoyage(voyages.get(position - 1));
                    Toast.makeText(getApplicationContext(), "Date sélectionnée : " + selectedDate, Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // L'action à effectuer si rien n'est sélectionné (optionnel)
            }

        });

    }

    public void remplirWidgetsAvecVoyage(Voyage voyage) {
        EditText jourEditText = findViewById(R.id.jour_et);
        EditText moisEditText = findViewById(R.id.mois_et);
        EditText anneeEditText = findViewById(R.id.annee_et);
        EditText lieuDepartEditText = findViewById(R.id.lieu_depart_et);
        EditText lieuArriveeEditText = findViewById(R.id.lieu_arrivee_et);
        EditText kilometreDepartEditText = findViewById(R.id.kilometre_depart_et);
        EditText kilometreArriveeEditText = findViewById(R.id.kilometre_arrivee_et);

        // Remplir les widgets EditText avec les données de l'objet Voyage
        jourEditText.setText(String.valueOf(voyage.getJour()));
        moisEditText.setText(String.valueOf(voyage.getMois()));
        anneeEditText.setText(String.valueOf(voyage.getAnnee()));
        lieuDepartEditText.setText(voyage.getLieuDepart());
        lieuArriveeEditText.setText(voyage.getLieuArrivee());
        kilometreDepartEditText.setText(String.valueOf(voyage.getKilometreDepart()));
        kilometreArriveeEditText.setText(String.valueOf(voyage.getKilometreArrivee()));
    }

}

