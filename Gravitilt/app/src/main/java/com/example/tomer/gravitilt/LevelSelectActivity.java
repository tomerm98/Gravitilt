package com.example.tomer.gravitilt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Arrays;

public class LevelSelectActivity extends Activity {


    static Intent toGameplayIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_select);
        toGameplayIntent = new Intent(this,GameplayActivity.class);
        toGameplayIntent.putExtra(Game.GAME_MODE_EXTRA_NAME,Game.GAME_MODE_LEVEL_PRACTICE);
        final Button[] buttons = new Button[7];
        for (int i =0; i <buttons.length; i++)
        {
            int id = getResources().getIdentifier("btn"+i, "id", getPackageName());
            buttons[i] = (Button) findViewById(id);
            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int id = Arrays.asList(buttons).indexOf(view);
                    toGameplayIntent.putExtra(Game.LEVEL_ID_EXTRA_NAME,id);
                    startActivity(toGameplayIntent);
                }
            });
        }
    }
}

