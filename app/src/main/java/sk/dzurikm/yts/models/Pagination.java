package sk.dzurikm.yts.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Pagination {
    // "movie_count":1000,"limit":20,"page_number":1

    public int resultsCount,perPage,pageNumber;

    public Pagination(int resultsCount, int perPage, int pageNumber) {
        this.resultsCount = resultsCount;
        this.perPage = perPage;
        this.pageNumber = pageNumber;
    }

    public static Pagination create(JSONObject response){
        Pagination pagination;
        try {
            String status = response.getString("status");
            if (!response.getString("status").equals("ok")){
                return null;
            }

            JSONObject data = response.getJSONObject("data");
            int movieCount = data.getInt("movie_count");
            int perPage = data.getInt("limit");
            int pageNumber = data.getInt("page_number");

            pagination = new Pagination(movieCount,perPage,pageNumber);

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return pagination;
    }

    @Override
    public String toString() {
        return "Pagination{" +
                "resultsCount=" + resultsCount +
                ", perPage=" + perPage +
                ", pageNumber=" + pageNumber +
                '}';
    }
}
