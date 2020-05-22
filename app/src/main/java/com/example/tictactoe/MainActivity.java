package com.example.tictactoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity{

    boolean xTurn;

    Button btnNew;

    Button btnUpLeft;
    Button btnUpMid;
    Button btnUpRight;
    Button btnCenLeft;
    Button btnCenMid;
    Button btnCenRight;
    Button btnDownLeft;
    Button btnDownMid;
    Button btnDownRight;

    Button[][] gridButtons;

    TextView display;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        xTurn = true;

        btnUpLeft = (Button) findViewById(R.id.button21);
        btnUpMid = (Button) findViewById(R.id.button11);
        btnUpRight = (Button) findViewById(R.id.button19);
        btnCenLeft = (Button) findViewById(R.id.button17);
        btnCenMid = (Button) findViewById(R.id.button12);
        btnCenRight = (Button) findViewById(R.id.button20);
        btnDownLeft = (Button) findViewById(R.id.button18);
        btnDownMid = (Button) findViewById(R.id.button13);
        btnDownRight = (Button) findViewById(R.id.button16);

        btnNew = (Button) findViewById(R.id.button10);
        display = (TextView) findViewById(R.id.textView);

        gridButtons = new Button[][] {{btnUpLeft, btnUpMid, btnUpRight}, {btnCenLeft, btnCenMid, btnCenRight}, {btnDownLeft, btnDownMid, btnDownRight}};
        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                freshGame();
            }
        });

        for(int i = 0; i < gridButtons.length; i++){
            for (int j = 0; j < gridButtons[i].length; j++)
            gridButtons[i][j].setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v){

                    if(xTurn){
                        ((Button) v).setText("X");
                        display.setText("Player O's Turn");
                    }
                    else{
                        ((Button) v).setText("O");
                        display.setText("Player X's Turn");
                    }

                    v.setEnabled(false);

                    if(winCheck()) {
                        if (xTurn) {
                            display.setText("X has won!");
                        }
                        else{
                            display.setText("O has won!");
                        }

                        gameOff();
                    }
                    else{
                        tieCheck();
                    }

                    xTurn = !xTurn;
                }
            });
        }
    }

    public boolean winCheck(){
        for (int i = 0; i < gridButtons.length; i++) {
            if (!gridButtons[i][0].isEnabled() &&
                    gridButtons[i][0].getText() == gridButtons[i][1].getText() &&
                    gridButtons[i][1].getText() == gridButtons[i][2].getText()) {
                return true;
            }
        }

        for (int j = 0; j < gridButtons[0].length; j++) {
            if (!gridButtons[0][j].isEnabled() &&
                    gridButtons[0][j].getText() == gridButtons[1][j].getText() &&
                    gridButtons[1][j].getText() == gridButtons[2][j].getText()) {
                return true;
            }
        }

        if(!gridButtons[0][0].isEnabled() &&
                gridButtons[0][0].getText() == gridButtons[1][1].getText() &&
                gridButtons[1][1].getText() == gridButtons[2][2].getText()){
            return true;
        }

        if(!gridButtons[0][2].isEnabled() &&
                gridButtons[0][2].getText() == gridButtons[1][1].getText() &&
                gridButtons[1][1].getText() == gridButtons[2][0].getText()){
            return true;
        }

        return false;


    }

    public void tieCheck(){

        for (Button[] row: gridButtons ) {
            for (Button b: row) {
                if (b.isEnabled()) {
                    return;
                }
            }
        }

        display.setText("Game has tied!");
    }

    public void gameOff(){

        for (Button[] row: gridButtons ) {
            for (Button b: row) {
                if (b.isEnabled()) {
                    b.setEnabled(false);
                }
            }
        }
    }

    public void freshGame(){

        for (Button[] row: gridButtons ) {
            for (Button b: row) {
                b.setText("");
                b.setEnabled(true);
            }
        }

        xTurn = true;
        display.setText("Player X's Turn");

    }

    public void onClick(View view){

    }
}
