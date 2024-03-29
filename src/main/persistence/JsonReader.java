package persistence;

import model.WatchList;
import model.Show;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Code Reference: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads watchlist from file and returns it;
    // throw IOException if an error occurs reading data from file
    public WatchList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseWatchList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    //EFFECTS: parses watchlist from JSON object and returns it
    private WatchList parseWatchList(JSONObject jsonObject) {
        WatchList wl = new WatchList();
        addShows(wl, jsonObject);
        return wl;
    }

    // MODIFIES: wl
    // EFFECTS: parses shows from JSON object and adds them to watchlist
    private void addShows(WatchList wl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("shows");
        for (Object json : jsonArray) {
            JSONObject nextShow = (JSONObject) json;
            addShow(wl, nextShow);
        }
    }

    // MODIFIES: wl
    // EFFECTS: parses show from JSON object and adds it to watchlist
    private void addShow(WatchList wl, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int episodes = jsonObject.getInt("episodes");
        int rating = jsonObject.getInt("rating");
        JSONArray genres = jsonObject.getJSONArray("genres");
        JSONArray favCharacters = jsonObject.getJSONArray("favCharacters");
        Show show = new Show(name, episodes);
        show.setRating(rating);

        for (Object json : genres) {
            String genre = (String) json;
            show.addGenre(genre);
        }

        for (Object json: favCharacters) {
            String favCharacter = (String) json;
            show.addFavCharacter(favCharacter);
        }

        wl.addShow(show);
    }


}
