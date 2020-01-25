package com.bird_brown.app04_file_format;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button writeButton = (Button)findViewById(R.id.button1);
        writeButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.button1) {
            writeFile();
        }
    }


    private void writeFile() {
        EditText edit = (EditText)findViewById(R.id.editText1);
        String moji = edit.getText().toString();

        String result = "書き込めました。";

        try {
            FileOutputStream fos = openFileOutput("file.text",MODE_APPEND);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

            bw.write(moji + "¥n");

            bw.close();
        } catch (Exception e) {
            result = "書き込みに失敗しました。";
        } finally {
            Toast t = Toast.makeText(this, result, Toast.LENGTH_SHORT);
            t.show();

            edit.setText("");
        }
    }
}

