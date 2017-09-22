package ca.cambrian.android.project.a3.threadtest;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ca.cambrian.android.project.a3.threadtest.databinding.ActivityTwoBinding;

public class TwoActivity extends AppCompatActivity {


    ActivityTwoBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_two);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_two);
        binding.setActivity(this);

        String data = getIntent().getStringExtra("data");
        binding.textView.setText(data);
    }
}
