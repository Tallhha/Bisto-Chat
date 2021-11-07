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

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.MyViewHolder> {

    ArrayList<Add_Contacts> arr;
    Context context, c;


    public ContactsAdapter(ArrayList<Add_Contacts> arr, Context context) {
        this.arr = arr;
        this.context = context;
    }

    @NonNull
    @Override
    public ContactsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contacts_view, parent, false);
        c = parent.getContext();

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsAdapter.MyViewHolder holder, int position) {

        //final Add_Contacts temp = arr.get(position);
        //final int pos = position;

        //holder.img.setImageResource(arr.get(position).getImg());
        if(arr.get(position).getImg() == null){
            holder.img.setImageResource(R.drawable.empty_cont);
        }
        else {
            holder.img.setImageBitmap(arr.get(position).getImg());
        }

        holder.name.setText(arr.get(position).getName());
        holder.msg.setText(arr.get(position).getPhone());
        holder.log.setImageResource(R.drawable.ic_back);

    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        CircleImageView img;
        ImageView log;
        TextView name, msg;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            msg = itemView.findViewById(R.id.msg);
            log = itemView.findViewById(R.id.log);
            img = itemView.findViewById(R.id.dp);

        }
    }
}


