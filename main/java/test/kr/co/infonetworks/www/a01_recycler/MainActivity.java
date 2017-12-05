package test.kr.co.infonetworks.www.a01_recycler;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import test.kr.co.infonetworks.www.a01_recycler.fragment.MasterFragment;

public class MainActivity extends FragmentActivity implements MasterFragment.OnSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //fragment add
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.framelayout,new MasterFragment()).commit();

    }

    @Override
    public void onSelected(String data) {
        Toast.makeText(this, "call from fragment data: " + data, Toast.LENGTH_SHORT).show();
    }

    public void opendata(){

    }
}
