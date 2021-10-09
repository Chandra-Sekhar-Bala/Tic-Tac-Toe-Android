package com.android.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        status = findViewById(R.id.status);
    }

    // 0-> x
    // x-> 0
    public int activePlayer = 0;
    int [] gameState = {2, 2, 2, 2, 2, 2, 2 ,2 ,2};

//    State
//    0- x
//    1-0
//    2 - BLANK

    int [][] winPos = {{0, 1, 2},
            {3, 4, 5},
            {6, 7, 8},
            {0, 3, 6},
            {1, 4, 7},
            {2, 5, 8},
            {0, 4, 8},
            {2, 4, 6}
    };
    private String winner;
    private TextView status;
    private boolean gameActive = true;

    public void play(View view) {
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());

        if(!gameActive){
            gameReset(view);
        }
        else if(gameState[tappedImage] == 2 && gameActive){

            gameState[tappedImage] = activePlayer;
            img.setTranslationY((-1000f));

            if(activePlayer == 0){
                img.setImageResource(R.drawable.x);
                activePlayer = 1;
                status.setText("O's turn - Tap bro");
            }
            else{
                img.setImageResource(R.drawable.o);
                activePlayer = 0;
                status.setText("X's turn - Tap bro");
            }

            img.animate().translationYBy(1000f).setDuration(300);
        }
//        IF any player won:
        for(int [] pos: winPos){
            if(gameState[pos[0]] == gameState[pos[1]] && gameState[pos[1]] == gameState[pos[2]] && gameState[pos[0]] != 2){
//                Someone won:
                if(gameState[pos[0]] == 0 ){
                    winner = "🎊🎊 'X' has WON 🎊🎊 ";
                }
                else {
                    winner = "🎊🎊  'O' has WON 🎊🎊 ";
                }
//                Winner announce;
                status.setText(winner);
                gameActive= false;
            }
        }
        boolean emptySquare = false;
        for (int squareState : gameState) {
            if (squareState == 2) {
                emptySquare = true;
                break;
            }
        }
        if (!emptySquare && gameActive) {
            // Game is a draw
            gameActive = false;
            String winnerStr;
            winnerStr = "No one won";
            TextView status = findViewById(R.id.status);
            status.setText(winnerStr);
        }
    }
    private void gameReset(View view){
        gameActive = true;
        activePlayer = 0;

        Arrays.fill(gameState, 2);
        status.setText("X's Turn - Tap bro");

        ((ImageView)findViewById(R.id.image_1)).setImageResource(0);
        ((ImageView)findViewById(R.id.image_2)).setImageResource(0);
        ((ImageView)findViewById(R.id.image_3)).setImageResource(0);
        ((ImageView)findViewById(R.id.image_4)).setImageResource(0);
        ((ImageView)findViewById(R.id.image_5)).setImageResource(0);
        ((ImageView)findViewById(R.id.image_6)).setImageResource(0);
        ((ImageView)findViewById(R.id.image_7)).setImageResource(0);
        ((ImageView)findViewById(R.id.image_8)).setImageResource(0);
        ((ImageView)findViewById(R.id.image_9)).setImageResource(0);

    }
}