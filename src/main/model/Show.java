package model;

import java.util.ArrayList;
import java.util.List;

// Represents a Show having a name and episode count and rating
public class Show {

    private String name;
    private int rating;
    private ArrayList<String> listOfGenres;
    private String director;
    private int episodes;
    private ArrayList<String> favCharacters;

    //REQUIRES: name has string length greater than 0
    //            numEpisodes is >= 1
    //EFFECTS: constructs a show with name name,
    //         numEpisodes episodes
    //         rating of 1
    //         empty listOfGenres
    //         a director with name Director
    //         and empty favCharacters

    public Show(String name, int numEpisodes) {
        this.name = name;
        this.episodes = numEpisodes;
        this.rating = 1;
        this.listOfGenres = new ArrayList<>();
        this.favCharacters = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getRating() {
        return rating;
    }

    public ArrayList<String> getListOfGenres() {
        return listOfGenres;
    }

    public int getEpisodes() {
        return episodes;
    }

    public List<String> getFavCharacters() {
        return favCharacters;
    }

    //MODIFIES: this
    //EFFECTS: sets the rating value to rating
    public void setRating(int rating) {
        this.rating = rating;
    }

    //MODIFIES: this
    //EFFECTS: adds a genre to listOfGenres
    public void addGenre(String genre) {
        listOfGenres.add(genre);
    }

    //MODIFIES: this
    //EFFECTS: adds a character to favCharacters
    public void addFavCharacter(String favCharacter) {
        favCharacters.add(favCharacter);
    }

    //EFFECTS: if episodes <= 25, classifies show as Short
    //         if 25<episodes<=65, classifies show as Medium
    //         if episodes >65, classifies show as Long
    public String classifyLength() {
        if (episodes <= 25) {
            return "Short";
        } else if (episodes > 25 && episodes <= 65) {
            return "Medium";
        } else {
            return "Long";
        }
    }


}
