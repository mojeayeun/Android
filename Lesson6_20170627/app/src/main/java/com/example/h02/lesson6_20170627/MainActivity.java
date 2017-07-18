package com.example.h02.lesson6_20170627;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static android.view.inputmethod.EditorInfo.IME_ACTION_DONE;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";

    TextView mTextView;
    Button mButton;
    EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.testView);

        mTextView.setText("this is test");

        mButton = (Button) findViewById(R.id.button2);

        mEditText = (EditText) findViewById(R.id.txtName);

        mEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                Log.d(TAG,"textView: " + textView.getText() + " int: " + i + " KeyEvent: " + keyEvent.toString());

                //IME_ACTION_DONE

                mTextView.setText(textView.getText());
                return false;
            }
        });

    }
}
