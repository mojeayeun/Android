package com.example.h02.lesson_20170711;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SeekBar mSeekBar;
    private TextView mTextView;
    private TextView mTextView2;
    private Button  mstartButton;
    private Button  mstopButton;

    private Handler mHandler;
    private int seekBarPosition = 0;

    private static final int SEEKBAR_MAXVALUE = 100;
    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSeekBar = (SeekBar) findViewById(R.id.seekBar);
        mTextView = (TextView) findViewById(R.id.textView);
        mstartButton = (Button) findViewById(R.id.startBtn);
        mstopButton = (Button) findViewById(R.id.stopBtn);
        mTextView2 = (TextView) findViewById(R.id.textView2);

        //mTextView2.setMovementMethod(new ScrollingMovementMethod());

        T t = new T();
        final Thread thread = new Thread(t);
        mHandler = new Handler();

        mSeekBar.setMax(SEEKBAR_MAXVALUE);
        mSeekBar.setProgress(seekBarPosition);

        mstopButton.setEnabled(false);

        mstartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mTextView.setText(String.format("%s",Integer.toString(seekBarPosition)));
                mSeekBar.setProgress(seekBarPosition);
                thread.start();

                mstartButton.setEnabled(false);
                mstopButton.setEnabled(true);
            }
        });


        mstopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mstartButton.setEnabled(true);
                mstopButton.setEnabled(false);

                if (thread.isAlive()){ thread.interrupt(); }
            }
        });



        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                seekBarPosition = i;
                mTextView.setText(String.format("%s",Integer.toString(seekBarPosition)));

                if (seekBarPosition == SEEKBAR_MAXVALUE){
                    mstartButton.setEnabled(true);
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, "onStartTrackingTouch", Toast.LENGTH_SHORT).show();
                mstartButton.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, "onStopTrackingTouch", Toast.LENGTH_SHORT).show();
                mstartButton.setVisibility(View.VISIBLE);
            }
        });

    }


    class T implements Runnable{

        @Override
        public void run() {

            while (seekBarPosition < SEEKBAR_MAXVALUE){
                seekBarPosition++;
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mSeekBar.setProgress(seekBarPosition);
                        mTextView.setText(String.format("%s",Integer.toString(seekBarPosition)));
                    }
                });


                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }

            }



        }
    }


//    private void moveSeekBar() {
//
//
//
//
//        handler.post(new Runnable() {
//            @Override
//            public void run() {
//                int i = 0;
//
//                while (i < 5){
//
//                    try {
//                        mSeekBar.setProgress(i);
//                        mTextView.setText(Integer.toString(i));
//                        Thread.sleep(1000);
//                        i++;
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//
//
//
//                }
//            }
//        });


//        int i = 0;
//
//        while (i < 5){
//
//            try {
//                mSeekBar.setProgress(i);
//                mTextView.setText(Integer.toString(i));
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            i++;
//
//        }

//
//    }
}
