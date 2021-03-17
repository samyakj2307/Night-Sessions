package com.samyak.rock_paper_scissors_game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int userscr = 0;
    int compscr = 0;
    ImageButton rock,paper,scissors;
    TextView userScore,compScore,resultText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rock = findViewById(R.id.rockbutton);
        paper = findViewById(R.id.paperbutton);
        scissors = findViewById(R.id.scissorsbutton);

        userScore = findViewById(R.id.userscoretext);
        compScore = findViewById(R.id.compscoretext);
        resultText = findViewById(R.id.result);

        rock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String choice = "r";
                game(choice);
            }
        });

        paper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String choice = "p";
                game(choice);

            }
        });

        scissors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String choice = "s";
                game(choice);

            }
        });
    }

    public  void game(String userChoice){
        String[] allchoices = {"r","p","s"};
        int x =(int) Math.floor(Math.random()*3);
        String compchoice = allchoices[x];

        String fullchoice = userChoice+compchoice;

        if(fullchoice.equals("rp")|fullchoice.equals("ps")|fullchoice.equals("sr")){
            lose(userChoice,compchoice);
        }
        else if( fullchoice.equals("rs") | fullchoice.equals("pr") | fullchoice.equals("sp")){
            win(userChoice,compchoice);
        }
        else{
            draw(userChoice,compchoice);
        }
    }

    public void win(String userChoice, String compChoice){
        userscr++;
        userScore.setText(Integer.toString(userscr));
        String resultstr = convertChoice(userChoice)+" wins to " + convertChoice(compChoice) + ".You Win!";
        resultText.setText(resultstr);
    }
    public void lose(String userChoice, String compChoice){
        compscr++;
        compScore.setText(Integer.toString(compscr));
        String resultstr = convertChoice(userChoice)+" loses to " + convertChoice(compChoice) + ".You Lost!";
        resultText.setText(resultstr);
    }

    public void draw(String userChoice, String compChoice){
        String resultstr = convertChoice(userChoice)+" equals to " + convertChoice(compChoice) + ".Its a Draw!";
        resultText.setText(resultstr);
    }


    public String convertChoice(String choice){
        if(choice.equals("r")){
            return "Rock";
        }
        else if(choice.equals("p")){
            return "Paper";
        }else {
            return  "Scissors";
        }
    }

}