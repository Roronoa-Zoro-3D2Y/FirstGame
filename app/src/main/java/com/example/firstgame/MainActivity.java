package com.example.firstgame;

import static android.media.MediaPlayer.create;
import static com.example.firstgame.R.id.iv_cat_head;
import static com.example.firstgame.R.id.iv_star1_body1;
import static com.example.firstgame.R.id.owl_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.Dialog;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView iv_numberOne,iv_numberTwo;
    ImageView iv_star1,iv_star2,iv_star3,iv_star4,iv_star5;
    ImageView iv_boardOne,iv_boardTwo;

//    ImageView iv_wrong,iv_right;

    ImageView iv_numberOne_answer,iv_numberTwo_answer;

    ArrayList<Integer> MySounds = new ArrayList<>();

    ImageView iv_owl,iv_owl_fly,iv_owl_no,iv_caterpillar;

    AnimationDrawable owl_tapping,owl_flying,owl_shaking,caterpillar_happy;

    AppCompatButton replay_btn;
    ImageView gray_1,gray_2,gray_3,gray_4,gray_5;

    Dialog displayScore;

    MediaPlayer myMediaPlayer;


    private int first_num,second_num,userAnswer,correct_Answer,theActualQuestion;

    public int countGames = 1,countUserWin=0;

    boolean result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);


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
        theActualQuestion =(int) set_question();
        OnImageClick();


    }// onCreate ENDS here

  /*  @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        owl_flying.start();
    }*/

    public int chooseRandomNumber(){
        int x;
        Random random = new Random();
        x = random.nextInt(10)+1;
        return x;
    }

    int[] mapNumToImageNum = new int[]{R.drawable.num_one,
            R.drawable.num_two,
            R.drawable.num_three,
            R.drawable.num_four,
            R.drawable.num_five,
            R.drawable.num_six,
            R.drawable.num_seven,
            R.drawable.num_eight,
            R.drawable.num_nine,
            R.drawable.num_ten
    };

    public void setNumberImage(int number, ImageView imageView){
        switch (number){

            case 1: imageView.setImageResource(R.drawable.num_one   );break;
            case 2: imageView.setImageResource(R.drawable.num_two   );break;
            case 3: imageView.setImageResource(R.drawable.num_three );break;
            case 4: imageView.setImageResource(R.drawable.num_four  );break;
            case 5: imageView.setImageResource(R.drawable.num_five  );break;
            case 6: imageView.setImageResource(R.drawable.num_six    ) ;break;
            case 7: imageView.setImageResource(R.drawable.num_seven  ) ;break;
            case 8: imageView.setImageResource(R.drawable.num_eight  );break;
            case 9: imageView.setImageResource(R.drawable.num_nine  );break;
            case 10:imageView.setImageResource(R.drawable.num_ten  );break;
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

    public void isUserCorrect(){

        if(userAnswer != correct_Answer) {
            setOwl_no();
            OnImageClick();
            myMediaPlayer = MediaPlayer.create(this,R.raw.not_this_one);
            myMediaPlayer.start();

        }
        else {
            iv_numberOne_answer.setVisibility(View.VISIBLE);
            iv_numberTwo_answer.setVisibility(View.VISIBLE);

//            correctAnswerSound = MediaPlayer.create(this,R.raw.huhuump3);
//            correctAnswerSound.start();

            //owl happy
            /*myMediaPlayer = MediaPlayer.create(this,R.raw.huhuump3);
            myMediaPlayer.start();*/
            if(myMediaPlayer!=null) {
                myMediaPlayer.stop();
                myMediaPlayer.release();
            }
            setOwl_flying();

            //set Star
            countUserWin += 1;
            setTheStar(countUserWin);

            int red_col = R.color.red;

            if( countUserWin < 5) {
                Handler myHandler = new Handler();
                myHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        iv_numberOne_answer.setVisibility(View.INVISIBLE);
                        iv_numberTwo_answer.setVisibility(View.INVISIBLE);
                        caterpillar_happy.setOneShot(false);
                        assignNumbers();
                        assignNumbersToImages();
                        theActualQuestion = (int)set_question();
                        OnImageClick();

                    }
                }, 1000);

            }// if count of games is within 5

            if(countUserWin >= 5) {
                Log.d("Tag20202","<<--Here2-->>") ;
                DisplayScore();
             }
        }//else ends
    }//is User Correct ends


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
    public int theSmallestOfTwo(){

        int smallest  = second_num;
        if(first_num < second_num){
            smallest = first_num;
            iv_numberOne_answer.setImageResource(R.drawable.tick_mark);
            iv_numberTwo_answer.setImageResource(R.drawable.cross);
        }
        else {
            iv_numberTwo_answer.setImageResource(R.drawable.tick_mark);
            iv_numberOne_answer.setImageResource(R.drawable.cross);
        }
        return smallest;

    }

    public void setTheStar(int userWins){
        setCaterpillar_happy();

            switch (userWins){
                case 1:iv_star1.setVisibility(View.VISIBLE);break;
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
        setOwl_tapping();
        setNumberImage(first_num, iv_numberOne);
        setNumberImage(second_num, iv_numberTwo);

        myMediaPlayer = MediaPlayer.create(this,R.raw.wordpop);
        myMediaPlayer.start();

        iv_numberOne.setClickable(false);
        iv_numberTwo.setClickable(false);

    }
    public void getNumAndAssign(){
        do {
            first_num = chooseRandomNumber();

            second_num = chooseRandomNumber();
        } while (first_num == second_num);

        setOwl_tapping();
        iv_numberOne.setImageResource(mapNumToImageNum[first_num]);
        iv_numberTwo.setImageResource(mapNumToImageNum[second_num]);;
    }

    public void OnImageClick(){


        iv_numberOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userAnswer = first_num;

                if(0 == theActualQuestion){

                    correct_Answer = theBiggestOfTwo();
                }

                else
                    correct_Answer = theSmallestOfTwo();

                //show the option user has clicked only
                iv_numberOne_answer.setVisibility(View.VISIBLE);

                isUserCorrect();

            } // onclick
        });// set onclick listener of Image one

        iv_numberTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userAnswer = second_num;
                if(0 == theActualQuestion)
                    correct_Answer = theBiggestOfTwo();
                else
                    correct_Answer = theSmallestOfTwo();
                iv_numberTwo_answer.setVisibility(View.VISIBLE);
                isUserCorrect();


        /*//show correct answer
                iv_numberOne_answer.setVisibility(View.VISIBLE);
                iv_numberTwo_answer.setVisibility(View.VISIBLE);*/

                //show only the image user has clicked on

            } // onclick
        });// set onclick listener of Image two


    }// onImageClick ENDS HERE

    public void DisplayScore() {
//        Log.d("Tag20202","<<--Here3-->>") ;
        int i = 1;

        if(displayScore!=null){
            displayScore.dismiss();
            displayScore = null;
        }

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
                displayScore.dismiss();
                restart();
            }
        });

        if(!displayScore.isShowing())
            displayScore.show();
    }



    public void restart(){
        countUserWin = 0;

        initializeStarsAnswers();
        assignNumbers();
        assignNumbersToImages();
        theActualQuestion = (int) set_question();
        OnImageClick();
    }

    public void setOwl_tapping(){
            iv_owl = findViewById(R.id.owl_1);
            iv_owl.setImageResource(R.drawable.owl_tap_animation);
            owl_tapping = (AnimationDrawable) iv_owl.getDrawable();
            owl_tapping.setOneShot(true);
            owl_tapping.start();
    }

    public void setOwl_no(){
        iv_owl_no = findViewById(owl_1);
        iv_owl_no.setImageResource(R.drawable.owl_no_no);
        owl_shaking = (AnimationDrawable) iv_owl_no.getDrawable();
        owl_shaking.setOneShot(true);
        owl_shaking.start();

        myMediaPlayer = MediaPlayer.create(this,R.raw.not_this_one);
        myMediaPlayer.start();

    }

    public void setCaterpillar_happy(){
        iv_caterpillar = findViewById(iv_cat_head);
        iv_caterpillar.setImageResource(R.drawable.caterpillar_happy);
        caterpillar_happy = (AnimationDrawable) iv_caterpillar.getDrawable();
        caterpillar_happy.setOneShot(true);
        caterpillar_happy.start();
    }

    public void setOwl_flying(){
        int temp = 5;
            iv_owl_fly = findViewById(owl_1);
            iv_owl_fly.setImageResource(R.drawable.owl_flying_in);
            owl_flying = (AnimationDrawable) iv_owl_fly.getDrawable();
            owl_flying.setOneShot(true);
            owl_flying.start();

            myMediaPlayer = MediaPlayer.create(this,R.raw.clap);
            myMediaPlayer.start();


    }

    public long set_question(){
        Random random = new Random();
        long i;
        i = random.nextInt(2);



        if(i == 0){
            myMediaPlayer = MediaPlayer.create(this,R.raw.which_one_is_bigger);
            myMediaPlayer.start();
        }
        else {
            myMediaPlayer = MediaPlayer.create(this,R.raw.which_one_is_smaller);
            myMediaPlayer.start();
        }

        iv_numberOne.setClickable(true);
        iv_numberTwo.setClickable(true);

        return i;
    }


/*                if (userAnswer == correct_Answer) {
                    //if user wins then show the other answer too
                    iv_numberTwo_answer.setVisibility(View.VISIBLE);

                    //counting user wins
                    countUserWin += 1;
                    setTheStar(countUserWin); //assigning stars per User Win
                    setOwl_flying();

                }
                if(userAnswer != correct_Answer)
                {
                    setOwl_no();
                    OnImageClick();
                }*/
    //isUserCorrect();

}