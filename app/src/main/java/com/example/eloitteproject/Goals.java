package com.example.eloitteproject;

public class Goals {

    String dategoals, descgoals, titlegoals;

    public Goals() {
    }

    public Goals(String dategoals, String descgoals, String titlegoals) {
        this.dategoals = dategoals;
        this.descgoals = descgoals;
        this.titlegoals = titlegoals;
    }

    public String getDategoals() {
        return dategoals;
    }

    public void setDategoals(String dategoals) {
        this.dategoals = dategoals;
    }

    public String getDescgoals() {
        return descgoals;
    }

    public void setDescgoals(String descgoals) {
        this.descgoals = descgoals;
    }

    public String getTitlegoals() {
        return titlegoals;
    }

    public void setTitlegoals(String titlegoals) {
        this.titlegoals = titlegoals;
    }
}
