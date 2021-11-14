package com.talhamustafa.i180573_180417;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

class ChatMsgAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context context;
    ArrayList<ChatMsg> list;
    String rid;
    String sid;
    public static final int ChatMsg_TYPE_IN = 1;
    public static final int ChatMsg_TYPE_OUT = 2;
    public static final int ChatMsg_TYPE_OUT_IMAGE = 3;
    public static final int ChatMsg_TYPE_IN_IMAGE = 4;
    public static final int ChatMsg_TYPE_OUT_IMAGE_ONLY = 5;
    public static final int ChatMsg_TYPE_IN_IMAGE_ONLY = 6;

    public ChatMsgAdapter(Context context, ArrayList<ChatMsg> list, String rid, String sid) { // you can pass other parameters in constructor
        this.context = context;
        this.sid=sid;
        for(int i=0;i<list.size();i++){
            Log.d("ChatMsgs",list.get(i).text);
        }
        this.list=list;

        this.rid=rid;
    }

    private class ChatMsgInViewHolder extends RecyclerView.ViewHolder {

        TextView ChatMsgTV;
        ChatMsgInViewHolder(final View itemView) {
            super(itemView);
            ChatMsgTV = itemView.findViewById(R.id.text_message1);

        }
        void bind(int position) {
            ChatMsg ChatMsg = list.get(position);
            ChatMsgTV.setText(ChatMsg.text);

        }
    }

    private class ChatMsgOutViewHolder extends RecyclerView.ViewHolder {

        TextView ChatMsgTV;
        ChatMsgOutViewHolder(final View itemView) {
            super(itemView);
            ChatMsgTV = itemView.findViewById(R.id.text_message);

        }
        void bind(int position) {
            ChatMsg ChatMsg = list.get(position);
            ChatMsgTV.setText(ChatMsg.text);

        }
    }
/*
    private class ChatMsgImageInViewHolder extends RecyclerView.ViewHolder {

        TextView ChatMsgTV;
        ImageView image;
        ChatMsgImageInViewHolder(final View itemView) {
            super(itemView);
            ChatMsgTV = itemView.findViewById(R.id.text_received_image);
            image=itemView.findViewById(R.id.image_received);


        }
        void bind(int position) {
            ChatMsg ChatMsg = list.get(position);
            ChatMsgTV.setText(ChatMsg.text);
            if(ChatMsg.getContainsImage()){
                Picasso.get().load(ChatMsg.getImgSrc()).fit().centerCrop().into(image);
            }


        }
    }

    private class ChatMsgImageOutViewHolder extends RecyclerView.ViewHolder {

        TextView ChatMsgTV;
        ImageView image;
        ChatMsgImageOutViewHolder(final View itemView) {
            super(itemView);
            ChatMsgTV = itemView.findViewById(R.id.text_sent_image);
            image=itemView.findViewById(R.id.image_sent);


        }
        void bind(int position) {
            ChatMsg ChatMsg = list.get(position);
            ChatMsgTV.setText(ChatMsg.text);
            if(ChatMsg.getContainsImage()){
                Picasso.get().load(ChatMsg.getImgSrc()).fit().centerCrop().into(image);
            }


        }
    }
*/

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ChatMsg_TYPE_IN) {
            return new ChatMsgInViewHolder(LayoutInflater.from(context).inflate(R.layout.item_container_received_message, parent, false));
        }
        else//(viewType == ChatMsg_TYPE_OUT){
        {
            return new ChatMsgOutViewHolder(LayoutInflater.from(context).inflate(R.layout.item_container_sent_message, parent, false));
        }
        /*
        else if(viewType == ChatMsg_TYPE_OUT_IMAGE){
            return new ChatMsgImageOutViewHolder(LayoutInflater.from(context).inflate(R.layout.ChatMsg_sent_image, parent, false));
        }
        else{
            return new ChatMsgImageInViewHolder(LayoutInflater.from(context).inflate(R.layout.ChatMsg_received_image, parent, false));
        }*/


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (list.get(position).senderId.equals(rid)  && list.get(position).containsImage.equals(false)) {
            ((ChatMsgInViewHolder) holder).bind(position);
        }
        else{
            ((ChatMsgOutViewHolder) holder).bind(position);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (list.get(position).senderId.equals(rid)  && list.get(position).containsImage.equals(false)) {
            return 1;
        }
        else if(list.get(position).senderId.equals(rid) && list.get(position).containsImage.equals(true)){
            return 4;
        }
        else if(list.get(position).senderId.equals(sid)&& list.get(position).containsImage.equals(false)){
            return 2;
        }
        else{
            return 3;
        }


    }
}
