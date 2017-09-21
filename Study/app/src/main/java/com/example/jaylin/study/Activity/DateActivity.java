package com.example.jaylin.study.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jaylin.study.R;

public class DateActivity extends AppCompatActivity {
    private TextView tvGetMessage;
    private Button btnReturnActivity;
    private EditText etBackMSG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);

        tvGetMessage = (TextView) findViewById(R.id.tvGetMessage);
        btnReturnActivity = (Button) findViewById(R.id.btnReturnActivity);
        etBackMSG = (EditText) findViewById(R.id.etBackMSG);

        String strgetmsg = getIntent().getStringExtra("joke");
        tvGetMessage.setText("收到的信息是：" + strgetmsg);



        btnReturnActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strBackMSG = etBackMSG.getText().toString();
//                String strgetmsg = getIntent().getStringExtra("joke");
//                if (strgetmsg.equals("joke")) {
                Intent intentData = new Intent();
                intentData.putExtra("ppx", strBackMSG);
                setResult(RESULT_OK, intentData);
                finish();
//                } else {
//                    Intent intentData = new Intent();
//                    setResult(RESULT_CANCELED, intentData);
//                    finish();
//                }
            }
        });


    }
}
