package sk.dzurikm.yts.models;

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

        if (quality != null) count++;
        if (genre != null) count++;
        if (rating != null) count++;
        if (orderBy != null) count++;

        return count;
    }
}
