package com.example.geolog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class SendMailActivity extends AppCompatActivity {

    private EditText editTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_mail);

        editTextMessage = findViewById(R.id.editTextMessage);
        Button btnSend = findViewById(R.id.btnSend);

        btnSend.setOnClickListener(view -> {
            logMessage(editTextMessage.getText().toString());
            Toast.makeText(this, "Courriel envoyé", Toast.LENGTH_SHORT).show();
            finish();
        });
    }

    private void logMessage(String message) {
        try (FileOutputStream fos = openFileOutput("log.txt", MODE_APPEND);
             PrintWriter writer = new PrintWriter(new OutputStreamWriter(fos))) {
            writer.println(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
