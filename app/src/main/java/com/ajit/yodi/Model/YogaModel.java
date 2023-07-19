package com.ajit.yodi.Model;

public class YogaModel {

    private String img_url;
    private String name;
    private String reps;

    public YogaModel(){

    }

    public YogaModel(String img_url, String name, String reps) {
        this.img_url = img_url;
        this.name = name;
        this.reps = reps;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReps() {
        return reps;
    }

    public void setReps(String reps) {
        this.reps = reps;
    }


}
