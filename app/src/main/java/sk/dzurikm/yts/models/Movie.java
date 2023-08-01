package sk.dzurikm.yts.models;

import androidx.annotation.DrawableRes;

import java.util.ArrayList;

public class Movie {
    private Long id;
    private int coverImage,year;
    private String title, coverImageUrl, slug, description, language;
    private double rating;
    private ArrayList<String> genres;
    private ArrayList<Torrent> torrents;

    public Movie(Long id, int year, String title, String coverImageUrl, String slug, String description, String language, double rating) {
        this.id = id;
        this.year = year;
        this.title = title;
        this.coverImageUrl = coverImageUrl;
        this.slug = slug;
        this.description = description;
        this.language = language;
        this.rating = rating;
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

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", coverImage=" + coverImage +
                ", year=" + year +
                ", title='" + title + '\'' +
                ", coverImageUrl='" + coverImageUrl + '\'' +
                ", slug='" + slug + '\'' +
                ", description='" + description + '\'' +
                ", language='" + language + '\'' +
                ", rating=" + rating +
                ", genres=" + genres +
                ", torrents=" + torrents +
                '}';
    }
}
