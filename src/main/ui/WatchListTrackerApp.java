package ui;

import java.util.Scanner;
import model.WatchList;
import model.Show;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;

//WatchList Tracker Application
//Code Reference: https://github.students.cs.ubc.ca/CPSC210/TellerApp.git

public class WatchListTrackerApp {
    private static final String JSON_STORE = "./data/watchlist.json";
    private WatchList watchList;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //EFFECTS: runs the WatchList Tracker App
    public WatchListTrackerApp() throws FileNotFoundException {
        input = new Scanner(System.in);
        watchList = new WatchList();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
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
        } else if (command.equals("c")) {
            doSeeDetails();
        } else if (command.equals("r")) {
            doRemoveShow();
        } else if (command.equals("v")) {
            doViewList();
        } else if (command.equals("n")) {
            doNumShows();
        } else if (command.equals("s")) {
            saveWatchList();
        } else if (command.equals("l")) {
            loadWatchList();
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
        System.out.println("\tc -> check details");
        System.out.println("\tr -> remove show");
        System.out.println("\tv -> view list");
        System.out.println("\tn -> number of shows");
        System.out.println("\ts -> save watchlist to file");
        System.out.println("\tl -> load watchlist to file");
        System.out.println("\tq -> quit");

    }

    //MODIFIES: this
    //EFFECTS: adds a show to watchlist
    private void doAddShow() {
        Show show;
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

    //REQUIRES: watch list is not empty
    //MODIFIES: this
    // EFFECTS: adds details to a show
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void doAddDetails() {
        Show selected = selectShow();
        String detail = "";

        if (selected == null) {
            System.out.println("there is nothing in the watchlist");
        } else {
            while (!(detail.equals("r") || detail.equals("g") || detail.equals("c"))) {
                System.out.println("r for rating");
                System.out.println("g for genre");
                System.out.println("c for character");
                detail = input.next();
                detail = detail.toLowerCase();
                if (detail.equals("r")) {
                    System.out.println("enter rating");
                    int rating = input.nextInt();
                    if (rating >= 1 && rating <= 10) {
                        selected.setRating(rating);
                    } else {
                        System.out.println("Rating must be from 1 to 10");
                    }
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
        }

    }

    //EFFECTS: prints out the details of a show
    private void doSeeDetails() {
        Show chosen = selectShow();
        String details = "";

        if (chosen == null) {
            System.out.println("show is not in the watchlist");
        } else {
            while (!(details.equals("r") || details.equals("g") || details.equals("c"))) {
                System.out.println("r for rating");
                System.out.println("g for genre");
                System.out.println("c for character");
                details = input.next();
                details = details.toLowerCase();

                if (details.equals("r")) {
                    System.out.println(chosen.getRating());
                } else if (details.equals("g")) {
                    System.out.println(chosen.getListOfGenres());
                } else {
                    System.out.println(chosen.getFavCharacters());
                }
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: removes show from watch list
    private void doRemoveShow() {
        Show removeSelect = selectShow();
        if (removeSelect == null) {
            System.out.println("there is no show to remove in watchlist");
        } else {
            watchList.removeShow(removeSelect);
            System.out.println("show has been removed");
        }
    }

    //EFFECTS: prints out the shows in the watch list
    private void doViewList() {
        if (watchList.isEmpty()) {
            System.out.println("looks like the watchlist is empty!");
        } else {
            System.out.println("Watch List:" + watchList.getShowNamesList());
        }
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

        if (watchList.isEmpty()) {
            System.out.println("there is nothing in the watchlist");
            System.out.println("add a show to the watchlist");
            return null;
        }

        while (selection.equals("")) {
            System.out.println("type name of the show");
            selection = input.next();
        }


        return watchList.getShowName(selection);
    }

    // EFFECTS: saves the watchlist to file
    private void saveWatchList() {
        try {
            jsonWriter.open();
            jsonWriter.write(watchList);
            jsonWriter.close();
            System.out.println("Saved WatchList to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads watchlist from file
    private void loadWatchList() {
        try {
            watchList = jsonReader.read();
            System.out.println("Loaded WatchList from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to reader from file: " + JSON_STORE);
        }
    }

}
