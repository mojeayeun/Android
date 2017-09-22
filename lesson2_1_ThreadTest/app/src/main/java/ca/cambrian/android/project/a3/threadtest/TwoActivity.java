package ca.cambrian.android.project.a3.threadtest;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

import ca.cambrian.android.project.a3.threadtest.databinding.ActivityTwoBinding;

public class TwoActivity extends AppCompatActivity {
    public static final String TAG = "TwoActivity";

    private Timer mTimer;
    private TimerTask mTask;

    ActivityTwoBinding binding;

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_two);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_two);
        binding.setActivity(this);

        String data = getIntent().getStringExtra("data");
        binding.textView.setText(data);


    }

    //goto next
    public void onTimerStart(View view) {
        update();

    }

    //goto next
    public void onTimerStop(View view) {
       if (mTimer !=null) {
           mTimer.cancel();
       }

    }

    private void update() {
        mTimer = new Timer();
        mTask = new TimerTask() {
            @Override
            public void run() {
                Log.d(TAG,"This is time: " + String.valueOf(System.currentTimeMillis()));
                //binding.textView.setText("This is time: " + String.valueOf(System.currentTimeMillis()));
                binding.textView.post(new Runnable() {
                    @Override
                    public void run() {
                        binding.textView.setText("This is time: " + String.valueOf(System.currentTimeMillis()));
                    }
                });

            }
        };

        mTimer.schedule(mTask,1000,1000);
    }

    @Override
    protected void onDestroy() {
        if (mTimer !=null) {
            mTimer.cancel();
        }
        super.onDestroy();

    }
}
