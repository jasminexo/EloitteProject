package com.example.eloitteproject;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class GoalsAdapter extends RecyclerView.Adapter<GoalsAdapter.MyViewHolder> {

    Context context;
    ArrayList<Goals> goals;

    public GoalsAdapter(Context c, ArrayList<Goals> p) {
        context = c;
        goals = p;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.goals_item_row, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, int i) {
        myViewHolder.titlegoals.setText(goals.get(i).getTitlegoals());
        myViewHolder.descgoals.setText(goals.get(i).getDescgoals());
        myViewHolder.dategoals.setText(goals.get(i).getDategoals());
//        myViewHolder.keygoals.setText(goals.get(i).getKeygoals());

        final String getTitleGoals = goals.get(i).getTitlegoals();
        final String getDescGoals = goals.get(i).getDescgoals();
        final String getDateGoals = goals.get(i).getDategoals();
        final String getKeyGoals = goals.get(i).getKeygoals();

        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, StudentGoalsEditActivity.class);
                intent.putExtra("titlegoals", getTitleGoals);
                intent.putExtra("descgoals", getDescGoals);
                intent.putExtra("dategoals", getDateGoals);
                intent.putExtra("keygoals", getKeyGoals);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return goals.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView titlegoals, descgoals, dategoals, keygoals;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titlegoals = (TextView) itemView.findViewById(R.id.titlegoals);
            descgoals = (TextView) itemView.findViewById(R.id.descgoals);
            dategoals = (TextView) itemView.findViewById(R.id.dategoals);
        }
    }
}
