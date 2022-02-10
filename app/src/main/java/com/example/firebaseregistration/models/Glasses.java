package com.example.firebaseregistration.models;

public class Glasses {
    String name;
    String img_url;
    String description;

    public Glasses(String name, String img_url, String description) {
        this.name = name;
        this.img_url = img_url;
        this.description = description;
    }

    public Glasses(){ }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
