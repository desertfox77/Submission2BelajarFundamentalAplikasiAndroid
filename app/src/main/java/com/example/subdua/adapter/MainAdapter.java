package com.example.subdua.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.subdua.R;
import com.example.subdua.UserDetailActivity;
import com.example.subdua.model.User;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.UserViewHolder>{
    final private Context context;
    final private List<User> users ;

    public MainAdapter(Context context, List<User> users) {
        this.context = context;
        this.users = users;
    }

    @NonNull
    @Override
    public MainAdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_user,parent,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User up = users.get(position);
        holder.textView1.setText(up.getLogin());
        holder.textView2.setText(up.getHtmlUrl());
        Glide.with(holder.itemView.getContext())
                .load(up.getAvatarUrl())
                .into(holder.imageView);

    }


    @Override
    public int getItemCount() {
        return users.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView textView1,textView2;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_user_detail_row);
            textView1 = itemView.findViewById(R.id.tv_username_detail_row);
            textView2 = itemView.findViewById(R.id.tv_htmlurl_detail_row);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            User up = users.get(getAdapterPosition());
            Intent intent = new Intent(context, UserDetailActivity.class);
            intent.putExtra("LEO",up);
            v.getContext().startActivity(intent);
        }
    }
}

