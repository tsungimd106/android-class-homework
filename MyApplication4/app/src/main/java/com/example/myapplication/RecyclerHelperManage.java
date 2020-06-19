package com.example.myapplication;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerHelperManage extends RecyclerView.Adapter<RecyclerHelperManage.ViewHolder> {
    private List<DataBean> data;
    private OnItemClickListener onClickListener;
    public RecyclerHelperManage(List<DataBean> data) {
        this.data = data;
    }

    public void setListener(OnItemClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
    public interface OnItemClickListener{
        void onItemClick( int position);
    }

    @NonNull
    @Override
    public RecyclerHelperManage.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mitem, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        if (data.isEmpty()) {
        } else {
            holder.txtTime.setText(data.get(position).getTime());
            holder.txtName.setText(data.get(position).getName());
            holder.txtCount.setText(data.get(position).getCount());
            holder.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.onItemClick(position);
                }
            });
        }

    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtName, txtTime, txtCount;

        private LinearLayout linearLayout;

        public ViewHolder(View v) {
            super(v);
            linearLayout = v.findViewById(R.id.linerlayout);
            txtName = v.findViewById(R.id.txtName);
            txtTime = v.findViewById(R.id.txtTime);
            txtCount = v.findViewById(R.id.txtcount);

        }
    }

}
