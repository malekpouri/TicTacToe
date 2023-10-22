package ir.utux.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    static final int CROSS = R.drawable.cross;
    static final int CIRCLE = R.drawable.circle;
    static int lastState = 0;
    int[][] board = new int[3][3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    public void click(View view) {
        ImageView imageView = (android.widget.ImageView) view;
        if (lastState == CROSS) {
            imageView.setImageResource(CIRCLE);
            lastState = CIRCLE;
        }
        else {
            imageView.setImageResource(CROSS);
            lastState = CROSS;
        }
        // set alpha to 1 with animation
        imageView.animate().alpha(1).setDuration(200);
        imageView.setEnabled(false);
        int x= Character.getNumericValue(getResources().getResourceEntryName(imageView.getId()).charAt(9));
        int y= Character.getNumericValue(getResources().getResourceEntryName(imageView.getId()).charAt(10));
        board[x-1][y-1] = lastState;

        check(view);
    }
    public void reset(View view) {
        ImageView imageView;
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3 ; j++) {
                imageView = (android.widget.ImageView)findViewById(getResources().getIdentifier("imageView" + i + j, "id", getPackageName()));
                imageView.setImageResource(0);
                imageView.setEnabled(true);
                imageView.animate().alpha(0).setDuration(0);
            }
        }
    }
    public void check(View view) {
        int winner = checkWinner();
        if (winner != 0) {
            Toast.makeText(this, "Player " + winner + " won!", Toast.LENGTH_SHORT).show();
        }
    }
    public int checkWinner() {
        int winner = 0;
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2])
                winner = board[i][0];
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i])
                winner = board[0][i];
        }
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2])
            winner = board[0][0];
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0])
            winner = board[0][2];
        return winner;
    }
}