package sk.dzurikm.yts.constants;

public class Filters {
    public static final String ALL = "All";
    public static final String LATEST = "";
    public static String[] quality = {
            ALL,
            "480p",
            "720p",
            "1080p",
            "2160p",
            "3D"
    };
    public static String[] genres = {
            ALL,
            "Action",
            "Adventure",
            "Animation",
            "Biography",
            "Comedy",
            "Crime",
            "Documentary",
            "Drama",
            "Family",
            "Fantasy",
            "Film-Noir",
            "History",
            "Horror",
            "Music",
            "Musical",
            "Mystery",
            "Romance",
            "Sci-Fi",
            "Sport",
            "Thriller",
            "War",
            "Western"
    };

    public static String[] filterableFieldsNames = {
            "Date Added",
            "Title",
            "Year",
            "Rating",
            "Peers",
            "Seeds",
            "Download Count",
            "Like Count"
    };

    public static String[] filterableFieldsValues = {
            "title",
            "year",
            "rating",
            "peers",
            "seeds",
            "download_count",
            "like_count",
            "date_added"
    };

    public static String[] order = {
        "DESC",
        "ASC"
    };

    public static String[] rating = {
            ALL,
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
    };
}
