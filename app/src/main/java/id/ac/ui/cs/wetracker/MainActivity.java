package id.ac.ui.cs.wetracker;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.Button;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private boolean isExit;
    Button addFriendButton, exitButton, stopwatchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.addButtonListener();
    }

    @Override
    public void onBackPressed(){
        backButtonHandler();
    }

    public void backButtonHandler(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                MainActivity.this);
        // Setting Dialog Title
        alertDialog.setTitle("Leave application?");
        // Setting Dialog Message
        alertDialog.setMessage("Please press the exit button to exit");
        // Setting Dismiss Dialog
        alertDialog.setNeutralButton("Okay",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        alertDialog.show();
    }

    public void addButtonListener(){
        exitButton = (Button)findViewById(R.id.exit_button);

        View.OnClickListener exitButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("exit button clicked");
                finish();
                return;
            }
        };

        exitButton.setOnClickListener(exitButtonListener);

        stopwatchButton = (Button) findViewById(R.id.stopwatch_button);

        View.OnClickListener stopwatchButtonListener = new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent stopwatchIntent = new Intent(MainActivity.this, StopwatchActivity.class);
                startActivity(stopwatchIntent);
            }
        };

        stopwatchButton.setOnClickListener(stopwatchButtonListener);
    }



}
