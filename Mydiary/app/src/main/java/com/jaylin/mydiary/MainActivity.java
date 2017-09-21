package com.jaylin.mydiary;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText editText1, editText2;
    private Button btn;
    private ProgressBar progressBar;
    private CheckBox checkBox;
    private SharedPreferences preferences;
    private Handler handler;
    int pb;
    final int CONTINUE = 4, STOP = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        HANDLER();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText1.getText().toString().trim();
                String pwd = editText2.getText().toString().trim();
                if ((!name.equals("admin")) || (!pwd.equals("admin"))) {
                    Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_LONG).show();
                } else {
                    SharedPreferences.Editor editor = preferences.edit();
                    if (checkBox.isChecked()) {
                        editor.putString("USERNAME", name);
                        editor.putString("PWD", pwd);
                        editor.putBoolean("REMBERPWD", true);
                        editor.commit();
                    } else {
                        editor.putBoolean("REMBERPWD", false);
                        editor.commit();
                    }
                    editText1.setEnabled(false);
                    editText2.setEnabled(false);
                    btn.setEnabled(false);
                    progressBar.setVisibility(View.VISIBLE);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                for (int i = 0; i < 5; i++) {
                                    pb = (i + 1) * 20;
                                    Thread.sleep(1000);
                                    if (i == 4) {
                                        Message msg = new Message();
                                        msg.what = STOP;
                                        handler.sendMessage(msg);
                                        break;
                                    } else {
                                        Message msg = new Message();
                                        msg.what = CONTINUE;
                                        handler.sendMessage(msg);
                                    }
                                }
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }
                    }).start();
                }
            }
        });

    }

    private void HANDLER() {
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case CONTINUE:
                        if (!Thread.currentThread().isInterrupted()) {
                            progressBar.setProgress(pb);
                        }
                        break;
                    case STOP:
                        Intent intent = new Intent();
                        intent.setClass(MainActivity.this, DiaryActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    default:
                        break;
                }
                super.handleMessage(msg);
            }
        };
    }

    private void init() {
        editText1 = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);
        btn = (Button) findViewById(R.id.button);
        checkBox = (CheckBox) findViewById(R.id.checkBox);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        preferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        editText1.setText(preferences.getString("USERNAME", ""));
        if (preferences.getBoolean("REMBERPWD", false)) {
            editText2.setText(preferences.getString("PWD", ""));
        } else {
            editText2.setText("");
        }
        checkBox.setChecked(preferences.getBoolean("REMBERPWD", false));
        pb = 0;
        progressBar.setProgress(pb);
        progressBar.setMax(100);
    }
}
