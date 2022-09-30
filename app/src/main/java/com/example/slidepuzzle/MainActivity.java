package com.example.slidepuzzle;


import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static android.app.PendingIntent.getActivity;

public class MainActivity extends Activity {

    Button[] buttons = new Button[9];
    TextView textView_message;
    int size = 9;
    Game game = new Game(buttons, textView_message);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //the button crew
        game.buttons[0] = (Button) findViewById(R.id.button5);
        game.buttons[1] = (Button) findViewById(R.id.button6);
        game.buttons[2] = (Button) findViewById(R.id.button7);
        game.buttons[3] = (Button) findViewById(R.id.button8);
        game.buttons[4] = (Button) findViewById(R.id.button9);
        game.buttons[5] = (Button) findViewById(R.id.button10);
        game.buttons[6] = (Button) findViewById(R.id.button11);
        game.buttons[8] = (Button) findViewById(R.id.button12);

    }


    public void clickSliderTile(View tile)
    {
        game.MoveTile(tile);
    }

    public class Game {
        public Button[] buttons = new Button[9];
        public TextView message;
        private int[] positions = new int[9];
        private int size = 9;
        private int buttonClickedIndex = 0;
        private int emptyLocationIndex = 0;

        Game(Button[] inButtons, TextView inTextView)
        {
            buttons = inButtons;
            message = inTextView;

            for (int i = 0; i < size; i++)
            {
                positions[i] = i + 1;
            }
        }

        public void MoveTile(View tile)
        {

            for (int i = 0; i < size; i++)
            {
                if (tile.getId() == buttons[i].getId())
                {
                    buttonClickedIndex = i;
                    break;
                }
            }
            checkIfTileMove();
            switchTwoTiles();
            if (emptyLocationIndex != -1)
            {
                 checkIfTileMove();
                switchTwoTiles();
            }
            checkIfTileMove();
        }

        private int checkIfTileMove()
        {
            int[] availibleMove = new int[2];
            availibleMove[0] = buttonClickedIndex - 1; // up
            availibleMove[1] = buttonClickedIndex + 1; // take it back
            availibleMove[2] = buttonClickedIndex - 1; // slide to the left
            availibleMove[3] = buttonClickedIndex + 1; // slide to the right


            if (buttonClickedIndex == emptyLocationIndex + 1 || buttonClickedIndex == emptyLocationIndex - 1) {
                availibleMove[3] = -1;
                Toast.makeText(getApplicationContext(),"No Move Here!", Toast.LENGTH_SHORT).show();
            }

            for (int i = 0; i < 4; i++)
            {
                if (availibleMove[i] >= 0 && availibleMove[i] < 9)
                {
                    if (buttons[availibleMove[i]].isShown() == false)
                    {
                        emptyLocationIndex = availibleMove[i];
                        return availibleMove[i];
                    }
                }
            }
            emptyLocationIndex = 1;
            return -1;
        }
        private void switchTwoTiles()
        {
            if(buttons[buttonClickedIndex].getText() == ""){
                buttons[buttonClickedIndex].setText(" ");
                buttons[emptyLocationIndex].setText(buttons[buttonClickedIndex].getText());
            }
//            //buttons[buttonClickedIndex].setVisibility(View.INVISIBLE);
//            buttons[emptyLocationIndex].setText(buttons[buttonClickedIndex].getText());
//            buttons[emptyLocationIndex].setVisibility(View.VISIBLE);
//            int temp = positions[emptyLocationIndex];
//            positions[emptyLocationIndex] = positions[buttonClickedIndex];
//            positions[buttonClickedIndex] = temp;
        }

    }

}
