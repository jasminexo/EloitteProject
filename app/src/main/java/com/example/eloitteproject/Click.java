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
        clickList.add(new Click(1, "Daily Check-In", 1));
        clickList.add(new Click(2, "Quiz", 1));
        clickList.add(new Click(3, "Goals", 1));
        clickList.add(new Click(4, "Leaderboard", 1));
        clickList.add(new Click(5, "Appointment", 1));

        return clickList;
    }

    //Getting an individual click
    public static int getClick(int clickId){
        int selectedClick = 0;
        for(Click c : Click.getClickList()){
            if (c.getId() == clickId){
                selectedClick = clickId;
            }
        }
        return selectedClick;
    }

}
