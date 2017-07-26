package ca.cambrian.android.project.aaa.project;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class List extends AppCompatActivity {

    //Data
    private static final String TAG_TITLE = "title";
    private static final String TAG_SUBTITLE = "subtitle";
    private static final String TAG_IMAGE = "image";
    private static final String TAG_CONTENT = "content";

    private static final String TAG_PARAMETER = "param";


    //UI 관련
    private RecyclerView rv;
    private LinearLayoutManager mLinearLayoutManager;

    private ArrayList<HashMap<String,String>> travelList;



    private class TravelAdapter extends RecyclerView.Adapter<TravelAdapter.ViewHolder>{
        ArrayList<HashMap<String,String>> travelList;
        Context context;

        public TravelAdapter(Context context, ArrayList<HashMap<String, String>> travelList) {
            this.travelList = travelList;
            this.context = context;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(context).inflate(R.layout.item_notice,null);

            return new ViewHolder(v);
        }

        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            HashMap<String,String> travelItem = travelList.get(position);

            switch (position) {
                case 0:
                    holder.tv_titleimage.setImageResource(R.drawable.ic_action_name);
                    break;
                case 1:
                    holder.tv_titleimage.setImageResource(R.drawable.ic_action_name);
                    break;
                case 2:
                    holder.tv_titleimage.setImageResource(R.drawable.ic_action_name);
                    break;

            }

            holder.tv_title.setText(travelItem.get(TAG_TITLE)); //tittle
            holder.tv_subtitle.setText(travelItem.get(TAG_SUBTITLE)); //subtitle

            AssetManager am = context.getResources().getAssets() ;
            InputStream is = null ;

            try {
                is = am.open(travelItem.get(TAG_IMAGE));
                Bitmap bm = BitmapFactory.decodeStream(is) ;

                holder.tv_image.setImageBitmap(bm);
                is.close();


            } catch (IOException e) {
                e.printStackTrace();
            }


            holder.tv_content.setText(travelItem.get("content")); //content

            holder.tv_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Toast.makeText(context, "Recycle Click" + position, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(List.this,Detail.class);
                    intent.putExtra(TAG_PARAMETER, Integer.toString(position+1));
                    startActivity(intent);
                }
            });


        }

        @Override
        public int getItemCount() {
            return travelList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView tv_title;
            TextView tv_subtitle;
            TextView tv_content;
            ImageView tv_titleimage;
            ImageView tv_image;
            CardView cv;

            public ViewHolder(View v) {
                super(v);
                tv_title = (TextView) v.findViewById(R.id.tv_title);
                tv_subtitle = (TextView) v.findViewById(R.id.tv_subtitle);
                tv_content = (TextView) v.findViewById(R.id.tv_content);
                tv_titleimage = (ImageView) v.findViewById(R.id.imageView);
                tv_image = (ImageView) v.findViewById(R.id.iv_image);
                cv = (CardView) v.findViewById(R.id.cv);
            }


        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);


        travelList = new ArrayList<HashMap<String, String>>();


        mLinearLayoutManager = new LinearLayoutManager(this);
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        rv = (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(mLinearLayoutManager);
        loadData();


        //카드 리스트뷰 어댑터에 연결
        TravelAdapter adapter = new TravelAdapter(this,travelList);
        rv.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }

    private void loadData() {
        //HashMap에 붙이기
        HashMap<String,String> posts ;
        posts = new HashMap<String,String>();


        posts.put(TAG_TITLE,"Banff");
        posts.put(TAG_SUBTITLE,this.getResources().getString(R.string.banff_subtitle));
        posts.put(TAG_IMAGE,this.getResources().getString(R.string.banff_image));
        posts.put(TAG_CONTENT, this.getResources().getString(R.string.banff_content));
        travelList.add(posts);

        posts = new HashMap<String,String>();
        posts.put(TAG_TITLE,"Orland");
        posts.put(TAG_SUBTITLE,this.getResources().getString(R.string.orland_subtitle));
        posts.put(TAG_IMAGE,this.getResources().getString(R.string.orland_image));
        posts.put(TAG_CONTENT, this.getResources().getString(R.string.orland_content));
        travelList.add(posts);

        posts = new HashMap<String,String>();
        posts.put(TAG_TITLE,"Montreal");
        posts.put(TAG_SUBTITLE,this.getResources().getString(R.string.montral_subtitle));
        posts.put(TAG_IMAGE,this.getResources().getString(R.string.montral_image));
        posts.put(TAG_CONTENT, this.getResources().getString(R.string.montral_content));

        //ArrayList에 HashMap 붙이기
        travelList.add(posts);
    }
}
