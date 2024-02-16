package model;

import java.util.List;
import java.util.ArrayList;

public class WatchList {
    private List<Show> watchList;
    private Show show;

    public WatchList() {
        this.watchList = new ArrayList<>();

    }

    //Effects: takes a show and adds it to watchList
    public void addShow(Show show) {
        watchList.add(show);
    }
}
