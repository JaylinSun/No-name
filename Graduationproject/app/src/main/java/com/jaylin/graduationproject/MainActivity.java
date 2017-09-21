package com.jaylin.graduationproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText etname, etpwd;
    private Button btnlog;
    private SharedPreferences preferences;
    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        btnlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etname.getText().toString().trim();
                String pwd = etpwd.getText().toString().trim();
                if ((!name.equals("1")) || (!pwd.equals("1"))) {
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
                    Intent intent=new Intent(MainActivity.this,Activity2.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }


    private void init() {
        etname = (EditText) findViewById(R.id.editText1);
        etpwd = (EditText) findViewById(R.id.editText2);
        btnlog = (Button) findViewById(R.id.button);
        checkBox = (CheckBox) findViewById(R.id.checkBox);
        preferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        etname.setText(preferences.getString("USERNAME", ""));
        if (preferences.getBoolean("REMBERPWD", false)) {
            etpwd.setText(preferences.getString("PWD", ""));
        } else {
            etpwd.setText("");
        }
        checkBox.setChecked(preferences.getBoolean("REMBERPWD", false));

    }
}
