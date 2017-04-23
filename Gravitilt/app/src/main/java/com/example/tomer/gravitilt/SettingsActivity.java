package com.example.tomer.gravitilt;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class SettingsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Button btnSensitivity = (Button) findViewById(R.id.btnSensitivity);
        btnSensitivity.setText(getGameTiltSensitivity());
        registerForContextMenu(btnSensitivity);

        SeekBar skTime = (SeekBar) findViewById(R.id.skTime);
        final TextView tvTimeValue = (TextView) findViewById(R.id.tvTimeValue);
        int timeValue = (int)(Game.timeFactor * 100);
        skTime.setProgress(timeValue);
        String timeValueText = String.valueOf(timeValue) + "%";
        tvTimeValue.setText(timeValueText);

        skTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                String timeValueText = String.valueOf(seekBar.getProgress()) + "%";
                tvTimeValue.setText(timeValueText);
                Game.timeFactor = ((float)seekBar.getProgress()) / 100;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId() == R.id.btnSensitivity)
        {
            getMenuInflater().inflate(R.menu.tilt_sensitivity_menu,menu);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Button btnSensitivity = (Button) findViewById(R.id.btnSensitivity);
        btnSensitivity.setText(item.getTitle());

        switch (item.getItemId())
        {
            case R.id.miLow:
                Game.GeneralGravityFactor = Game.Amount.Low;
                break;
            case R.id.miMedium:
                Game.GeneralGravityFactor = Game.Amount.Medium;
                break;
            case R.id.miHigh:
                Game.GeneralGravityFactor = Game.Amount.High;
                break;
        }

        return super.onContextItemSelected(item);
    }

    private String getGameTiltSensitivity() {
        switch (Game.GeneralGravityFactor){
            case Low:
                return"Low";
            case Medium:
                return "Medium";
            case High:
                return "High";

        }
        return "UNDEFINED";
    }

    public void onClickBtnClear(View view) {
        new AlertDialog.Builder(this)
                .setTitle("Warning")
                .setMessage("Are you sure you want to permanently clear the leaderboards?")
                .setCancelable(false)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        LocalDataStorage lds = new LocalDataStorage(getApplicationContext());
                        lds.deleteAllScores();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public void onClickBtnSensitivity(View view) {
        openContextMenu(view);
    }




}
