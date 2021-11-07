package com.talhamustafa.i180573_180417;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    ArrayList<ContactList> arr;
    Context context, c;


    public RecyclerViewAdapter(ArrayList<ContactList> arr, Context context) {
        this.arr = arr;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view, parent, false);
        c = parent.getContext();

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int position) {

        final ContactList temp = arr.get(position);
        final int pos = position;

        holder.img.setImageResource(arr.get(position).getImg());
        holder.name.setText(arr.get(position).getName());
        holder.msg.setText(arr.get(position).getMsg());
        holder.time.setText(arr.get(position).getTime());

        holder.online.setAlpha(arr.get(position).getOnline());
        holder.new_text.setAlpha(arr.get(position).getNew_text());

        holder.click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Chat.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("name",temp.getName());
                intent.putExtra("photo",temp.getImg());
                context.startActivity(intent);
                //((Activity) c).startActivityForResult(intent, 2);

            }
        });


    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        CircleImageView img;
        TextView online, name, msg, time, new_text;
        Button click;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            online = itemView.findViewById(R.id.online);
            name = itemView.findViewById(R.id.name);
            msg = itemView.findViewById(R.id.msg);
            new_text = itemView.findViewById(R.id.new_text);
            time = itemView.findViewById(R.id.time);
            img = itemView.findViewById(R.id.dp);
            click = (Button) itemView.findViewById(R.id.click);
        }
    }
}

