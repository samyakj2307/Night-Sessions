package com.example.ns_quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class questionpage extends AppCompatActivity {

    TextView question;
    Button optA, optB, optC, optD, next;
    TriviaQuizHelper triviaQuizHelper;
    TriviaQuestion currentQuestion;
    List<TriviaQuestion> list;
    int qid = 0;
    public static int score;
    String answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionpage);

        question = findViewById(R.id.textView5);
        optA = findViewById(R.id.button4);
        optB = findViewById(R.id.button7);
        optC = findViewById(R.id.button5);
        optD = findViewById(R.id.button6);
        next = findViewById(R.id.button8);
        score = 0;
        answer = "";

        triviaQuizHelper = new TriviaQuizHelper(this);
        triviaQuizHelper.getWritableDatabase();

        if(triviaQuizHelper.getAllOfTheQuestions().size() == 0) {
            triviaQuizHelper.allQuestion();
        }

        list = triviaQuizHelper.getAllOfTheQuestions();
        currentQuestion = list.get(qid);
        updateQueAndOptions();

        optA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                optA.setBackgroundResource(R.drawable.select_button);
                optB.setBackgroundResource(R.drawable.option_deselect);
                optC.setBackgroundResource(R.drawable.option_deselect);
                optD.setBackgroundResource(R.drawable.option_deselect);
                answer = optA.getText().toString();
            }
        });

        optB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                optA.setBackgroundResource(R.drawable.option_deselect);
                optB.setBackgroundResource(R.drawable.select_button);
                optC.setBackgroundResource(R.drawable.option_deselect);
                optD.setBackgroundResource(R.drawable.option_deselect);
                answer = optB.getText().toString();
            }
        });

        optC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                optA.setBackgroundResource(R.drawable.option_deselect);
                optB.setBackgroundResource(R.drawable.option_deselect);
                optC.setBackgroundResource(R.drawable.select_button);
                optD.setBackgroundResource(R.drawable.option_deselect);
                answer = optC.getText().toString();
            }
        });

        optD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                optA.setBackgroundResource(R.drawable.option_deselect);
                optB.setBackgroundResource(R.drawable.option_deselect);
                optC.setBackgroundResource(R.drawable.option_deselect);
                optD.setBackgroundResource(R.drawable.select_button);
                answer = optD.getText().toString();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(answer.equals(currentQuestion.getAnswer())){
                    score++;
                }

                qid++;

                if(qid == list.size()){
                    Intent intent = new Intent(questionpage.this, Result.class);
                    startActivity(intent);
                } else {
                    currentQuestion = list.get(qid);
                    updateQueAndOptions();
                }
            }
        });
    }

    public void updateQueAndOptions() {
        question.setText(currentQuestion.getQuestion());

        optA.setText(currentQuestion.getOpta());
        optB.setText(currentQuestion.getOptb());
        optC.setText(currentQuestion.getOptc());
        optD.setText(currentQuestion.getOptd());

        optA.setBackgroundResource(R.drawable.option_deselect);
        optB.setBackgroundResource(R.drawable.option_deselect);
        optC.setBackgroundResource(R.drawable.option_deselect);
        optD.setBackgroundResource(R.drawable.option_deselect);
    }

}