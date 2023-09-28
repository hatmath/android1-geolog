package com.example.geolog;

import static android.app.PendingIntent.getActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
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

// récupérer toutes les variables de la vue
// Stocker ces variables dans un tableau
// Informer l'utilisateur que les valeurs ont été enregistrées correctement en les affichant
// Lecture: Spinner

public class MainActivity extends AppCompatActivity {
    ArrayList<GeologInfo> geologInfos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.mode_de_deplacement, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Button soumissionBtn = (Button) findViewById(R.id.soumission_button);

        soumissionBtn.setOnClickListener(view -> {

            GeologInfo geologInfo = new GeologInfo();
            EditText distanceET = (EditText) findViewById(R.id.kilometre_txt);
            geologInfo.setDistance(distanceET.getText().toString().toUpperCase());

            EditText lieuET = (EditText) findViewById(R.id.lieu_visite_txt);
            geologInfo.setLieu(lieuET.getText().toString().toUpperCase());

            DatePicker dateDP = (DatePicker) findViewById(R.id.datePicker);
            int day = dateDP.getDayOfMonth();
            int month = dateDP.getMonth(); // Les mois commencent à 0, janvier est 0, février est 1, etc.
            int year = dateDP.getYear();
            String formattedDate = String.format("%02d/%02d/%04d", day, month + 1, year);
            geologInfo.setDate(formattedDate);

            geologInfo.setModeDeDeplacement((String)spinner.getSelectedItem());

            geologInfos.add(geologInfo);
            GeologInfo lastItem = geologInfos.get(geologInfos.size() - 1);

            Toast myToast = Toast.makeText(this, lastItem.toString(), Toast.LENGTH_LONG);
            myToast.show();

        });

        Button listeBtn = (Button) findViewById(R.id.liste_button);

        listeBtn.setOnClickListener(view -> {

            String geoLogInfosStr = "";
            for (GeologInfo item : geologInfos) {
                geoLogInfosStr += item.toString();
            }

            new AlertDialog.Builder(view.getContext())
                    .setTitle("Liste des entrées")
                    .setMessage(geoLogInfosStr)
                    .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                        // Faites quelque chose lorsque le bouton est cliqué
                    })
                    .show();

        });
    }
}

