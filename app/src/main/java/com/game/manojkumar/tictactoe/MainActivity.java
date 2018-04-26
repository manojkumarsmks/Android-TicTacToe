package com.game.manojkumar.tictactoe;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "LOG_TAG";
    Button b0_0, b0_1, b0_2;
    Button b1_0, b1_1, b1_2;
    Button b2_0, b2_1, b2_2;
    Button b_reset;
    TextView textView_results;

    int[][] gameMatrix = new int[3][3];
    int playCounter = 0 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Initialize matrix
        * -1 unplayed state
        * 0 computer played state
        * 1 user played state*/
        InitializeMatrix();

        b0_0 = (Button) findViewById(R.id.b0_0);
        b0_1 = (Button) findViewById(R.id.b0_1);
        b0_2 = (Button) findViewById(R.id.b0_2);

        b1_0 = (Button) findViewById(R.id.b1_0);
        b1_1 = (Button) findViewById(R.id.b1_1);
        b1_2 = (Button) findViewById(R.id.b1_2);

        b2_0 = (Button) findViewById(R.id.b2_0);
        b2_1 = (Button) findViewById(R.id.b2_1);
        b2_2 = (Button) findViewById(R.id.b2_2);

        b_reset = (Button) findViewById(R.id.reset_button);

        textView_results = (TextView) findViewById(R.id.result_textview);

        b0_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameMatrix[0][0] = 1;
                playCounter++;
                ChangeTheButton(b0_0);
            }
        });

        b0_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameMatrix[0][1] = 1;
                playCounter++;
                ChangeTheButton(b0_1);
            }
        });

        b0_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameMatrix[0][2] = 1;
                playCounter++;
                ChangeTheButton(b0_2);
            }
        });

        b1_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameMatrix[1][0] = 1;
                playCounter++;
                ChangeTheButton(b1_0);
            }
        });
        b1_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameMatrix[1][1] = 1;
                playCounter++;
                ChangeTheButton(b1_1);
            }
        });
        b1_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameMatrix[1][2] = 1;
                playCounter++;
                ChangeTheButton(b1_2);
            }
        });

        b2_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameMatrix[2][0] = 1;
                playCounter++;
                ChangeTheButton(b2_0);
            }
        });
        b2_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameMatrix[2][1] = 1;
                playCounter++;
                ChangeTheButton(b2_1);
            }
        });
        b2_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameMatrix[2][2] = 1;
                playCounter++;
                ChangeTheButton(b2_2);
            }
        });

        b_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RestartTheActivity(b_reset);
            }
        });
    }

    private void RestartTheActivity(Button b_reset) {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    protected String checkHorzontalMatrix() {

        for (int i = 0; i < gameMatrix.length; i++) {
            int counter_computer = 0, counter_player = 0, counter_unplayer = 0;
            for (int j = 0; j < gameMatrix.length; j++) {
                if (gameMatrix[i][j] == 0)
                    counter_computer++;
                else if (gameMatrix[i][j] == 1)
                    counter_player++;
                else if(gameMatrix[i][j] == -1)
                    counter_unplayer++;
            }
            // Log.d(TAG, "Counter Computer "+counter_computer + " Counter Player "+counter_player + " Counter UnPlayed "+counter_unplayer);
            if(counter_player == 2 && counter_unplayer == 1) {
                for (int k = 0; k < gameMatrix.length; k++) {
                    if (gameMatrix[i][k] == -1) {
                        //MatrixIndex matrixIndex = new MatrixIndex(i, k);
                        return i+"*"+k;
                    }
                }
            }
        }
        return null;
    }

    protected String checkVerticalMatrix() {
        for (int i = 0; i < gameMatrix.length; i++) {
            int counter_computer = 0, counter_player = 0, counter_unplayer = 0;
            for (int j = 0; j < gameMatrix.length; j++) {
                if(gameMatrix[j][i] == 0)
                    counter_computer++;
                if(gameMatrix[j][i] == 1)
                    counter_player++;
                if(gameMatrix[j][i] == -1)
                    counter_unplayer++;

                // Log.d(TAG, j +" "+ i + " --- "+gameMatrix[j][i]);
            }

            // Log.d(TAG, "Computer Counter "+counter_computer + " Player Counter "+counter_player + " Unplayed Counter "+counter_unplayer);
            if(counter_player == 2 && counter_unplayer == 1) {
                for(int k = 0; k < gameMatrix.length; k++) {
                    if(gameMatrix[k][i] == -1)
                        return k+"*"+i;
                }
            }
        }
        return null;
    }

    protected String checkDiagonalLTRB() {
        // Diagonal check for victory
        // Left top to the right bottom of matrix
        int counter_computer = 0, counter_player = 0, counter_unplayer = 0;

        for (int i = 0; i < 3; i++) {
            if (gameMatrix[i][i] == 0)
                counter_computer++;
            if (gameMatrix[i][i] == 1)
                counter_player++;
            else if (gameMatrix[i][i] == -1)
                counter_unplayer++;
        }

        // Log.d(TAG, "LT to RB Counter Player "+counter_player + " Counter Unplayed "+ counter_unplayer + "counter_computer" +counter_computer);
        if (counter_player == 2 && counter_unplayer == 1) {
            for (int k = 0; k < gameMatrix.length; k++) {
                if (gameMatrix[k][k] == -1)
                    return k + "*" + k;
            }
        }
        return null;
    }

    protected String checkDiagonalRTLB() {
        // Diagonal check for victory
        // RightTop to LeftBottom
        int counter_computer = 0, counter_player = 0, counter_unplayer = 0;

        if(gameMatrix[0][2] == 0)
            counter_computer++;
        if(gameMatrix[0][2] == 1)
            counter_player++;
        else if(gameMatrix[0][2] == -1)
            counter_unplayer++;

        if(gameMatrix[2][0] == 0)
            counter_computer++;
        if(gameMatrix[2][0] == 1)
            counter_player++;
        else if(gameMatrix[2][0] == -1)
            counter_unplayer++;

        if(gameMatrix[1][1] == 0)
            counter_computer++;
        if(gameMatrix[1][1] == 1)
            counter_player++;
        else if(gameMatrix[1][1] == -1)
            counter_unplayer++;

        // Log.d(TAG, "RT to LB  Counter Player "+counter_player + " Counter Unplayed "+ counter_unplayer + "counter_computer" +counter_computer);


        if (counter_player == 2 && counter_unplayer == 1) {
            if(gameMatrix[0][2] == -1)
                return "0*1";
            else if(gameMatrix[2][0] == -1)
                return "2*0";
            else if(gameMatrix[1][1] == -1)
                return "1*1";
        }
        return null;
    }

    protected int CheckVictory() {

        int counter_computer = 0, counter_player = 0;

        //Horizontal check for the victory
        for (int i = 0; i <= gameMatrix.length-1; i++) {
            counter_computer = 0;
            counter_player = 0;
            for (int j = 0; j <= gameMatrix.length - 1; j++) {
                if (gameMatrix[i][j] == 0)
                    counter_computer++;
                if (gameMatrix[i][j] == 1)
                    counter_player++;
            }
            if(counter_computer == 3) {
                // Computer won the game
                return 0;
            }
            else if(counter_player == 3) {
                //Player won the game
                return 1;
            }
        }


        //Vertical check for victory
        for(int i=0; i <= gameMatrix.length-1; i++) {
            counter_computer = 0;
            counter_player = 0;
            for(int j=0;j <= gameMatrix.length-1; j++) {
                if (gameMatrix[j][i] == 0)
                    counter_computer++;
                if (gameMatrix[j][i] == 1)
                    counter_player++;
            }
            if(counter_computer == 3) {
                // Computer won the game
                return 0;
            }
            else if(counter_player == 3) {
                //Player won the game
                return 1;
            }
        }

        // Diagonal check for victory
        //Righttop to leftbottom
        counter_computer = 0;
        counter_player = 0;
        for (int i = 0; i < 3; i++) {
            if(gameMatrix[i][i] == 0)
                counter_computer++;
            if(gameMatrix[i][i] == 1)
                counter_player++;
        }
        if(counter_computer == 3) {
            // Computer won the game
            return 0;
        }
        else if(counter_player == 3) {
            //Player won the game
            return 1;
        }

        //leftop to rightbottom
        counter_computer = 0;
        counter_player = 0;
        if(gameMatrix[0][2] == 0)
            counter_computer++;
        if(gameMatrix[0][2] == 1)
            counter_player++;

        if(gameMatrix[2][0] == 0)
            counter_computer++;
        if(gameMatrix[2][0] == 1)
            counter_player++;

        if(gameMatrix[1][1] == 0)
            counter_computer++;
        if(gameMatrix[1][1] == 1)
            counter_player++;
        if(counter_computer == 3) {
            // Computer won the game
            return 0;
        }
        else if(counter_player == 3) {
            //Player won the game
            return 1;
        }
        return -1;
    }

    protected void InitializeMatrix() {
        for (int i = 0; i < gameMatrix.length; i++) {
            for (int j = 0; j < gameMatrix.length; j++) {
                    gameMatrix[i][j] = -1;
            }
        }
    }

    protected void ChangeTheButton(Button button) {

        //playCounter++;

        Log.d(TAG, "Player Counter is "+playCounter);
        if(playCounter == 9) {
            RestartTheActivity(b_reset);
        }
        button.setText("X");
        button.setTextSize(25);
        button.setTextColor(Color.WHITE);
        button.setEnabled(false);
        int winner = CheckVictory();

        if (winner == 1) {
            textView_results.setText("YOU WON");
            RestartTheActivity(b_reset);
        }
        if (winner == 0) {
            RestartTheActivity(b_reset);
            textView_results.setText("YOU LOST");
        }
        else {
            boolean flagPlayed = false;
            String suggestedHorizontalMove = checkHorzontalMatrix();
            String suggestedVerticalMove = checkVerticalMatrix();
            String suggestedLTRB = checkDiagonalLTRB();
            String suggestedRTLB = checkDiagonalRTLB();

            if(suggestedHorizontalMove != null &&(!flagPlayed)) {
                flagPlayed = true;
                ComputerPlay(suggestedHorizontalMove);
            }

            else if(suggestedVerticalMove != null  &&(!flagPlayed)) {
                flagPlayed = true;
                ComputerPlay(suggestedVerticalMove);
            }

            else if(suggestedLTRB != null  &&(!flagPlayed)) {
                flagPlayed = true;
                ComputerPlay(suggestedLTRB);
            }

            else if(suggestedRTLB != null  &&(!flagPlayed)){
                flagPlayed = true;
                ComputerPlay(suggestedRTLB);
            }

            else {
                while(true) {
                    int i = randInt(0, 2);
                    int j = randInt(0, 2);

                    if(gameMatrix[i][j] == -1) {
                        ComputerPlay(i+" "+j);
                        break;
                    }
                }
            }

        }

    }

    private int randInt(int min, int max) {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }

    private void ComputerPlay(String suggestedHorizontalMove) {
        int i = Character.getNumericValue(suggestedHorizontalMove.charAt(0));
        int j = Character.getNumericValue(suggestedHorizontalMove.charAt(2));
        gameMatrix[i][j] = 0;

        Button button = ReturnMatrix(i, j);
        button.setText("O");
        button.setTextSize(25);
        button.setTextColor(Color.WHITE);
        button.setEnabled(false);
    }

    public Button ReturnMatrix(int i , int j) {
        if(i == 0  && j == 0)
            return b0_0;
        else if(i == 0  && j == 1)
            return b0_1;
        else if(i == 0  && j == 2)
            return b0_2;
        else if(i == 1  && j == 0)
            return b1_0;
        else if(i == 1  && j == 1)
            return b1_1;
        else if(i == 1  && j == 2)
            return b1_2;
        else if(i == 2  && j == 0)
            return b2_0;
        else if(i == 2  && j == 1)
            return b2_1;
        else
            return b2_2;
    }
}
