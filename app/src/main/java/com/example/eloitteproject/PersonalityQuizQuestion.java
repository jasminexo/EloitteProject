package com.example.eloitteproject;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

@Entity
public class PersonalityQuizQuestion {
    @SerializedName("qID")
    @Expose
    @PrimaryKey
    @NonNull
    public String qID;
    @SerializedName("Question")
    @Expose
    private String question;
    @SerializedName("Image")
    @Expose
    private Integer image;
    @SerializedName("Option1")
    @Expose
    private String option1;
    @SerializedName("Option2")
    @Expose
    private String option2;
    @SerializedName("Option3")
    @Expose
    private String option3;

    public PersonalityQuizQuestion(String qID, String question, Integer image, String option1, String option2, String option3) {
        this.qID = qID;
        this.question = question;
        this.image = image;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
    }

    public static ArrayList<PersonalityQuizQuestion> getPersonalityQuizQuestionList(){
        ArrayList <PersonalityQuizQuestion> PersonalityQuizQuestionList = new ArrayList<>();
        PersonalityQuizQuestionList.add(new PersonalityQuizQuestion("q1", "\nMemory and Learning", R.drawable.quiz_memory,"Quick to learn, quick to forget", "Good general memory", "Learns slower but good long-term memory"));
        PersonalityQuizQuestionList.add(new PersonalityQuizQuestion("q2", "\nSleep Patterns", R.drawable.quiz_sleep,"Light sleep, easily interrupted", "Sound sleep, medium length", "Sound, long, typically heavy sleep"));
        PersonalityQuizQuestionList.add(new PersonalityQuizQuestion("q3", "\nMental Quality", R.drawable.quiz_mental_health,"Quick mind, imaginative, restless at times", "Sharp intellect, efficient, a perfectionist at times", "A calm, steady, stable mind"));
        PersonalityQuizQuestionList.add(new PersonalityQuizQuestion("q4", "\nEating Habits", R.drawable.quiz_eating,"Irregular hunger", "Sharp, acute hunger may arise", "Can easily skip meals without getting hungry"));
        PersonalityQuizQuestionList.add(new PersonalityQuizQuestion("q5", "\nMood Stability", R.drawable.quiz_moods,"Moods can change quickly", "Intense moods that change slowly", "Steady moods, change rarely"));
        PersonalityQuizQuestionList.add(new PersonalityQuizQuestion("q6", "\nSpeaking Style", R.drawable.quiz_speaking_style,"Speaks quickly, may use hand gestures", "Deliberate, strong-willed speaker", "Speaks calmly, pleasingly"));
        PersonalityQuizQuestionList.add(new PersonalityQuizQuestion("q7", "\nAttitude to Weather", R.drawable.quiz_weather,"Dislikes cold weather", "Dislikes hot weather", "Dislikes damp and cool weather"));
        PersonalityQuizQuestionList.add(new PersonalityQuizQuestion("q8", "\nGeneral Body Characteristics", R.drawable.quiz_body,"Thin, light, quick movements", "Strong, well built, perhaps muscular", "Heavy build, may gain weight easily"));
        PersonalityQuizQuestionList.add(new PersonalityQuizQuestion("q9", "\nPhysical Movement Traits", R.drawable.quiz_physical_movement,"Walks quickly and may like to take sprints", "Determined movements, brisk walking style", "Slow and ready, not much variation"));

        return PersonalityQuizQuestionList;
    }

    @NonNull
    public String getqID() {
        return qID;
    }

    public void setqID(@NonNull String qID) {
        this.qID = qID;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }
}

