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

    Button[] gridButtons;

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

        gridButtons = new Button[] {btnUpLeft, btnUpMid, btnUpRight, btnCenLeft, btnCenMid, btnCenRight, btnDownLeft, btnDownMid, btnDownRight};
        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                freshGame();
            }
        });

        for(int i = 0; i < gridButtons.length; i++){
            gridButtons[i].setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v){

                    if(xTurn){
                        ((Button) v).setText("X");
                    }
                    else{
                        ((Button) v).setText("O");
                    }

                    v.setEnabled(false);
                    xTurn = !xTurn;
                }
            });
        }
    }

    public void freshGame(){

        for(int i = 0; i < gridButtons.length; i++){
            xTurn = true;
            gridButtons[i].setText("");
            gridButtons[i].setEnabled(true);
            display.setText("It Worked!!!!");
        }
    }

    public void onClick(View view){

    }
}
