package com.example.eloitteproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.MyViewHolder> {
    public ArrayList<Video> mVideo;
    public ClickListener listener;

    public VideoAdapter(Context context, ArrayList<Video> mVideo, ClickListener listener) {
        this.mVideo = mVideo;
        this.listener = listener;
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name;
        public ImageView picture;
        ClickListener listener;
        final VideoAdapter mAdapter;

        public MyViewHolder(@NonNull View itemView, VideoAdapter mAdapter, ClickListener listener) {
            super(itemView);
            name = itemView.findViewById(R.id.pName);
            picture = itemView.findViewById(R.id.profilePic);
            this.mAdapter = mAdapter;
            this.listener = listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(getAdapterPosition());
        }
    }

    //Allows click events to be caught
    public interface ClickListener {
        void onClick(int position);
    }

    //inflate the row layout from xml when needed (just the view, no data)
    @NonNull
    @Override
    public VideoAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.meditation_item_row, parent, false);
        return new MyViewHolder(view, this, listener);
    }

    //binds the data to the TextView Elements in each row
    @Override
    public void onBindViewHolder(@NonNull VideoAdapter.MyViewHolder holder, int position) {
        Video video = mVideo.get(position);
        holder.name.setText(video.getName());
        holder.picture.setImageResource(video.getPicture());


    }

    @Override
    public int getItemCount() {
        return mVideo.size();
    }

}
