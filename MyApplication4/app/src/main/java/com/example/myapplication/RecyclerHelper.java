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

public class RecyclerHelper extends RecyclerView.Adapter<RecyclerHelper.ViewHolder> {
    private List<String> data;

    public RecyclerHelper(List<String> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public RecyclerHelper.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.textView.setText(data.get(position));
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        private LinearLayout linearLayout;

        public ViewHolder(View v) {
            super(v);
            linearLayout = v.findViewById(R.id.linerlayout);
            textView=v.findViewById(R.id.txtName);
            textView.setGravity(Gravity.CENTER);
        }
    }

}
