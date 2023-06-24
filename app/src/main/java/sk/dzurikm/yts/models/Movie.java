package sk.dzurikm.yts.models;

import androidx.annotation.DrawableRes;

public class Movie {
    int image;
    String title, year, imageUrl;

    public Movie(@DrawableRes int image, String title, String year) {
        this.image = image;
        this.title = title;
        this.year = year;
    }

    public Movie(String imageUrl, String title, String year) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.year = year;
    }

    public Movie() {}

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
