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

    private int first_num,second_num;
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



        do {
            first_num = assignNumbers();
            second_num = assignNumbers();
        }while (first_num == second_num);


        Log.d("Testing","X and y are"+first_num+"  "+second_num);

        setNumberImage(first_num,iv_numberOne);
        setNumberImage(second_num,iv_numberTwo);


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
    public void initializeStars(){
        iv_star1.setVisibility(View.INVISIBLE);
        iv_star2.setVisibility(View.INVISIBLE);
        iv_star3.setVisibility(View.INVISIBLE);
        iv_star4.setVisibility(View.INVISIBLE);
        iv_star5.setVisibility(View.INVISIBLE);
    }

}