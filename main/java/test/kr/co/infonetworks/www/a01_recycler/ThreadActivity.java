package test.kr.co.infonetworks.www.a01_recycler;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.Locale;

import test.kr.co.infonetworks.www.a01_recycler.databinding.ActivityThreadBinding;

public class ThreadActivity extends AppCompatActivity {
    private static final String TAG = "ThreadActivity";
    public static final int REQUEST_NUM = 0;

    private ActivityThreadBinding mBinding;

    private SeekBarHanlder mSeekBarHanlder;
    private SeekBarBackThread mSeekBarBackThread;


    //자신의 Intent를 자신이 만든다
    public static Intent getIntent(Context context){
        Intent intent = new Intent(context,ThreadActivity.class);
        return intent;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_thread);

        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_thread);


        final Intent intent = getIntent();
        String loginid = intent.getStringExtra("loginid");
        Log.d(TAG,"login id : " + loginid);

        mBinding.tvParamValue.setText(loginid);

        mBinding.btnSaveRef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveSharedPreference();
            }
        });

        //2.Run Thread
        // 화면에 값을 변경한다


        mBinding.btnRunThread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //performTheread1();
                //performTheread2();
                //performTheread3();
                performTheread4();

            }
        });



        //3. Start SeekBar
        mBinding.btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveSeekBar();
            }


        });

        //4.Stop SeekBar
        mBinding.btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopSeekBar();

            }
        });

        //5.Activity Result
        mBinding.btnActivityResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(ThreadActivity.this,ResultReturnActivity.class);
                startActivityForResult(intent1,REQUEST_NUM);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Toast.makeText(this, "requestCode: " + requestCode + " resultCode: " + resultCode,Toast.LENGTH_SHORT).show();

        if (requestCode == REQUEST_NUM){

            if (resultCode == RESULT_OK){
                String result = data.getStringExtra("RESULT");

                Toast.makeText(this, "return value: " + result, Toast.LENGTH_SHORT).show();
            }

        }

    }
    //SeekBar를 위한 Handler와 Thread Class
    private class SeekBarHanlder extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1){
                mBinding.seekBar.setProgress(msg.arg1);
            }
        }
    }

    private class SeekBarBackThread extends Thread {

        Handler handler;

        public SeekBarBackThread(Handler handler) {
            this.handler = handler;
        }

        @Override
        public void run() {
            super.run();
            int i = 0;

            try {
                Log.d(TAG, "Thread.currentThread().isInterrupted(): " + Thread.currentThread().isInterrupted());

                while (!Thread.currentThread().isInterrupted()) {

                    while (i < 100) {

                        Message message = new Message();
                        message.what = 1;
                        message.arg1 = i;
                        handler.sendMessage(message);
                        i++;

                        Thread.sleep(1000);

                    }

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                Log.d(TAG, "catch-Thread.currentThread().isInterrupted(): " + Thread.currentThread().isInterrupted());

            }
        }
    }

    private void moveSeekBar() {
        mSeekBarHanlder = new SeekBarHanlder();

        mSeekBarBackThread  = new SeekBarBackThread(mSeekBarHanlder);
        mSeekBarBackThread.setDaemon(true);
        mSeekBarBackThread.start();
    }


    private void stopSeekBar(){

        if (mSeekBarBackThread != null){
            mSeekBarBackThread.interrupt();  //thread를 stop한다

            Log.d(TAG,"call -- mSeekBarBackThread.interrupt()");

            mBinding.seekBar.setProgress(1);
        }
    }

    private void moveSeekBar_old() {

        //http://yoonhada.com/wp/?p=795&ckattempt=1
        //non-static inner class와 anonymous class는 outer class에 대한 implicit reference를 갖고,
        // static inner class의 경우에는 갖지 않는다.
        class MyHanlder extends Handler{
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 1){
                    mBinding.seekBar.setProgress(msg.arg1);
                }
            }
        }

        class BackThread extends Thread {

            Handler handler;

            public BackThread(Handler handler) {
                this.handler = handler;
            }

            @Override
            public void run() {
                super.run();
                int i = 0;
                while (i < 100){

                    Message message = new Message();
                    message.what = 1;
                    message.arg1 = i;
                    handler.sendMessage(message);
                    i++;

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }

        MyHanlder myHanlder = new MyHanlder();

        BackThread backThread = new BackThread(myHanlder);
        backThread.setDaemon(true);
        backThread.start();


    }


        private void moveSeekBar_leakcase() {

        final Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                if (msg.what == 0) {
                    mBinding.seekBar.setProgress(msg.arg1);
                }
            }
        };



        new Thread(){
            @Override
            public void run() {
                super.run();
                int i = 0;
                while(i < 100) {
                    Message message = new Message();
                    message.what = 0;
                    message.arg1 = i;
                    handler.sendMessage(message);
                    i++;

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }.start();



    }
    /**
     * 별도의 worker Therad를 통하여 수행
     */
    private void performTheread4() {

        //non-static inner class와 anonymous class는 outer class에 대한 implicit reference를 갖고,
        // static inner class의 경우에는 갖지 않는다.

//        Handler handler = new Handler(){
//            @Override
//            public void handleMessage(Message msg) {
//                super.handleMessage(msg);
//                if (msg.what == 1){
//                    mBinding.tvChagneValue.setText(String.format(Locale.US,"%d",msg.arg1));
//                }
//            }
//        };


        class MyHanlder extends Handler{
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 1){
                    mBinding.tvChagneValue.setText(String.format(Locale.US,"%d",msg.arg1));
                }
            }
        }



        class BackThread extends Thread {

            Handler handler;

            public BackThread(Handler handler) {
                this.handler = handler;
            }

            @Override
            public void run() {
                super.run();
                int i = 0;
                while (i < 5){

                    Message message = new Message();
                    message.what = 1;
                    message.arg1 = i;
                    handler.sendMessage(message);
                    i++;

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }

        MyHanlder myHanlder = new MyHanlder();

        BackThread backThread = new BackThread(myHanlder);
        backThread.setDaemon(true);
        backThread.start();

    }

    /**
     * post를 통한 변경
     */
    private void performTheread3() {
        final Handler handler = new Handler();

        new Thread(){
            @Override
            public void run() {
                int i = 0;
                while (i < 5){

                    final int finalI = i;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            mBinding.tvChagneValue.setText(String.format(Locale.US,"%d",finalI));
                            Log.d(TAG, "run Thread : " + finalI);
                        }
                    });

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    i++;
                }
            }
        }.start();


    }

    /**
     * Runnable을 통한 전달
     */
    private void performTheread2() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (i < 5){
                    //CalledFromWrongThreadException
                   mBinding.tvChagneValue.setText(String.format(Locale.US,"%d",i));
                    i++;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        new Thread(runnable).start();

    }

    /**
     * Thread를 통한 처리
     */
    private void performTheread1() {

        Thread thread = new Thread(){
            @Override
            public void run() {
                super.run();
                int i = 0;
                while (i < 5) {
                    //CalledFromWrongThreadException: Only the original thread that created a view hierarchy can touch its views.
                    mBinding.tvChagneValue.setText(i);
                    i++;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        };

        thread.start();

    }




    private void saveSharedPreference() {
        //1.Save Shared Preference
        String userid    = mBinding.tvParamValue.getText().toString();

        SharedPreferences sp = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("loginid",userid);
        editor.apply();
        Toast.makeText(this, "save data to shared reference" , Toast.LENGTH_SHORT).show();

        userid    = sp.getString("loginid", "default value");
        Toast.makeText(this, "load user : " + userid, Toast.LENGTH_SHORT).show();
    }



}
