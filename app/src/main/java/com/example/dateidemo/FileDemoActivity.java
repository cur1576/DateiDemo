package com.example.dateidemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FileDemoActivity extends AppCompatActivity {

    private static final String TAG = FileDemoActivity.class.getSimpleName();
    private static final String FILENAME = TAG + ".txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText editText = findViewById(R.id.edit);
        final Button load = findViewById(R.id.bt_load);
        load.setOnClickListener(v -> editText.setText(load()));
        final Button save = findViewById(R.id.bt_save);
        save.setOnClickListener(v -> save(editText.getText().toString()));
        final Button clear = findViewById(R.id.bt_clear);
        clear.setOnClickListener(v -> editText.setText(""));

        File dir = getFilesDir();
        Log.d(TAG, "onCreate: " + dir.getAbsolutePath());
    }

    private void save(String text) {
        try (FileOutputStream fos = openFileOutput(FILENAME, MODE_PRIVATE); // MODE_APPEND -> um text hinzuzufügen ohne die File zu lehre -> zb. für LOGs
             OutputStreamWriter osw = new OutputStreamWriter(fos)) {
            osw.write(text);
        } catch (IOException e) {
            Log.e(TAG, "save: Error",e );
        }
    }

    private String load() {
        StringBuilder stringBuilder = new StringBuilder();
        // statt einem InputStream(FILENAME) verwenden wir für Dateien einen openFileInput(FILENAME)
        try(BufferedReader br = new BufferedReader(new InputStreamReader(openFileInput(FILENAME)))){
            String line;
            // datei zeilenweise lesen
            while ((line = br.readLine())!=null){
                if(stringBuilder.length()>0){
                    // Zeilenumbruch einfügen
                    stringBuilder.append('\n');
                }
                // hinzufügen der Zeile / des Inhalts
                stringBuilder.append(line);
            }
        } catch (FileNotFoundException e) {
            Log.e(TAG, "Datei nicht gefunden. ", e);
        } catch (IOException e) {
            Log.e(TAG, "Error: ", e);
        }
        return stringBuilder.toString();
    }
}
