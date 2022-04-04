package com.example.eloitteproject;

import java.util.ArrayList;

public class Click {

    private int id;
    private String activityTitle;
    private int points;

    public Click(int id, String activityTitle, int points) {
        this.id = id;
        this.activityTitle = activityTitle;
        this.points = points;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getActivityTitle() {
        return activityTitle;
    }

    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public static ArrayList<Click> getClickList(){
        ArrayList <Click> clickList = new ArrayList<>();
        clickList.add(new Click(1, "Daily Check-In", 50));
        clickList.add(new Click(2, "Quiz", 20));
        clickList.add(new Click(3, "Dashboard", 20));
        clickList.add(new Click(4, "Leaderboard", 20));
        clickList.add(new Click(5, "Meditation", 20));

        return clickList;
    }

}
