package com.example.tictactoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity{

    boolean xTurn;
    boolean defaultTurn;

    Button btnSettings;

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
        defaultTurn = true;

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
        btnSettings = (Button) findViewById(R.id.btnSettings);

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

        btnSettings.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAbout();
            }
        } );
    }

    public void openAbout(){
        String stuff = "New Game: X";
        String things = "New Game: O";

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setPositiveButton(stuff, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                xTurn = true;
                defaultTurn = true;
            }
        })
        .setNegativeButton(things, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id){
                xTurn = false;
                defaultTurn = false;
            }
        });

        alertDialogBuilder.setMessage("This application was developed by Conor O'Haleck in 2020, for CPW 252 at Clover Park Technical College");

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
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

        xTurn = defaultTurn;
        if(defaultTurn) {
            display.setText("Player X's Turn");
        }
        else{
            display.setText("Player O's Turn");
        }

    }

    public void onClick(View view){

    }
}
