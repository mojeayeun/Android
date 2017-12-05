package test.kr.co.infonetworks.www.a01_recycler;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ResultReturnActivity extends AppCompatActivity {
    public static final int REQUEST_NUM = 0;
    private static final String TAG = "ResultReturnActivity";

    private String mString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_return);



        Button btnClose = findViewById(R.id.btnClose);
        Button btnNext = findViewById(R.id.btnNext);

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("RESULT","this is return data");
                setResult(RESULT_OK,intent);
                finish();
            }
        });


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultReturnActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });


        Button btnSet = findViewById(R.id.btnSet);
        Button btnGet = findViewById(R.id.btnGet);

        //p.520 자동복원기능을 가진뷰 (TextView와 파생된 하위뷰는 자동복원된다)
        //단 id가 지정되어야 한다
        btnSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mString = "Global Varialbe";
                Toast.makeText(ResultReturnActivity.this, "set data: " + mString , Toast.LENGTH_SHORT).show();
            }
        });

        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ResultReturnActivity.this, "get data: " + mString , Toast.LENGTH_SHORT).show();
            }
        });


        if (savedInstanceState != null){
            String data = savedInstanceState.getString("savedata");
            Toast.makeText(this, "onCreate() data : " + data, Toast.LENGTH_SHORT).show();
        }


    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("savedata","onSaveInstanceState");
        Log.d(TAG, "call onSaveInstanceState");
    }
}
