package sk.dzurikm.yts.constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import sk.dzurikm.yts.models.Movie;
import sk.dzurikm.yts.models.Torrent;

public class ApiHelper {
    public static ArrayList<Movie> getMovies(JSONObject response){
        try {
            String status = response.getString("status");
            if (!response.getString("status").equals("ok")){
                return null;
            }

            ArrayList<Movie> movieModels = new ArrayList<>();

            JSONObject data = response.getJSONObject("data");
            JSONArray movies = data.getJSONArray("movies");

            for (int i = 0; i < movies.length(); i++) {
                JSONObject movie = (JSONObject) movies.get(i);

                Movie movieModel = new Movie(
                        movie.getLong("id"),
                        movie.getInt("year"),
                        movie.getString("title"),
                        movie.getString("medium_cover_image"),
                        movie.getString("slug"),
                        movie.getString("description_full"),
                        movie.getString("language"),
                        movie.getDouble("rating")
                );

                JSONArray torrents = movie.getJSONArray("torrents");
                ArrayList<Torrent> torrentModels = new ArrayList<>();

                for (int j = 0; j < torrents.length(); j++) {
                    JSONObject torrent = (JSONObject) torrents.get(j);

                    Torrent torrentModel = new Torrent(
                            torrent.getString("url"),
                            torrent.getString("quality"),
                            torrent.getString("type"),
                            torrent.getString("size"),
                            torrent.getInt("seeds"),
                            torrent.getInt("peers")
                    );

                    torrentModels.add(torrentModel);
                }

                JSONArray genres = movie.getJSONArray("genres");
                ArrayList<String> genresModels = new ArrayList<>();

                for (int j = 0; j < genres.length(); j++) {
                    genresModels.add((String) genres.get(j));
                }

                movieModel.setGenres(genresModels);
                movieModel.setTorrents(torrentModels);
                movieModels.add(movieModel);
            }

            return movieModels;

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }
}
