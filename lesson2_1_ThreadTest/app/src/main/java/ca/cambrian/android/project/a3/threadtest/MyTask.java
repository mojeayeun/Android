package ca.cambrian.android.project.a3.threadtest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.TextView;

import static java.lang.Thread.sleep;

/**
 * Created by h02 on 2017. 9. 15..
 */

//Params, Process, Result
public class MyTask extends AsyncTask <Void, Integer,String>{

    Context mContext;
    TextView mTextView;
    Button mButton;
    ProgressDialog mProgressDialog;

    public MyTask(Context mContext, TextView mTextView, Button mButton) {
        this.mContext = mContext;
        this.mTextView = mTextView;
        this.mButton = mButton;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mProgressDialog = new ProgressDialog(mContext);

        mProgressDialog.setTitle("Downlaoding Files");
        mProgressDialog.setMax(10);
        mProgressDialog.setProgress(0);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.show();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        mButton.setEnabled(true);
        mProgressDialog.hide();
    }


    @Override
    protected String doInBackground(Void... voids) {

        int i = 0;

        while (i < 10){
            try {
                sleep(100);
                publishProgress(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
    }

//        synchronized (this) {
//            while (i < 10) {
//                try {
//                    wait(100);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                i++;
//                publishProgress(i);
//            }
//        }


        return "Download Complete";
    }


    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);

        int progress = values[0];
        mProgressDialog.setProgress(progress);
        mTextView.setText("downloading");

    }





}
