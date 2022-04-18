package com.example.eloitteproject;

import java.util.ArrayList;

public class Video {
    public Video(String id, String name, String desc, String source, String videoId, int picture) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.source = source;
        this.videoId = videoId;
        this.picture = picture;
    }

    private String id, name, desc, source, videoId;
    private int picture;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    public static ArrayList<Video> getVideo() {
        ArrayList<Video> videos = new ArrayList<>();
        videos.add(new Video("1", "Time Out", "In this guided relaxation, we learn a special way of taking time out for ourselves, so that we can feel calmer and happier whenever we need to.",
                "Source: Cosmic Kids Yoga", "9_vEZTrmtyA", R.drawable.timeout));
        videos.add(new Video("2", "Thought Bubbles", "Ever feel that your mind is too busy? Do the 'Thought Bubbles' video to blow them away.",
                "Source: The MH Teacher", "70j3xyu7OGw", R.drawable.chart));
        videos.add(new Video("3", "The Body Scanner", "A super relaxing lying down body scan! Great for your emotion check-ins or the end of the school day.",
                "Source: The MH Teacher", "xLoK5rOl8Qk", R.drawable.body_scan));
        videos.add(new Video("4", "5 Minute Focus", "This is a short 5 minute guided meditation designed to help you improve your focus.  The more you practice, the better you will become.",
                "Source: Great Meditation", "zSkFFW--Ma0", R.drawable.target));
        videos.add(new Video("5", "Rainbow Relaxation", "Enjoy this breathing activity! Made to put a smile on your face and relax you.",
                "Source: The MH Teacher", "IIbBI-BT9c4", R.drawable.rainbow));
        videos.add(new Video("6", "Snowy Sensations", "Use your imagination to sense the snowflakes on your skin. A different type of Body Scan!",
                "Source: The MH Teacher", "zkm2lFVVlqE", R.drawable.snowman));
        videos.add(new Video("7", "Breath of Thunder", "In this Guided Meditation you can practice this breathing exercise designed to overcome any fears or worries...allowing the thunder to dissolve them and the rain to wash them away.",
                "Source: New Horizon", "R4VxP7Mx5kU", R.drawable.rain));
        videos.add(new Video("8", "Moon and Stars", "In this Cosmic Kids guided relaxation, we imagine we're enjoying looking at the moon and the stars!",
                "Source: Cosmic Kids Yoga", "QQCnWvwrO8U", R.drawable.moon));
        videos.add(new Video("9", "Belly Breathing", "This deep breathing technique is at the core of many mindfulness and relaxation practices.",
                "Source: The MH Teacher", "RiMb2Bw4Ae8", R.drawable.lithotherapy));
        videos.add(new Video("10", "Magic Treehouse", "In this Cosmic Kids guided relaxation, we discover a magical place at the top of the garden where we feel safe and happy.",
                "Source: Cosmic Kids Yoga", "WhoIeqDJM6E", R.drawable.treehouse));

        return videos;
    }

    //filters through every instance of the array list until it finds an ID that matches the input
    public static Video getName(String Id) {
        Video selectedName = null;
        for (Video v : getVideo()) {
            if (Id.equals(v.getId())) {
                selectedName = v;
            }
        }
        return selectedName;
    }
}

