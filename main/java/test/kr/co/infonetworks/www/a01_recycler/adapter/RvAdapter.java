package test.kr.co.infonetworks.www.a01_recycler.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import test.kr.co.infonetworks.www.a01_recycler.R;
import test.kr.co.infonetworks.www.a01_recycler.fragment.MasterFragment;
import test.kr.co.infonetworks.www.a01_recycler.model.Model;

/**
 * Created by andy on 2017. 12. 2..
 */

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.ViewHolder>{

    private static final String TAG = "RvAdapter" ;
    private List<Model> mModels;
    private Context mContext;
    private MasterFragment.OnSelectedListener mOnSelectedListener;

    public RvAdapter(List<Model> models, Context context, MasterFragment.OnSelectedListener onSelectedListener) {
        mModels = models;
        mContext = context;
        mOnSelectedListener = onSelectedListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Model model = mModels.get(position);

        holder.mImage.setImageDrawable(model.getImage());
        holder.mTitle.setText(model.getName());


    }

    @Override
    public int getItemCount() {
        return mModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView mImage;
        private TextView mTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            mImage = itemView.findViewById(R.id.ivImage);
            mTitle = itemView.findViewById(R.id.tvTitle);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            //Toast.makeText(mContext,"text", Toast.LENGTH_SHORT).show();
            TextView textView = view.findViewById(R.id.tvTitle);

            mOnSelectedListener.onSelected(textView.getText().toString());
            //Log.d(TAG, "onclick-onrecyclerview");
        }
    }

}
