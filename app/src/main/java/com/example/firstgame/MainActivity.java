package com.example.firstgame;

import static com.example.firstgame.R.id.iv_star1_body1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView iv_numberOne,iv_numberTwo;
    ImageView iv_star1,iv_star2,iv_star3,iv_star4,iv_star5;
    ImageView iv_boardOne,iv_boardTwo;

//    ImageView iv_wrong,iv_right;

    ImageView iv_numberOne_answer,iv_numberTwo_answer;

    ImageButton replay_btn;
    ImageView gray_1,gray_2,gray_3,gray_4,gray_5;

    Dialog displayScore;
    private int first_num,second_num,userAnswer,correct_Answer;


    public int countGames = 1,countUserWin=0;

    boolean result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


            //Assigning the IDs
            iv_numberOne = findViewById(R.id.number_1);
            iv_numberTwo = findViewById(R.id.number_2);

            iv_boardOne = findViewById(R.id.board1);
            iv_boardTwo = findViewById(R.id.board2);
            iv_star1 = findViewById(iv_star1_body1);
            iv_star2 = findViewById(R.id.iv_star2_body2);
            iv_star3 = findViewById(R.id.iv_star3_body3);
            iv_star4 = findViewById(R.id.iv_star4_body4);
            iv_star5 = findViewById(R.id.iv_star5_body5);

            iv_numberOne_answer = findViewById(R.id.number_1_checkbox);
            iv_numberTwo_answer = findViewById(R.id.number_2_checkbox);


            displayScore = new Dialog(this);

