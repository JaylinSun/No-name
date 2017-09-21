package com.example.jaylin.study.Activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jaylin.study.R;

public class MainActivity extends AppCompatActivity {
    private Button btnGetMoney;
    private TextView tvGetMoney;
    private Button btnLoseMoney;
    private EditText etGoalMoney;
    private RadioGroup rgSuvery;
    private CheckBox cbStudy, cbGL, cbBZ;
    private Button btnSwitch;
    private int money = 0;
    private Button btnJp;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about:
                Toast.makeText(MainActivity.this, "about", Toast.LENGTH_SHORT).show();
                break;
            case R.id.more:
                Toast.makeText(MainActivity.this, "more", Toast.LENGTH_SHORT).show();
                break;
            case R.id.jx:
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:+8615278886995"));
                startActivity(intent);
            default:
        }
        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnGetMoney = (Button) findViewById(R.id.btnGetMoney);
        btnLoseMoney = (Button) findViewById(R.id.btnLoseMoney);
        tvGetMoney = (TextView) findViewById(R.id.tvGetMoney);
        etGoalMoney = (EditText) findViewById(R.id.etGoalMoney);
        rgSuvery = (RadioGroup) findViewById(R.id.rgSuvery);
        cbStudy = (CheckBox) findViewById(R.id.cbStudy);
        cbGL = (CheckBox) findViewById(R.id.cbGF);
        cbBZ = (CheckBox) findViewById(R.id.cbBZ);
        btnSwitch = (Button) findViewById(R.id.btnSwitch);
        btnJp = (Button) findViewById(R.id.btnJp);

        btnGetMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strIuputMoney = etGoalMoney.getText().toString().trim();
                int imoney = Integer.parseInt(strIuputMoney);
                if (imoney <= money) {
                    Toast.makeText(MainActivity.this, "你已经完成了目标", Toast.LENGTH_SHORT).show();
                } else {
                    money++;
                    tvGetMoney.setText("你已经得到了" + money + "元");
                }
            }
        });
        btnLoseMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (money == 0) {
                    Toast.makeText(MainActivity.this, "你已经没有钱了", Toast.LENGTH_SHORT).show();
                } else {
                    money--;
                    tvGetMoney.setText("你已经得到了" + money + "元");
                }
            }
        });

        rgSuvery.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId) {
                    case R.id.rbNo:
                        Toast.makeText(MainActivity.this, "妈的智障，你不是谁是！", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rbYes:
                        Toast.makeText(MainActivity.this, "还有点自知之明！", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rbYou:
                        Toast.makeText(MainActivity.this, "妈的智障，搞事情吗？", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        cbStudy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(MainActivity.this, "这就是你没有女朋友的理由？", Toast.LENGTH_SHORT).show();
                }

            }
        });
        cbGL.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(MainActivity.this, "你会有女朋友？", Toast.LENGTH_SHORT).show();
                }
            }
        });
        cbBZ.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(MainActivity.this, "这就是你没有女朋友的理由？", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnJp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btnJp:
                        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                        dialog.setTitle("Warning");
                        dialog.setMessage("真的要炸掉日本吗？");
                        dialog.setCancelable(false);
                        dialog.setPositiveButton("是的", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                final ProgressDialog PD = ProgressDialog.show(MainActivity.this, "", "核弹发射中，请等待", true, true);
                                Thread t = new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            Thread.sleep(10000);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        PD.dismiss();
                                    }
                                });
                                t.start();
                                PD.setCancelable(false);
                            }
                        });
                        dialog.setNegativeButton("不是", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this, "否定无效，自动启动导弹系统", Toast.LENGTH_LONG).show();
                                final ProgressDialog PD = ProgressDialog.show(MainActivity.this, "", "核弹发射中，请等待", true, true);
                                Thread t = new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            Thread.sleep(10000);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        PD.dismiss();
                                    }
                                });
                                t.start();
                                PD.setCancelable(false);
                            }
                        });
                        dialog.show();
                        break;
                    default:
                        break;

                }
            }
        });
    }
}
