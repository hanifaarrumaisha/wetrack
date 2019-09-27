package id.ac.ui.cs.wetracker;

import android.os.AsyncTask;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.time.format.DateTimeFormatter;


public class StopwatchActivity extends AppCompatActivity {
    private Integer seconds;
    TextView timer;
    Button startButton, stopButton, resumeButton;
    AsyncStopwatch asyncStopwatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        timer = (TextView) findViewById(R.id.timer);
        startButton = (Button) findViewById(R.id.startButton);
        stopButton = (Button) findViewById(R.id.stopButton);
        resumeButton = (Button) findViewById(R.id.resumeButton);
        startButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                asyncStopwatch = new AsyncStopwatch();
                seconds = 0;
                asyncStopwatch.execute(seconds);
                startButton.setEnabled(false);
                resumeButton.setEnabled(false);
                stopButton.setEnabled(true);
            }
        });
        stopButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                asyncStopwatch.cancel(true);
                startButton.setEnabled(true);
                resumeButton.setEnabled(true);
            }
        });
        resumeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                asyncStopwatch = new AsyncStopwatch();
                asyncStopwatch.execute(seconds);
                resumeButton.setEnabled(false);
                startButton.setEnabled(false);
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
    }


    private class AsyncStopwatch extends AsyncTask<Integer, String, Integer>{
        private boolean flag=true;
        @Override
        protected Integer doInBackground(Integer... integers) {
            System.out.println("Started");
            while (!isCancelled()){
                publishProgress(Integer.toString(seconds));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                seconds = seconds+1;
                System.out.println(seconds);
            }
            return seconds;
        }

        @Override
        protected void onProgressUpdate(String... progress) {
            timer.setText(progress[0]);
        }

        @Override
        protected void onCancelled(){
            flag = false;
        }


    }



}
