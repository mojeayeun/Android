package com.example.h02.test_20170711;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText mUserName;
    private EditText mPassWord;
    private Button mLoginBtn;

    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mUserName = (EditText) findViewById(R.id.username);
        mPassWord = (EditText) findViewById(R.id.password);
        mLoginBtn = (Button) findViewById(R.id.loginBtn);


        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


               if ("andy".equals(mUserName.getText().toString()) && "1234".equals(mPassWord.getText().toString())){
                    Toast.makeText(MainActivity.this, "Login is success", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Login is fail", Toast.LENGTH_SHORT).show();
                }


            }
        });




    }
}
