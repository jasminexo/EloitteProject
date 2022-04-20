package com.example.eloitteproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.BoardViewHolder> {

    private List<User> mUserData;

    public BoardAdapter(List mUserData) {
        this.mUserData = mUserData;
    }

    public void setBoards(List<User> users) {
        this.mUserData.addAll(users);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public BoardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mBoardItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.boarditem, parent, false);
        BoardAdapter.BoardViewHolder bvh = new BoardAdapter.BoardViewHolder(mBoardItemView);
        return bvh;
    }

    @Override
    public void onBindViewHolder(@NonNull BoardViewHolder holder, int position) {
        User mCurrentUser = mUserData.get(position);
        holder.boardPoints.setText("\uD83C\uDFC6 " + String.valueOf(mCurrentUser.getScore()));
        holder.pName.setText(mCurrentUser.getFullName());
        holder.profilePic.setImageResource(mCurrentUser.getProfilePic());

//        Glide.with(holder.profilePic)
//                .load("https://ui-avatars.com/api/?name=" + mCurrentUser.getFullName())
//                .into(holder.profilePic);
    }

    @Override
    public int getItemCount() {
        return mUserData.size();
    }

    public class BoardViewHolder extends RecyclerView.ViewHolder {
        private TextView boardPoints, pName;
        private ImageView profilePic;

        public BoardViewHolder(@NonNull View itemView) {
            super(itemView);
            profilePic = itemView.findViewById(R.id.profilePic);
            pName = itemView.findViewById(R.id.pName);
            boardPoints = itemView.findViewById(R.id.boardPoints);
        }
    }

    //Sort method for users
    public void sort(final int sortUserMethod) {
        if (mUserData.size() > 0) {
            Collections.sort(mUserData, new Comparator<User>() {
                @Override
                public int compare(User u1, User u2) {
                    if (sortUserMethod == 1) {
                        //Sort by name
                        return u1.getFullName().compareTo(u2.getFullName());
                    } else if (sortUserMethod == 2) {
                        //Sort by points
                        return u2.getScore().compareTo(u1.getScore());
                    }
                    return String.valueOf(u2.getScore()).compareTo(String.valueOf(u1.getScore()));
                }
            });
        }
        notifyDataSetChanged();
    }
}

