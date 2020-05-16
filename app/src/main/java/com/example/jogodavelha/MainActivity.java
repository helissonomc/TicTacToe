package com.example.jogodavelha;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int player = 0;

    boolean winnerExist = false;

    int[] gameState = {2,2,2,
                       2,2,2,
                       2,2,2};

    int[][] winningPositions = {{0,1,2},
                                {3,4,5},
                                {6,7,8},
                                {0,3,6},
                                {1,4,7},
                                {2,5,8},
                                {0,4,8},
                                {6,4,2}};
    int contador = 0;
    public void dropIn(View view){
        ImageView counter = (ImageView) view;

        if(!winnerExist) {
            if (counter.getDrawable() == null) {

                String tag = (String) counter.getTag();
                gameState[Integer.parseInt(tag)] = player;

                if (player == 0) {
                    counter.setImageResource(R.drawable.vermelha);
                    counter.setTranslationY(-1500);
                    counter.animate().translationYBy(1500).setDuration(800);
                    player = 1;
                } else {
                    counter.setImageResource(R.drawable.amarela);
                    counter.setTranslationY(-1500);
                    counter.animate().translationYBy(1500).setDuration(800);
                    player = 0;

                }
                contador+=1;
            }

            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[0]] == gameState[winningPosition[2]] &&
                        gameState[winningPosition[0]] != 2) {


                    Button playAgain = (Button) findViewById(R.id.playAgainButton);
                    TextView winnerText = (TextView) findViewById(R.id.winnerTextView);
                    if (gameState[winningPosition[0]] == 0) {
                       winnerText.setText("Vermelho Venceu!");
                    } else {
                        winnerText.setText("Amarelo Venceu!");
                    }
                    winnerExist = true;
                    playAgain.setVisibility(View.VISIBLE);
                    winnerText.setVisibility(View.VISIBLE);
                }
            }

            if(contador == 9 && !winnerExist){
                Button playAgain = (Button) findViewById(R.id.playAgainButton);
                TextView winnerText = (TextView) findViewById(R.id.winnerTextView);
                winnerText.setText("Empate!");
                playAgain.setVisibility(View.VISIBLE);
                winnerText.setVisibility(View.VISIBLE);
            }

        }

    }

    public void playAgain(View view){

        Button playAgain = (Button) findViewById(R.id.playAgainButton);
        TextView winnerText = (TextView) findViewById(R.id.winnerTextView);
        playAgain.setVisibility(View.INVISIBLE);
        winnerText.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for (int i = 0; i<gridLayout.getChildCount();i++){
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
        player = 0;
        contador = 0;
        winnerExist = false;

        gameState = new int[]{2, 2, 2,
                              2, 2, 2,
                              2, 2, 2};

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
