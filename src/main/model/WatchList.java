package model;

import java.util.List;
import java.util.ArrayList;

//Represents of List of Shows
public class WatchList {
    private List<Show> watchList;
    private Show show;

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

    //EFFECTS: returns the current state of the watchlist
    public List<Show> getWatchList() {
        return watchList;
    }

    //REQUIRES: watchlist is not empty
    //MODIFIES: this
    //EFFECTS: removes show from watchlist
    public void removeShow(Show show) {
        watchList.remove(show);
    }

    //EFFECTS: returns the number of shows in watchlist
    public int numOfShow() {
        return watchList.size();
    }
}
