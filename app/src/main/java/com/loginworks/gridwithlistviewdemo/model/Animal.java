package com.loginworks.gridwithlistviewdemo.model;

/**
 * Created by Ankit Maurya on 12-May-17.
 */

public class Animal {
    private String imageName;
    private String imageUrl;

    public Animal(String imageName, String imageUrl) {
        this.imageName = imageName;
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
