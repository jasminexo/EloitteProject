package com.example.eloitteproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TeacherScoreFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TeacherScoreFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    //Declare our variables
    ImageButton filterButton, emailButton;
    private TeacherBoardAdapter mAdapter;

    public TeacherScoreFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ScoreFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TeacherScoreFragment newInstance(String param1, String param2) {
        TeacherScoreFragment fragment = new TeacherScoreFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.teacher_fragment_score, container, false);
    }

    //NON AUTO STUFF BEGINS HERE
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        //Initialising widgets
        filterButton = getView().findViewById(R.id.filterBtn);
        emailButton = getView().findViewById(R.id.emailButton);

        //Instantiate the menu to sort the rows in leaderboard
        PopupMenu dropDownMenu = new PopupMenu(getActivity(), filterButton);
        final Menu menu = dropDownMenu.getMenu();
        menu.add(0, 0, 0, "Points");
        menu.add(0, 1, 0, "Name");

        dropDownMenu.getMenuInflater().inflate(R.menu.menu_score, menu);

        dropDownMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    //Sort by Points
                    case 0:
                        mAdapter.sort(2);
                        return true;
                    //Sort by Name
                    case 1:
                        mAdapter.sort(1);
                        return true;
                    //By default sort by points
                    default:
                        mAdapter.sort(2);
                        return true;
                }
            }
        });

        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dropDownMenu.show();
            }
        });

//        emailButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {goToEmail();}
//        });


        //Create RecyclerView
        RecyclerView mRecyclerView = (RecyclerView) getView().findViewById(R.id.leaderboardRecyclerView);
        mRecyclerView.setHasFixedSize(true);

        // Give the RecyclerView a default layout manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new TeacherBoardAdapter(new ArrayList<User>());
        mRecyclerView.setAdapter(mAdapter);


        //User database
        UserDatabase uDB = Room.databaseBuilder(getActivity(), UserDatabase.class,
                "user-database")
                .fallbackToDestructiveMigration()
                .build();

        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                List<User> userList = uDB.userDao().getAllUsers();
                if (getActivity() != null) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mAdapter.setBoards(userList);

                        }
                    });
                }
            }
        });
    }

}