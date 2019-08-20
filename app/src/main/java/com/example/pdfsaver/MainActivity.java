package com.example.pdfsaver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText nameText, contactText;
    private Button saveButton;
    static ArrayList<DataModel> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
        onListeners();
    }

    private void initialize() {
        nameText = findViewById(R.id.name);
        contactText = findViewById(R.id.contact);
        saveButton = findViewById(R.id.saveButton);
    }

    private void onListeners() {
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.add(new DataModel(nameText.getText().toString(),contactText.getText().toString()));
                PDFUtility pdf = new PDFUtility();
                pdf.pdfSaver();
                clearField();
            }
        });
    }

    private void clearField(){
        nameText.setText("");
        contactText.setText("");
    }
}