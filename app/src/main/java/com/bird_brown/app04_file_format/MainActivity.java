package com.bird_brown.app04_file_format;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {    // 起動時に呼び出されるメソッド
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 書き込みボタンをリスナ登録する
        Button writeButton = (Button)findViewById(R.id.button1);
        writeButton.setOnClickListener(this);

        // 読み込みボタンをリスナ登録する
        Button readButton = (Button)findViewById(R.id.button2);
        readButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {    // ボタンが押された時に呼び出されるメソッド
        int id = view.getId();      // 押されたボタンのリソースIDを取得する

        if (id == R.id.button1) {
            writeFile();
        } else if (id == R.id.button2) {    // 読込ボタンを押した時の処理
            readFile();     // 読込処理を行う
        }
    }


    private void writeFile() {
        EditText edit = (EditText)findViewById(R.id.editText1);
        String moji = edit.getText().toString();

        String result = "書き込めました。";

        try {
            FileOutputStream fos = openFileOutput("file.txt",MODE_APPEND);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

            bw.write(moji + "\n");

            bw.close();
        } catch (Exception e) {
            result = "書き込みに失敗しました。";
        } finally {
            Toast t = Toast.makeText(this, result, Toast.LENGTH_SHORT);
            t.show();

            edit.setText("");
        }
    }

    private void readFile() {
        // 読み込んだ文字列をトーストに表示する変数の宣言
        String result = "";

        try {
            // 入力ストリームの生成
            FileInputStream fis = openFileInput("file.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));

            // ファイルから1行づつデータを取得し変数resultに追加する
            String line;

            while ((line = br.readLine()) != null) {
                result += line + "\n";
            }

            // 入力ストリームを閉じる
            br.close();
        } catch (Exception e) {
            result = "読み込みに失敗しました。";
        } finally {
            //トーストで読み込み結果を表示
            Toast t = Toast.makeText(this, result, Toast.LENGTH_SHORT);
            t.show();
        }
    }
}
