package com.saturnine.tictactoe;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    int active_player = 0;
    boolean gameIsActive = true;
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winningPositions = {{0,1,2},{3, 4, 5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public void drop(View view) {
        ImageView counter = (ImageView) view;
        int tapped_state = Integer.parseInt(counter.getTag().toString());
        if (gameState[tapped_state] == 2 && gameIsActive) {
            gameState[tapped_state] = active_player;
            counter.setTranslationY(-1000f);
            if (active_player == 0) {
                counter.setImageResource(R.drawable.yellow);
                active_player = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                active_player = 0;
            }
            counter.animate().translationYBy(1000f).rotation(360).setDuration(300);
            for (int[] winningPosition : winningPositions) {
                if (((gameState[winningPosition[0]] == gameState[winningPosition[1]]) &&
                        (gameState[winningPosition[1]] == gameState[winningPosition[2]])) &&
                        (gameState[winningPosition[0]] != 2)) {
                    gameIsActive = false;
                    String winner = "Red has won";
                    if (gameState[winningPosition[0]] == 0) {
                        winner = "Yellow has won";
                    }
                    TextView winnerMessage = findViewById(R.id.winnerMessage);
                    winnerMessage.setText(winner);
                    LinearLayout layout = findViewById(R.id.playLayout);
                    layout.setVisibility(View.VISIBLE);
                    break;


                } else {
                    boolean isGameOver = true;
                    for (int status : gameState) {
                        System.out.println(status);
                        if (status == 2) {
                            isGameOver = false;
                        }
                    }
                    if (isGameOver) {
                        TextView winnerMessage = findViewById(R.id.winnerMessage);
                        String result = "It is a draw";
                        winnerMessage.setText(result);
                        LinearLayout layout = findViewById(R.id.playLayout);
                        layout.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
    }

    public void playAgain(View view){
        gameIsActive = true;
        LinearLayout layout = findViewById(R.id.playLayout);
        layout.setVisibility(View.INVISIBLE);
        active_player = 0;
        Arrays.fill(gameState, 2);
        androidx.gridlayout.widget.GridLayout mygridLayout = findViewById(R.id.play_layout);
        for(int i = 0; i < mygridLayout.getChildCount();i++){
            ((ImageView) mygridLayout.getChildAt(i)).setImageResource(0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}