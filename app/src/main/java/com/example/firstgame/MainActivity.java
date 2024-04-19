package com.example.firstgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView iv_numberOne,iv_numberTwo;
    ImageView iv_star1,iv_star2,iv_star3,iv_star4,iv_star5;
    ImageView iv_boardOne,iv_boardTwo;

//    ImageView iv_wrong,iv_right;

    ImageView iv_numberOne_answer,iv_numberTwo_answer;

    private int first_num,second_num,userAnswer;

    private int selected_option=1;

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
        iv_star1 = findViewById(R.id.iv_star1_body1);
        iv_star2 = findViewById(R.id.iv_star2_body2);
        iv_star3 = findViewById(R.id.iv_star3_body3);
        iv_star4 = findViewById(R.id.iv_star4_body4);
        iv_star5 = findViewById(R.id.iv_star5_body5);

        iv_numberOne_answer=findViewById(R.id.number_1_checkbox);
        iv_numberTwo_answer=findViewById(R.id.number_2_checkbox);


        do {
            first_num = assignNumbers();
            second_num = assignNumbers();
        }while (first_num == second_num);


        Log.d("Testing","X and y are"+first_num+"  "+second_num);

        setNumberImage(first_num,iv_numberOne);
        setNumberImage(second_num,iv_numberTwo);

        initializeStarsAnswers();

        iv_numberOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userAnswer = first_num;

            }
        });

        iv_numberTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userAnswer = second_num;
            }
        });

        result=checkUserWin(userAnswer);
        if(userAnswer == first_num)
            performImageOne();
        else
            performImageTwo();


    }

    public int assignNumbers(){
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

    public void performImageOne(){
        iv_numberOne_answer.setVisibility(View.VISIBLE);
        iv_numberTwo_answer.setVisibility(View.VISIBLE);
        if(result){
            iv_numberOne_answer.setImageResource(R.drawable.tick_mark);
            iv_numberTwo_answer.setImageResource(R.drawable.cross);

        }
        else
            iv_numberOne_answer.setImageResource(R.drawable.cross);
            iv_numberTwo_answer.setImageResource(R.drawable.tick_mark);

    }

    public void performImageTwo(){
        iv_numberTwo_answer.setVisibility(View.VISIBLE);
        iv_numberOne_answer.setVisibility(View.VISIBLE);
        if(result){
            iv_numberTwo_answer.setImageResource(R.drawable.tick_mark);
            iv_numberOne_answer.setImageResource(R.drawable.cross);
        }
        else
            iv_numberTwo_answer.setImageResource(R.drawable.cross);
            iv_numberOne_answer.setImageResource(R.drawable.tick_mark);

    }

}