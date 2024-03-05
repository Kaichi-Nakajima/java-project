package model;

import java.util.List;
import java.util.ArrayList;

//Represents of List of Shows
public class WatchList {
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
    }

    //REQUIRES: watchlist is not empty
    //EFFECTS: returns the show at index n in watchlist
    public Show getShow(int n) {
        return watchList.get(n);
    }

    //REQUIRES: watchlist is not empty
    //EFFECTS: returns the name of the show if it is contained in the watchlist
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
        return showNameList;
    }

    //REQUIRES: watchlist is not empty
    //MODIFIES: this
    //EFFECTS: removes show from watchlist
    public void removeShow(Show show) {
        watchList.remove(show);
    }

    //EFFECTS: returns true is show is contained in watchList and returns its name
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
}
