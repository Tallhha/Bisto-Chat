package com.talhamustafa.i180573_180417;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder> {

    ArrayList<ChatMsg> msg;
    Context context, c;

    public ChatAdapter(ArrayList<ChatMsg> msg, Context context) {
        this.msg = msg;
        this.context = context;
    }

    @NonNull
    @Override
    public ChatAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_container_sent_message, parent, false);
        c = parent.getContext();

        return new MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ChatAdapter.MyViewHolder holder, int pos) {

        //final CallLogs temp = arr.get(position);
        final int position = pos;

        holder.text_message.setText(msg.get(position).getMsg());
        holder.text_message1.setText(msg.get(position).getReply());
        holder.text_time.setText(msg.get(position).getTime());
        holder.text_time1.setText(msg.get(position).getRtime());
        holder.dp.setImageResource(msg.get(position).getDp());
        holder.ss.setAlpha(msg.get(position).getSs());
        holder.image.setImageBitmap(msg.get(position).getBitmap());

        holder.text_message.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                final EditText edittext = new EditText(context);
                dialog.setTitle("Delete?");
                dialog.setMessage(msg.get(position).getMsg());
                dialog.setView(edittext);

                dialog.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MyDBHelper helper = new MyDBHelper(context);
                        SQLiteDatabase database = helper.getWritableDatabase();
                        database.delete(MyContacts.Chats.TABLENAME, MyContacts.Chats._ID+" =?",new String[]{msg.get(position).getId()});
                        msg.remove(position);
                        notifyDataSetChanged();
                    }
                });

                dialog.setNegativeButton("EDIT", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String YouEditTextValue = edittext.getText().toString();
                        holder.text_message.setText(YouEditTextValue);
                        MyDBHelper helper = new MyDBHelper(context);
                        helper.updateMsg(msg.get(position).getMsg(),msg.get(position).getTime(),YouEditTextValue);
                    }
                });
                AlertDialog d = dialog.create();
                d.show();

                return false;
            }
        });



    }

    @Override
    public int getItemCount() {
        return msg.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {

        TextView text_message, text_message1, text_time, text_time1, ss;
        //EditText text_message_edit;
        ImageView image;
        CircleImageView dp;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            text_message = itemView.findViewById(R.id.text_message);
            text_message1 = itemView.findViewById(R.id.text_message1);
            text_time = itemView.findViewById(R.id.text_time);
            text_time1 = itemView.findViewById(R.id.text_time1);
            //text_message_edit = itemView.findViewById(R.id.text_message_edit);
            image = itemView.findViewById(R.id.image);
            dp = itemView.findViewById(R.id.dp);
            ss = itemView.findViewById(R.id.ss);

        }
    }
}


