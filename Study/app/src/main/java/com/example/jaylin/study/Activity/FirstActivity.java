package com.example.jaylin.study.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.jaylin.study.R;

public class FirstActivity extends AppCompatActivity {
    private Button btnFirst;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.first, menu);
        return true;
    }

    private Button btnSecond;
    private Button btnThird;
    private int flag=0;

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.Photo:
                Intent intent = new Intent(FirstActivity.this, CaptureActivity.class);
                startActivity(intent);
                Toast.makeText(FirstActivity.this,"sb",Toast.LENGTH_LONG).show();
                break;
            case R.id.Exit:
//                ProgressDialog PDExit = new ProgressDialog(FirstActivity.this);
//                PDExit.setMessage("正在退出中...");
//                PDExit.setTitle("退出");
//                PDExit.show();
//                PDExit.setCancelable(true);
                final ProgressDialog PD = ProgressDialog.show(FirstActivity.this, "", "正在退出中...", true, true);
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                            flag=1;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        PD.dismiss();

                    }
                });
                t.start();
                PD.setCancelable(false);
                if (flag==1){
                    finish();
                }
                break;
            default:
                //break;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        btnFirst = (Button) findViewById(R.id.btnFirst);
        btnSecond = (Button) findViewById(R.id.btnSecond);
        btnThird = (Button) findViewById(R.id.btnThird);

        btnFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentChange = new Intent(FirstActivity.this, MainActivity.class);
                startActivity(intentChange);
            }
        });
        btnSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentChange = new Intent(FirstActivity.this, SecondActivity.class);
                startActivity(intentChange);
            }
        });
        btnThird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentChange = new Intent(FirstActivity.this, ActivityLifeCycle.class);
                startActivity(intentChange);
            }
        });
    }
}
