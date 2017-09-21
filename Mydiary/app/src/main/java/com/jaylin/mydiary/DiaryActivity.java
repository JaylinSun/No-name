package com.jaylin.mydiary;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Time;
import java.util.Calendar;

public class DiaryActivity extends AppCompatActivity {
    private EditText DiaryeditText;
    private Button button;
    final String FILENAME = "MyDiary.txt";
    private FileOutputStream out;
    private FileInputStream in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);
        init();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    out=openFileOutput(FILENAME,Context.MODE_PRIVATE);
                    out.write(DiaryeditText.getText().toString().getBytes());

                    Toast.makeText(DiaryActivity.this, "保存成功", Toast.LENGTH_LONG).show();
                }catch (FileNotFoundException e){
                    e.printStackTrace();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        });
    }

    private void init() {
        DiaryeditText = (EditText) findViewById(R.id.editText3);
        button = (Button) findViewById(R.id.save);
        try {
            in = openFileInput(FILENAME);
            ByteArrayOutputStream bou = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length = 0;
            while ((length = in.read(buffer)) != -1) {
                bou.write(buffer, 0, length);
            }
            DiaryeditText.setText(new String(bou.toByteArray()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Calendar c = Calendar.getInstance();
        DiaryeditText.append("\n" + c.get(Calendar.YEAR) + "年-" +
                c.get(Calendar.MONTH) + "月—" + c.get(Calendar.DAY_OF_MONTH) + "日");

    }
}
