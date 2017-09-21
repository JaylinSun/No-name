package com.example.jaylin.study.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.jaylin.study.R;

/**
 * Created by Jaylin on 2017/2/18.
 */

public class  SecondActivity extends AppCompatActivity {

    private RatingBar rtbScore;
    private SeekBar skbScore;
    private TextView tvScore;

    private ImageButton ibTime;
    private ProgressBar pbTime;

    private DatePicker dpPicker;
    private TimePicker tpPicker;

    private Button btnBack;
    private Button btnALC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        rtbScore = (RatingBar) findViewById(R.id.rtbScore);
        skbScore = (SeekBar) findViewById(R.id.skbScore);
        tvScore = (TextView) findViewById(R.id.tvScore);

        pbTime = (ProgressBar) findViewById(R.id.pbTime);
        ibTime = (ImageButton) findViewById(R.id.ibTime);

        dpPicker=(DatePicker)findViewById(R.id.dpPicker);
        tpPicker=(TimePicker)findViewById(R.id.tpPicker);

        btnBack=(Button)findViewById(R.id.btnBack);

        btnALC=(Button)findViewById(R.id.btnALC);

        btnALC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SecondActivity.this,ActivityLifeCycle.class);
                startActivity(intent);
            }
        });

        rtbScore.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(SecondActivity.this, "您的评分是：" + rating, Toast.LENGTH_SHORT).show();
            }
        });

        skbScore.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvScore.setText("你成为傻逼的进度：" + progress + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        ibTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pbTime.setVisibility(View.VISIBLE);

                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(5);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
                thread.start();
            }
        });
        dpPicker.setCalendarViewShown(false);
        dpPicker.init(2017, 1, 18, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Toast.makeText(SecondActivity.this,"你选取的日期为："+year+"-"+(monthOfYear+1)+"-"+dayOfMonth,Toast.LENGTH_SHORT).show();
            }
        });
        tpPicker.setIs24HourView(true);
        tpPicker.setCurrentHour(20);
        tpPicker.setCurrentMinute(05);
        tpPicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                Toast.makeText(SecondActivity.this, "发生的时间是："+hourOfDay+":"+minute , Toast.LENGTH_SHORT).show();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SecondActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
