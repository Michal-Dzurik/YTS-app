package sk.dzurikm.yts.models;

import sk.dzurikm.yts.constants.Filters;
import sk.dzurikm.yts.constants.RequestParameters;

public class FilterBundle {
    public String quality,genre,rating,orderBy;

    public FilterBundle(String quality, String genre, String rating, String orderBy) {
        this.quality = quality;
        this.genre = genre;
        this.rating = rating;
        this.orderBy = orderBy;
    }

    public FilterBundle() {
    }

    @Override
    public String toString() {
        return "FilterBundle{" +
                "quality='" + quality + '\'' +
                ", genre='" + genre + '\'' +
                ", rating='" + rating + '\'' +
                ", orderBy='" + orderBy + '\'' +
                '}';
    }

    public int getCountOfUsedFilters(){
        int count = 0;

        if (quality != null && !quality.equals(Filters.quality[0])) count++;
        if (genre != null && !genre.equals(Filters.genres[0])) count++;
        if (rating != null && !rating.equals(Filters.rating[0])) count++;
        if (orderBy != null && !orderBy.equals(Filters.filterableFieldsValues[0])) count++;

        return count;
    }

    public void applyFilters(RequestParameters parameters){
        if (quality != null && !quality.equals(Filters.quality[0])) parameters.add("quality",quality);
        if (genre != null && !genre.equals(Filters.genres[0])) parameters.add("genre",genre);
        if (rating != null && !rating.equals(Filters.rating[0])) parameters.add("minimum_rating",rating);
        if (orderBy != null && !orderBy.equals(Filters.filterableFieldsValues[0])) parameters.add("sort_by",orderBy);
    }

    public void clearFilters(){
        quality = null;
        genre = null;
        rating = null;
        orderBy = null;
    }
}
