package com.surbhi100.ticktak;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //0= eater
    //1= nerd
    boolean gameactive = true;
    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};  //2 means gameState
    int[][] winningPositions = {{0,1, 2}, {3,4, 5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

    public void DropIn(View view) {
        ImageView counter = (ImageView) view;
        System.out.print(counter.getTag().toString());
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if (gameState[tappedCounter] == 2&& gameactive) {
            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-1000f);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.eater);
                activePlayer = 1;
            }
            else {
                counter.setImageResource(R.drawable.car);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1000f).rotation(360).setDuration(300);
            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {

                        String winner = "Nerd";

                        if (gameState[winningPosition[0]] == 0) {
                            winner = "Eater";
                        }

                        //someone has won
                        gameactive = false;
                        TextView winnermessage = (TextView) findViewById(R.id.winnermessage);
                        winnermessage.setText("Yay! " + winner + " has won");
                        LinearLayout layout = (LinearLayout) findViewById(R.id.playagainlayout);
                        layout.setVisibility(view.VISIBLE);
                    }
                    else
                    {
                        boolean game = true;
                        for (int countstate : gameState) {
                            if (countstate == 2) {
                                game = false;
                            }
                        }

                        if (game) {
                            TextView winnermessage = (TextView) findViewById(R.id.winnermessage);
                            winnermessage.setText("0h no! It's a draw");
                            LinearLayout layout = (LinearLayout) findViewById(R.id.playagainlayout);
                            layout.setVisibility(view.VISIBLE);
                        }
                    }

                }
            }
        }

        public void PlayAgain (View view)
        {
            gameactive = true;

            LinearLayout layout = (LinearLayout) findViewById(R.id.playagainlayout);
            layout.setVisibility(view.INVISIBLE);
            activePlayer = 0;
            for (int i = 0; i < gameState.length; i++) {
                gameState[i] = 2;
            }

            GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout2);
            for (int i = 0; i < gridLayout.getChildCount(); i++) {
                ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
            }
        }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
