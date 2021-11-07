package com.talhamustafa.i180573_180417;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class CallLogsAdapter extends RecyclerView.Adapter<CallLogsAdapter.MyViewHolder> {

    ArrayList<CallLogs> arr;
    Context context, c;


    public CallLogsAdapter(ArrayList<CallLogs> arr, Context context) {
        this.arr = arr;
        this.context = context;
    }

    @NonNull
    @Override
    public CallLogsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.call_logs_view, parent, false);
        c = parent.getContext();

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CallLogsAdapter.MyViewHolder holder, int position) {

        //final CallLogs temp = arr.get(position);
        //final int pos = position;

        holder.img.setImageResource(arr.get(position).getImg());
        holder.name.setText(arr.get(position).getName());
        holder.msg.setText(arr.get(position).getMsg());
        holder.online.setAlpha(arr.get(position).getOnline());
        holder.log.setImageResource(arr.get(position).getIncoming());

    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        CircleImageView img;
        ImageView log;
        TextView online, name, msg;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            online = itemView.findViewById(R.id.online);
            name = itemView.findViewById(R.id.name);
            msg = itemView.findViewById(R.id.msg);
            log = itemView.findViewById(R.id.log);
            img = itemView.findViewById(R.id.dp);

        }
    }
}


