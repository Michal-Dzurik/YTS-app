package sk.dzurikm.yts.constants;

public class ApiMap {
    public static final String BASE_URL = "https://yts.mx/api/v2";

    // MOVIES
    public static final String MOVIES_LIST = "list_movies";
    public static final String MOVIE_DETAILS = "movie_details";
    public static final String MOVIE_SUGGESTIONS = "movie_suggestions";
    public static final String MOVIE_COMMENTS = "movie_comments";
    public static final String MOVIE_REVIEWS = "movie_reviews";
    public static final String MOVIE_PARENTAL_GUIDE = "movie_parental_guides";

    // MOVIE BOOKMARKS
    public static final String MOVIE_LIST_BOOKMARKS = "get_movie_bookmarks";
    public static final String MOVIE_ADD_BOOKMARK = "add_movie_bookmark";
    public static final String MOVIE_DELETE_BOOKMARK = "delete_movie_bookmark";

    // MOVIE ACTIONS
    public static final String LIKE_MOVIE = "like_movie";
    public static final String REQUEST_MOVIE = "make_request";

    // USER
    // If I want to make profile changes I need application KEY
    /* To make any POST requests to our API you must use your developer's 'application_key', if you do not have one please apply HERE (https://yts.mx/contact) */
    public static final String USER_LOGIN = "user_get_key";
    public static final String USER_REGISTER = "user_register";
    public static final String USER_DETAILS = "user_details";
    public static final String USER_PROFILE = "user_profile";
    public static final String USER_PROFILE_CHANGE = "user_edit_settings";
    public static final String USER_FORGOT_PASSWORD = "user_forgot_password";
    public static final String USER_RESET_PASSWORD = "user_reset_password";


    // COMMENTS
    public static final String LEAVE_COMMENT = "make_comment";
    public static final String LIKE_COMMENT = "like_comment";
    public static final String REPORT_COMMENT = "report_comment";
    public static final String DELETE_COMMENT = "delete_comment";

}
