package ca.cambrian.android.project.a3.threadtest;

import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import ca.cambrian.android.project.a3.threadtest.databinding.ActivityReadWriteToFileBinding;

public class ReadWriteToFile extends AppCompatActivity {

    public static final String TAG = "ReadWriteToFile";

    ActivityReadWriteToFileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_read_write_to_file);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_read_write_to_file);
        binding.setActivity(this);

        if (Build.VERSION.SDK_INT > 9){
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }



    }


    public void onWrite(View view){
        Toast.makeText(this, "onWrite", Toast.LENGTH_SHORT).show();

        String message = binding.textView2.getText().toString();

        try {
            FileOutputStream f = openFileOutput("download",MODE_PRIVATE);
            f.write(message.getBytes());
            f.close();
            Toast.makeText(this, "writefile somewhere", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void onRead(View view){
        Toast.makeText(this, "onRead", Toast.LENGTH_SHORT).show();

        String message;
        try {
            FileInputStream fileInputStream = openFileInput("download");

            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();

            while ((message = bufferedReader.readLine()) != null){
                stringBuffer.append(message + "\n");
            }

            binding.textView3.setText(stringBuffer.toString());
            Log.d (TAG,stringBuffer.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void onDownload(View view){
        Toast.makeText(this, "onDownload", Toast.LENGTH_SHORT).show();

        String message;
        try {

            URL url = new URL("https://www.naver.com");
            InputStream in = url.openStream();

            //FileInputStream fileInputStream = openFileInput("download");

            InputStreamReader inputStreamReader = new InputStreamReader(in);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();

            while ((message = bufferedReader.readLine()) != null){
                stringBuffer.append(message + "\n");
            }

            binding.textView3.setText(stringBuffer.toString());
            Log.d (TAG,stringBuffer.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
