package test.kr.co.infonetworks.www.a01_recycler.fragment;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import test.kr.co.infonetworks.www.a01_recycler.R;
import test.kr.co.infonetworks.www.a01_recycler.adapter.RvAdapter;
import test.kr.co.infonetworks.www.a01_recycler.model.Model;


/**
 * A simple {@link Fragment} subclass.
 */
public class MasterFragment extends Fragment {

    private List<Model> mModels = new ArrayList<Model>();

    //1.Activity로 이벤트 콜백 메소드 만들기
    public interface OnSelectedListener{
        void onSelected(String data);
    }

    //2.리스너 변수 선언
    OnSelectedListener  mOnSelectedListener;

    //3.Attache에서 Activity의 Reference를 받아온다
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mOnSelectedListener = (OnSelectedListener)context;

    }

    public MasterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_master, container, false);


        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);

        //리니어레이아웃을 만들어서서 리스트 뷰를 제어
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        loadData();

        //어댑터를 만든다
        RvAdapter rvAdapter = new RvAdapter(mModels,getContext(),mOnSelectedListener);
        recyclerView.setAdapter(rvAdapter);



        return view;
    }

    private void loadData() {
        String[] titles = {"title01","title02","title03","title04","title05","title06"};

        for(String title: titles){
            Drawable drawable;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                drawable  = getResources().getDrawable(getResources().getIdentifier(title,"drawable",getActivity().getPackageName()),null);
            }else{
                drawable  = getResources().getDrawable(getResources().getIdentifier(title,"drawable",getActivity().getPackageName()));
            }
            mModels.add(new Model(drawable,title));

        }

    }

}
