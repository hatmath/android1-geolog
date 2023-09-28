package com.example.geolog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.sql.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<GeologInfo> geologInfos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button soumission = (Button) findViewById(R.id.soumission_button);

        soumission.setOnClickListener(view -> {

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

            geologInfos.add(geologInfo);
            GeologInfo lastItem = geologInfos.get(geologInfos.size() - 1);

            new AlertDialog.Builder(view.getContext())
                    .setTitle("Ajouté au tableau de valeurs")
                    .setMessage(lastItem.toString())
                    .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                        // Faites quelque chose lorsque le bouton est cliqué
                    })
                    .show();

            String geoLogInfosStr = "";
            for (GeologInfo item : geologInfos) {
                geoLogInfosStr += item.toString();
            }

            new AlertDialog.Builder(view.getContext())
                    .setTitle("Contenu du tableau de valeurs")
                    .setMessage(geoLogInfosStr)
                    .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                        // Faites quelque chose lorsque le bouton est cliqué
                    })
                    .show();

        });
    }
}

