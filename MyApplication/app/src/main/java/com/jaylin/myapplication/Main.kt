package com.jaylin.myapplication

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView

/**
 * Created by Jaylin on 2017/9/7.
 */

class Main : AppCompatActivity() {
    private var btn: Button? = null
    private var text:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn = findViewById(R.id.button) as Button


    }


}
