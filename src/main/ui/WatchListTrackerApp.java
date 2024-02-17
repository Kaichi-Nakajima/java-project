package ui;

import java.util.List;
import java.util.Scanner;
import model.WatchList;
import model.Show;

//WatchList Tracker Application
//Code Reference: Teller Application, TellerApp class

public class WatchListTrackerApp {
    private WatchList watchList;
    private Show show;
    private Scanner input;

    //EFFECTS: runs the WatchList Tracker App
    public WatchListTrackerApp() {
        runTracker();
    }

    //MODIFIES: this
    //EFFECTS: processes user input
    public void runTracker() {
        boolean keepGoing = true;
        String command = null;

        initialize();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nGoodbye");
    }

    //MODIFIES: this
    //EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            doAddShow();
        } else if (command.equals("d")) {
            doAddDetails();
        } else if (command.equals("s")) {
            doSeeDetails();
        } else if (command.equals("r")) {
            doRemoveShow();
        } else if (command.equals("v")) {
            doViewList();
        } else if (command.equals("n")) {
            doNumShows();
        } else {
            System.out.println("selection not valid");
        }
    }

    //EFFECTS: initializes watchList
    private void initialize() {
        watchList = new WatchList();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    //EFFECTS: displays menu of options to uses
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add show");
        System.out.println("\td -> add details");
        System.out.println("\ts -> see details");
        System.out.println("\tr -> remove show");
        System.out.println("\tv -> view list");
        System.out.println("\tn -> number of shows");
        System.out.println("\tq -> quit");

    }

    //MODIFIES: this
    //EFFECTS: adds a show to watchlist
    private void doAddShow() {
        System.out.println("enter the name of the show");
        String name = input.next();
        System.out.println("enter number of episodes");
        int episodes = input.nextInt();
        show = new Show(name, episodes);

        if (!watchList.isContained(show)) {
            watchList.addShow(show);
        } else {
            System.out.println("show is already in watch list");
        }
    }

    //MODIFIES: this
    // EFFECTS: adds details to a show
    private void doAddDetails() {
        Show selected = selectShow();
        String detail = "";

        while (!(detail.equals("r") || detail.equals("g") || detail.equals("c"))) {
            System.out.println("r for rating");
            System.out.println("g for genre");
            System.out.println("c for character");
            detail = input.next();
            detail = detail.toLowerCase();
        }

        if (detail.equals("r")) {
            System.out.println("enter rating");
            int rating = input.nextInt();
            selected.setRating(rating);
        } else if (detail.equals("g")) {
            System.out.println("enter genre");
            String genre = input.next();
            selected.addGenre(genre);
        } else {
            System.out.println("enter character");
            String character = input.next();
            selected.addFavCharacter(character);
        }
    }

    private void doSeeDetails() {
        Show chosen = selectShow();
        String details = "";

        while (!(details.equals("r") || details.equals("g") || details.equals("c"))) {
            System.out.println("r for rating");
            System.out.println("g for genre");
            System.out.println("c for character");
            details = input.next();
            details = details.toLowerCase();
        }

        if (details.equals("r")) {
            System.out.println(chosen.getRating());
        } else if (details.equals("g")) {
            System.out.println(chosen.getListOfGenres());
        } else {
            System.out.println(chosen.getFavCharacters());
        }
    }

    //MODIFIES: this
    //EFFECTS: removes show from watch list
    private void doRemoveShow() {
        Show removeSelect = selectShow();
        watchList.removeShow(removeSelect);
        System.out.println("show has been removed");
    }

    //EFFECTS: prints out the shows in the watch list
    private void doViewList() {
        System.out.println("Watch List:" + watchList.getWatchList());
    }

    // EFFECTS: prints out the number of total number of shows
    //          number of short shows
    //          number of medium shows
    //           or number of long shows
    private void doNumShows() {
        String select = "";

        while (!(select.equals("t") || select.equals("s")
                || select.equals("m") || select.equals("l"))) {
            System.out.println("t for total");
            System.out.println("s for short");
            System.out.println("m for medium");
            System.out.println("l for long");
            select = input.next();
            select = select.toLowerCase();
        }

        if (select.equals("t")) {
            System.out.println(watchList.numOfShows());
        } else if (select.equals("s")) {
            System.out.println(watchList.numOfShortShows());
        } else if (select.equals("m")) {
            System.out.println(watchList.numOfMediumShows());
        } else {
            System.out.println(watchList.numOfLongShows());
        }
    }

    //EFFECTS: prompts user to select a show
    private Show selectShow() {
        String selection = "";

        while (!(selection.equals(show.getName()))) {
            System.out.println("type name of the show");
            selection = input.next();
        }

        if (selection.equals(show.getName())) {
            return show;
        } else {
            System.out.println("show is not in list");
        }
        return null;
    }

}