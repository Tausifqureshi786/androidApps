package com.tausif2606.tictactoe;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {
    int board[] ={2,2,2,2,2,2,2,2,2,2};
    int Player=0;
    boolean Active = true;
    String Winner = "";
    int[] [] Positions = {
            {0,1,2}, {3,4,5}, {6,7,8},
            {0,3,6}, {1,4,7}, {2,5,8},
            {0,4,8}, {2,4,6}
    };
    int count=0;




    private void checkWinner(){


        // tracking the outer array of position
        for(int i=0; i<Positions.length; i++) {

            if (board[Positions[i][0]] == 0 && board[Positions[i][1]] == 0 && board[Positions[i][2]] == 0) {
                Winner = "zero";
                Active=false;
                break;
            } else if (board[Positions[i][0]] == 1 && board[Positions[i][1]] == 1 && board[Positions[i][2]] == 1) {

                Winner = "one";
                Active=false;
                break;
            }else if(count==8 ){
                Winner="draw";
                Active=false;

            }
        }
    }

    public void restartGame(View nView)
    {
        // set active to true
        Active=true;


        ImageView imgView1= (ImageView) findViewById(R.id.imageView1);
        ImageView imgView2= (ImageView) findViewById(R.id.imageView2);
        ImageView imgView3= (ImageView) findViewById(R.id.imageView3);
        ImageView imgView4= (ImageView) findViewById(R.id.imageView4);
        ImageView imgView5= (ImageView) findViewById(R.id.imageView5);
        ImageView imgView6= (ImageView) findViewById(R.id.imageView6);
        ImageView imgView7= (ImageView) findViewById(R.id.imageView7);
        ImageView imgView8= (ImageView) findViewById(R.id.imageView8);
        ImageView imgView9= (ImageView) findViewById(R.id.imageView9);

        // make all the images alpha to 0
        imgView1.setAlpha(0f);
        imgView2.setAlpha(0f);
        imgView3.setAlpha(0f);
        imgView4.setAlpha(0f);
        imgView5.setAlpha(0f);
        imgView6.setAlpha(0f);
        imgView7.setAlpha(0f);
        imgView8.setAlpha(0f);
        imgView9.setAlpha(0f);


        // reset the array boards value to 22222
        for(int i=0; i<board.length; i++)
        {
            board[i]=2;
        }
        // set the string winner to "";
        Winner="";
        // set the alpha value of linear layout to 0
        View linearLayout = findViewById(R.id.linearLayout);
        linearLayout.setAlpha(0f);

        Player=0;

        count=0;
    }

    public void placeMarker(View view)
    {
        ImageView imgView = (ImageView) view;

        String winner="";
        int tappedImgView = Integer.parseInt(imgView.getTag().toString());

        if(board[tappedImgView]==2 && Active)
        {
            board[tappedImgView]=Player;
            if(Player==0)
            {
                imgView.setImageResource(R.drawable.cross);
                imgView.setAlpha(1.0f);
                Player = 1;
            }
            else {
                imgView.setImageResource(R.drawable.o);
                imgView.setAlpha(1.0f);
                Player = 0;
            }
        }

       checkWinner();

        if(Active==false) {
            View linearLayout = findViewById(R.id.linearLayout);
            linearLayout.setAlpha(1.0f);

            if(Winner=="zero" )
            {
                    // player 0 wins;
                    TextView textView = findViewById(R.id.textView2);
                    textView.setText("The winner is Player 1");

            }
            else if(Winner=="one") {
                //player one wins;
                TextView textView = findViewById(R.id.textView2);
                textView.setText("The winner is Player 2");
            }
            else if( Winner=="draw"){
                TextView textView = findViewById(R.id.textView2);
                textView.setText("The game was a draw..");
            }
        }
        count++;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}