//        Log.d("Testing", "X and y are" + first_num + "  " + second_num);

        //return the game to initial stage
        initializeStarsAnswers();

        //FIRST GAME
        assignNumbers();
        assignNumbersToImages();
        OnImageClick();


    }// onCreate ENDS here

    public int chooseRandomNumber(){
        int x;
        Random random = new Random();
        x = random.nextInt(10)+1;
        return x;
    }

    public void setNumberImage(int number,ImageView imageView){
        switch (number){

            case 1: imageView.setImageResource(R.drawable.num_one);break;
            case 2: imageView.setImageResource(R.drawable.num_two);break;
            case 3: imageView.setImageResource(R.drawable.num_three);break;
            case 4: imageView.setImageResource(R.drawable.num_four);break;
            case 5: imageView.setImageResource(R.drawable.num_five);break;
            case 6: imageView.setImageResource(R.drawable.num_six);break;
            case 7: imageView.setImageResource(R.drawable.num_seven);break;
            case 8: imageView.setImageResource(R.drawable.num_eight);break;
            case 9: imageView.setImageResource(R.drawable.num_nine);break;
            case 10:imageView.setImageResource(R.drawable.num_ten);break;
        }

    }
    public void initializeStarsAnswers(){
        iv_star1.setVisibility(View.INVISIBLE);
        iv_star2.setVisibility(View.INVISIBLE);
        iv_star3.setVisibility(View.INVISIBLE);
        iv_star4.setVisibility(View.INVISIBLE);
        iv_star5.setVisibility(View.INVISIBLE);

        iv_numberOne_answer.setVisibility(View.INVISIBLE);
        iv_numberTwo_answer.setVisibility(View.INVISIBLE);

    }

    public boolean checkUserWin(int userAnswer){
        int otherOption;
        boolean result=false;
        if(userAnswer == first_num)
            otherOption = second_num;
        else
            otherOption = first_num;
        if(userAnswer > otherOption)
            result = true;

        return result;
    }

    public int theBiggestOfTwo(){
        int biggest=second_num;
        if(first_num > second_num){
            biggest = first_num;
            iv_numberOne_answer.setImageResource(R.drawable.tick_mark);
            iv_numberTwo_answer.setImageResource(R.drawable.cross);
        }
        else {
            iv_numberTwo_answer.setImageResource(R.drawable.tick_mark);
            iv_numberOne_answer.setImageResource(R.drawable.cross);
        }
        return biggest;
    }

    public void setTheStar(int userWins){
            switch (userWins){
                case 1:iv_star1.setVisibility(View.VISIBLE)

                ;break;
                case 2:iv_star2.setVisibility(View.VISIBLE);break;
                case 3:iv_star3.setVisibility(View.VISIBLE);break;
                case 4:iv_star4.setVisibility(View.VISIBLE);break;
                case 5:iv_star5.setVisibility(View.VISIBLE);break;
            }
    }

    public void assignNumbers(){
        countGames += 1;
        do {
            first_num = chooseRandomNumber();
            second_num = chooseRandomNumber();
        } while (first_num == second_num);

    }
    public void assignNumbersToImages(){
        //set the images

        setNumberImage(first_num, iv_numberOne);
        setNumberImage(second_num, iv_numberTwo);

    }

    public void OnImageClick(){
        iv_numberOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userAnswer = first_num;
                correct_Answer = theBiggestOfTwo();

                //show correct answer
                iv_numberOne_answer.setVisibility(View.VISIBLE);
                iv_numberTwo_answer.setVisibility(View.VISIBLE);

                //check if user win
                if (userAnswer == correct_Answer) {
                    //counting user wins
                    countUserWin += 1;
                    setTheStar(countUserWin); //assigning stars per User Win
                }

                if( countGames <= 5) {
                    Handler myHandler = new Handler();
                    myHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            iv_numberOne_answer.setVisibility(View.INVISIBLE);
                            iv_numberTwo_answer.setVisibility(View.INVISIBLE);

                            assignNumbers();
                            assignNumbersToImages();
                            OnImageClick();
                        }
                    }, 900);

                }// if count of games is within 5

                if(countGames > 5) {
                    Log.d("Tag20202","<<--Here1-->>") ;
                    DisplayScore() ;
//                    showCustomDialog();

                }
            }
        });

        iv_numberTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userAnswer = second_num;
                correct_Answer = theBiggestOfTwo();

                iv_numberOne_answer.setVisibility(View.VISIBLE);
                iv_numberTwo_answer.setVisibility(View.VISIBLE);

                if (userAnswer == correct_Answer) {
                    //counting user wins
                    countUserWin += 1;
                    setTheStar(countUserWin); //assigning stars per User Win
                }

                if( countGames <= 5) {
                    Handler myHandler = new Handler();
                    myHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            iv_numberOne_answer.setVisibility(View.INVISIBLE);
                            iv_numberTwo_answer.setVisibility(View.INVISIBLE);

                            assignNumbers();
                            assignNumbersToImages();
                            OnImageClick();
                        }
                    }, 900);

                }// if count of games is within 5

                if(countGames > 5) {
                    Log.d("Tag20202","<<--Here2-->>") ;
                    DisplayScore();
                }
            }
        });


    }// onImageClick ENDS HERE

    public void DisplayScore() {
//        Log.d("Tag20202","<<--Here3-->>") ;
        int i = 1;
        displayScore = new Dialog(this);
        displayScore.setContentView(R.layout.activity_display_score);
        displayScore.getWindow();

        gray_1 = displayScore.findViewById(R.id.star_1);
        gray_2 = displayScore.findViewById(R.id.star_2);
        gray_3 = displayScore.findViewById(R.id.star_3);
        gray_4 = displayScore.findViewById(R.id.star_4);
        gray_5 = displayScore.findViewById(R.id.star_5);

        replay_btn = displayScore.findViewById(R.id.restart_btn);

        while (i<= countUserWin){
            switch (i) {
                case 1:
                    gray_1.setImageResource(R.drawable.star);
                    break;
                case 2:
                    gray_2.setImageResource(R.drawable.star);
                    break;
                case 3:
                    gray_3.setImageResource(R.drawable.star);
                    break;
                case 4:
                    gray_4.setImageResource(R.drawable.star);
                    break;
                case 5:
                    gray_5.setImageResource(R.drawable.star);
                    break;
            }
            i++;
        }

        replay_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restart();
            }
        });

        displayScore.show();
    }



    public void restart(){
        initializeStarsAnswers();
        assignNumbers();
        assignNumbersToImages();
        OnImageClick();
    }



    // Inside your activity
/*    public void showCustomDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.activity_display_score, null);
        builder.setView(dialogView);

        *//*TextView dialogTitle = dialogView.findViewById(R.id.dialog_title);
        EditText editText = dialogView.findViewById(R.id.edit_text);
        Button cancelButton = dialogView.findViewById(R.id.dialog_button_cancel);
        Button okButton = dialogView.findViewById(R.id.dialog_button_ok);
*//*
//        dialogTitle.setText("Custom Dialog Title");

        AlertDialog dialog = builder.create();

        *//*cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userInput = editText.getText().toString();
                // Do something with the user input
                dialog.dismiss();
            }
        });
*//*
        dialog.show();
    }*/


}