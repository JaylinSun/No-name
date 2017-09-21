package com.example.jaylin.study.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jaylin.study.R;

public class ActivityLifeCycle extends AppCompatActivity {
    private EditText etMessage;
    private Button btnMessage;
    private Button btnTC;
    private Button btnFinish;

//    private Button btnPZ;

    private Button btnSendBroadcast;
    private EditText etSendBroadcast;

    private int count = 0;
    private static final int RC_DATA_ACTIVITY = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle);

        if (savedInstanceState != null) {
            count = savedInstanceState.getInt("count");
            Toast.makeText(this, "count=" + count, Toast.LENGTH_SHORT).show();
        }

        btnMessage = (Button) findViewById(R.id.btnMessage);
        etMessage = (EditText) findViewById(R.id.etMessage);

        btnTC = (Button) findViewById(R.id.btnTC);
        btnFinish = (Button) findViewById(R.id.btnFinish);

        Button btnPZ=(Button)findViewById(R.id.btnPZ);



        btnSendBroadcast = (Button) findViewById(R.id.btnSendBroadcast);
        etSendBroadcast = (EditText) findViewById(R.id.etSendBroadcast);

        btnPZ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityLifeCycle.this, CaptureActivity.class);
                startActivity(intent);
            }
        });

        btnMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strmsg = etMessage.getText().toString();
                Intent intent = new Intent(ActivityLifeCycle.this, DateActivity.class);
                intent.putExtra("joke", strmsg);
                startActivityForResult(intent, RC_DATA_ACTIVITY);
            }
        });

        btnTC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityLifeCycle.this, DialogActivity.class);
                startActivity(intent);
            }
        });


        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnSendBroadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strMsg = etSendBroadcast.getText().toString();
                Intent broadcastintent = new Intent();
                broadcastintent.setAction("com.example.action.NOTIFY");
                broadcastintent.putExtra("msg", strMsg);
                sendBroadcast(broadcastintent);
            }
        });

        Log.d("SJL", "onCreate启动");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("SJL", "onStart启动");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("SJL", "onDestroy启动");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("SJL", "onCreate启动");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v("SJL", "onPause启动");
    }

    @Override
    protected void onResume() {
        super.onResume();

        count++;

        Log.w("SJL", "onResume启动");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("count", count);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case RC_DATA_ACTIVITY:
                if (resultCode == RESULT_OK) {
                    String strResult = data.getStringExtra("ppx");
                    Toast.makeText(ActivityLifeCycle.this, strResult, Toast.LENGTH_SHORT).show();
                } else if (resultCode == RESULT_CANCELED) {
                    Toast.makeText(ActivityLifeCycle.this, "没能获取返回结果", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }

    }
}
