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
            videos.add(new Video("1", "CV/Resume Best Practices", "A strong CV is crucial if you want to land interviews for the best jobs, so this video breaks down the CV writing process into simple steps.",
                    "Source: StandOut CV", "_fP43gcBywU", R.drawable.reading));
            videos.add(new Video("2", "How to write a cover letter", "A well written cover letter could increase your chances of an interview by 50%.This video shows you how to write an awesome cover letter that impresses employers.",
                    "Source: ZipJob", "jHg0b7Nai6c", R.drawable.reading));
            videos.add(new Video("3", "Recently graduated? Where to next?!", "A video that discusses life after graduating university/college, covering uncertainty, depression, fears, anxiety, finding your call, passion or vocation and more.",
                    "Source: Simple Victoria", "Has9xYpF4XQ", R.drawable.reading));
            videos.add(new Video("4", "How to write a professional email", "Need to learn how to write a professional email for your teachers and future employers or just interested in how a sandwich is made? Either way, look no further.",
                    "Source: CTL", "SMnjShkHCug", R.drawable.reading));
            videos.add(new Video("5", "Preparing for an interview", "Looking for interview tips? In this video, we dissect an entire job interview from start to finish, analysing everything from common interview questions to etiquette and how to follow up.",
                    "Source: Indeed", "HG68Ymazo18", R.drawable.reading));
            videos.add(new Video("6", "Video Interview tips and tricks", "This video shows 10 helpful tips for making the best impression you can in your video interview, and demonstrating that you’re a great fit for the role.",
                    "Source: Forbes", "Si4GLeQoqLA", R.drawable.reading));
            videos.add(new Video("7", "Acing the Assessment Centre", "A video that uncovers tips for each stage of the Assessment Centre, preparing you to crush that AC with ease.",
                    "Source: Afzal Hussein", "_mWqvsCC9kM", R.drawable.reading));
            videos.add(new Video("8", "Networking 101: How to make connections", "Knowing how to network can strengthen your business connections and might even lead to a job referral. In this video, we'll show you how to navigate a networking event like a pro.",
                    "Source: Indeed", "OVf5c7NthSw", R.drawable.reading));
            videos.add(new Video("9", "Persevering after professional rejection", "In this video, you’ll learn strategies for overcoming rejection.",
                    "Source: GCFLearnFree.org", "b3F5UATw_X8", R.drawable.reading));
            videos.add(new Video("10", "Things to know before changing career paths", "An insightful TED Talk video that raises questions that should be asked before changing careers.",
                    "Source: TEDx Talks", "MIjH8MCbONI", R.drawable.reading));

            return videos;
        }
        //filters through every instance of the array list until it finds an ID that matches the input
        public static Video getName(String Id) {
            Video selectedName = null;
            for (Video v: getVideo()) {
                if (Id.equals(v.getId())) {
                    selectedName = v;
                }
            }
            return selectedName;
        }
    }

