package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.List;
import java.util.Collections;
import java.util.ArrayList;

// Code Reference: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

//Represents of List of Shows
public class WatchList implements Writable {
    private final List<Show> watchList;
    private int numShortShows = 0;
    private int numMediumShows = 0;
    private int numLongShows = 0;

    //EFFECTS: creates an instance of this class with an empty watchlist
    public WatchList() {
        this.watchList = new ArrayList<>();

    }

    //REQUIRES: watchlist does not already contain show with same name and episodes
    //MODIFIES: this
    //EFFECTS: takes a show and adds it to watchList
    public void addShow(Show show) {

        watchList.add(show);
        EventLog.getInstance().logEvent(new Event("Show Added: " + show.getName()));
    }

    //REQUIRES: watchlist is not empty
    //EFFECTS: returns the show at index n in watchlist
    public Show getShow(int n) {

        return watchList.get(n);
    }

    //EFFECTS: returns an unmodifiable watchlist at its current state
    public List<Show> getShows() {

        return Collections.unmodifiableList(watchList);
    }

    //REQUIRES: watchlist is not empty
    //EFFECTS: returns the show if its name is contained in the watchlist
    public Show getShowName(String nameShow) {
        for (Show show: watchList) {
            if (show.getName().equals(nameShow)) {
                return show;
            }
        }
        return null;
    }

    //EFFECTS: returns the names of shows currently in the watchlist
    public List<String> getShowNamesList() {
        List<String> showNameList = new ArrayList<>();
        for (Show show: watchList) {
            showNameList.add(show.getName());
        }
        EventLog.getInstance().logEvent(new Event("Watchlist Viewed"));
        return showNameList;
    }

    //REQUIRES: watchlist is not empty
    //MODIFIES: this
    //EFFECTS: removes show from watchlist
    public void removeShow(Show show) {
        watchList.remove(show);
        EventLog.getInstance().logEvent(new Event("Show Removed: " + show.getName()));
    }

    //EFFECTS: returns true is show is contained in watchList
    public boolean isContained(Show show) {
        return watchList.contains(show);
    }

    //EFFECTS: returns true if show name is contained in watchList
    public boolean isNameContained(String showName) {
        for (Show show: watchList) {
            if (showName.equals(show.getName())) {
                return true;
            }
        }
        return false;
    }


    //EFFECTS: returns the number of shows in watchlist
    public int numOfShows() {
        return watchList.size();
    }

    //EFFECTS: returns the number of shows classified as short
    public int numOfShortShows() {
        for (Show show: watchList) {
            if (show.classifyLength().equals("Short")) {
                numShortShows++;
            }
        }
        return numShortShows;
    }

    //EFFECTS: returns the number of shows classified as medium
    public int numOfMediumShows() {
        for (Show show: watchList) {
            if (show.classifyLength().equals("Medium")) {
                numMediumShows++;
            }
        }
        return numMediumShows;
    }

    //EFFECTS: returns the number of shows classified as long
    public int numOfLongShows() {
        for (Show show: watchList) {
            if (show.classifyLength().equals("Long")) {
                numLongShows++;
            }
        }
        return numLongShows;
    }

    public boolean isEmpty() {
        return watchList.isEmpty();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("shows", watchListToJson());
        return json;
    }

    // EFFECTS: returns things in this worklist as JSON array
    private JSONArray watchListToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Show s : watchList) {
            jsonArray.put(s.toJson());
        }

        return jsonArray;
    }
}
