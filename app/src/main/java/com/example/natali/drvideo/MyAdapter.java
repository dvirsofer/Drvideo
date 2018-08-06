package com.example.natali.drvideo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    private List<UserItem> users;
    private int rowLayout;
    private Context mContext;
    public MyAdapter(List<UserItem> users, int rowLayout, Context context) {
        this.users = users;
        this.rowLayout = rowLayout;
        this.mContext = context;
    }
    @Override
    public int getItemCount() {
        return users == null ? 0 : users.size();
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        UserItem myItem = users.get(i);
        viewHolder.name.setText(myItem.getName());

        //        viewHolder.image.setImageDrawable(mContext.getDrawable(myItem.getImg()));
        viewHolder.email.setText(myItem.getEmail());
        viewHolder.email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("TAG", "3");
            }
        });

    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public Button email;
        public ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.text_name);
            image = (ImageView) itemView.findViewById(R.id.image);
            email = (Button) itemView.findViewById(R.id.btn_phone);
        }
    }
}
