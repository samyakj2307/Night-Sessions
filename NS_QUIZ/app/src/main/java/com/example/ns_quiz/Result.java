package com.example.ns_quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.ns_quiz.questionpage.score;

public class Result extends AppCompatActivity {

    TextView res;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        res = findViewById(R.id.textView8);

        res.setText(String.valueOf(score));
    }
}