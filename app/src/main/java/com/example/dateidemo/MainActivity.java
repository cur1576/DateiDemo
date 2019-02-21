package com.example.dateidemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
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
    }

    private void save(String text) {
        try (FileOutputStream fos = openFileOutput(FILENAME, MODE_PRIVATE);
             OutputStreamWriter osw = new OutputStreamWriter(fos)) {
            osw.write(text);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ;
    }

    private String load() {
        return null;
    }
}
