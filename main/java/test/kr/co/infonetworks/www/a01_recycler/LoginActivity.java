package test.kr.co.infonetworks.www.a01_recycler;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";

    private EditText mLoginId, mPassword;
    private Button mLoginBtn,mSetBtn;
    private ProgressBar mProgressBar;
    private LoingTask mLoingTask = null;

    private static final String[] UESERTABLE = {"a@a.com:1234" , "b@b.com:1234"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        mLoginId = findViewById(R.id.etLoginId);
        mPassword = findViewById(R.id.etPassword);
        mLoginBtn = findViewById(R.id.btnLogin);
        mProgressBar = findViewById(R.id.loginProgressBar);
        mSetBtn = findViewById(R.id.btnSet);
        //initialize ProgressBar
        hideProgressBar();

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    performlogin();

            }
        });

        mSetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLoginId.setText("a@a.com");
                mPassword.setText("1234");

            }
        });


    }



    private void performlogin() {

        // id , pwd
        String loginid = mLoginId.getText().toString();
        String password = mPassword.getText().toString();

        //nullcheck
        if (TextUtils.isEmpty(loginid) || !isValidLoginId(loginid)){
            Toast.makeText(this, "enter login id", Toast.LENGTH_SHORT).show();
            mLoginId.requestFocus();
            return;
        }else if (TextUtils.isEmpty(password) || !isValidPassword(password)){
            Toast.makeText(this, "enter password", Toast.LENGTH_SHORT).show();
            mPassword.requestFocus();
            return;
        }else{

            mLoingTask = new LoingTask(loginid,password);
            mLoingTask.execute();

        }


    }

    private boolean isValidLoginId(String loginid) {
        return loginid.contains("@");
    }

    private boolean isValidPassword(String password) {
        return password.length() > 3;
    }


    private class LoingTask extends AsyncTask<Void,Void,Boolean>{

        private String loginid;
        private String password;

        public LoingTask(String id, String password) {
            this.loginid = id;
            this.password = password;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showProgressBar();
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            Boolean flag = false;

            //처리가 되는것 처럼 보여야 한다.
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //cancel이 되지 않았으면 수행된다
            if (!isCancelled()) {
                for (String user : UESERTABLE) {
                    String[] pieces = user.split(":");
                    if (pieces[0].equals(loginid) && pieces[1].equals(password)) {
                        flag = true;
                        break;  // true이면 for문을 빠져 나간다
                    }
                }
            }
            return flag;
        }

        @Override
        protected void onPostExecute(Boolean flag) {
            super.onPostExecute(flag);
            mLoingTask = null;
            hideProgressBar();

            //login에 성공했다면
            if (flag) {

                goNext(loginid);
            }else{
                mLoginId.requestFocus();
                Toast.makeText(LoginActivity.this, "Wroing user id or password", Toast.LENGTH_SHORT).show();
            }


        }


        //취소되었을경우 처리
        @Override
        protected void onCancelled() {
            super.onCancelled();
            mLoingTask = null;
        }
    }

    private void goNext(String loginid) {
        //다음 Activity로 저장한다.
        Intent intent = ThreadActivity.getIntent(this);
        intent.putExtra("loginid",loginid);
        Log.d(TAG,"login id " + loginid);
        startActivity(intent);

    }

    private void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.INVISIBLE);
    }


}
