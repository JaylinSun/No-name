package com.example.jaylin.study.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.jaylin.study.R;

public class CaptureActivity extends AppCompatActivity {
    private Button btnCapture;
    private ImageView ivCaptureIMG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture);
        btnCapture = (Button) findViewById(R.id.btnCapture);
        ivCaptureIMG = (ImageView) findViewById(R.id.ivCaptureIMG);

        btnCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent capIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(capIntent, 100);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(CaptureActivity.this, "拍照成功", Toast.LENGTH_SHORT).show();
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                ivCaptureIMG.setImageBitmap(bitmap);
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(CaptureActivity.this, "拍照不成功", Toast.LENGTH_SHORT).show();

            }
        }
    }
}
