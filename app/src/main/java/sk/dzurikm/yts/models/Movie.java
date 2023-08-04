package sk.dzurikm.yts.models;

import androidx.annotation.DrawableRes;

import java.util.ArrayList;

public class Movie {
    private Long id;
    private int coverImage,year,duration;
    private String title, coverImageUrl, slug, description, language, url,ytTrailerCode;
    private double rating;
    private ArrayList<String> genres;
    private ArrayList<Torrent> torrents;

    public Movie(Long id, int year, String title, String coverImageUrl, String slug, String description, String language, double rating, int duration, String url, String ytTrailerCode) {
        this.id = id;
        this.year = year;
        this.title = title;
        this.coverImageUrl = coverImageUrl;
        this.slug = slug;
        this.description = description;
        this.language = language;
        this.rating = rating;
        this.duration = duration;
        this.url = url;
        this.ytTrailerCode = ytTrailerCode;
    }

    public Movie(@DrawableRes int image, String title, int year) {
        this.coverImage = image;
        this.title = title;
        this.year = year;
    }

    public Movie(String imageUrl, String title, int year) {
        this.coverImageUrl = imageUrl;
        this.title = title;
        this.year = year;
    }

    public ArrayList<Torrent> getTorrents() {
        return torrents;
    }

    public void setTorrents(ArrayList<Torrent> torrents) {
        this.torrents = torrents;
    }

    public Movie() {}

    public int getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(int coverImage) {
        this.coverImage = coverImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public String getGenresInString(){
        String output = "";
        for (int i = 0; i < genres.size(); i++) {
            if (i == 0) output += genres.get(i);
            else output += ", " + genres.get(i);
        }

        return output;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
    }

    public int getDuration() {
        return duration;
    }

    public String getFormattedDuration() {
        return ((int) duration / 60) + "h " + (duration % 60 != 0 ? duration % 60 + "min" : "");
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTrailerUrl() {
        return "https://www.youtube.com/watch?v=" + ytTrailerCode;
    }

    public void setYtTrailerCode(String ytTrailerCode) {
        this.ytTrailerCode = ytTrailerCode;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", coverImage=" + coverImage +
                ", year=" + year +
                ", duration=" + duration +
                ", title='" + title + '\'' +
                ", coverImageUrl='" + coverImageUrl + '\'' +
                ", slug='" + slug + '\'' +
                ", description='" + description + '\'' +
                ", language='" + language + '\'' +
                ", url='" + url + '\'' +
                ", trailerUrl='" + getTrailerUrl() + '\'' +
                ", rating=" + rating +
                ", genres=" + genres +
                ", torrents=" + torrents +
                '}';
    }
}
