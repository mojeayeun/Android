package ca.cambrian.android.project.a3.threadtest;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import ca.cambrian.android.project.a3.threadtest.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {


    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setActivity(this);


        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+1:00"));
        Date currentLocalTime = cal.getTime();
        DateFormat date = new SimpleDateFormat("HH:mm a");
        date.setTimeZone(TimeZone.getTimeZone("GMT+1:00"));

        // final String localTime = date.format(currentLocalTime);

//        final Thread thread = new Thread(){
//            @Override
//            public void run() {
////                binding.tvTitle.setText(localTime);
////                MyTask myTask = new MyTask(MainActivity.this,mTitle,mButton);
//
//                int i = 0;
//                while (i < 5) {
//                    try {
//                        sleep(5000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    myTask.execute();
//                    i++;
//                }
//            }
//        };

    }

        public void onDownloadClick(View view){
            Toast.makeText(MainActivity.this, "clickclik", Toast.LENGTH_SHORT).show();
            MyTask myTask = new MyTask(MainActivity.this,binding.tvTitle,binding.downloadBtn);
            myTask.execute();
            binding.downloadBtn.setEnabled(false);

        }

        public void onGotoNextPageButtonClick(View view) {
            Intent intent = new Intent(MainActivity.this,TwoActivity.class);
            intent.putExtra("data","test");
            startActivity(intent);

        }

        public void onInternetClick(View view) {
            Uri uri = Uri.parse("http://www.google.com");
            Intent it  = new Intent(Intent.ACTION_VIEW,uri);
            startActivity(it);

        }

        public void onPhoneCallClick(View view) {
            Uri uri = Uri.parse("tel:xxxxxx");
            Intent it = new Intent(Intent.ACTION_DIAL, uri);
            startActivity(it);

        }

}
