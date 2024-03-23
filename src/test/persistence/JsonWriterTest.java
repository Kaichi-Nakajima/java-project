package persistence;

import model.Show;
import model.WatchList;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;

// Code Reference: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest{

    @Test
    void testWriterInvalidFile() {
        try {
            WatchList wl = new WatchList();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:filename.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWatchList() {
        try {
            WatchList wl = new WatchList();
            JsonWriter writer = new JsonWriter("./data/testEmptyWatchList.json");
            writer.open();
            writer.write(wl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testEmptyWatchList.json");
            wl = reader.read();
            assertEquals(0, wl.numOfShows());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWatchList() {
        Show show1 = new Show("Pokemon", 1000);
        Show show2 = new Show("Breaking Bad", 63);

        show1.setRating(7);
        show1.addGenre("action");
        show1.addGenre("adventure");
        show1.addFavCharacter("Pikachu");
        show1.addFavCharacter("Ash Ketchum");

        show2.setRating(10);
        show2.addGenre("crime");
        show2.addGenre("suspense");
        show2.addFavCharacter("Walter White");
        show2.addFavCharacter("Saul Goodman");

        List<String> genres1 = new ArrayList<>();
        genres1.add("action");
        genres1.add("adventure");

        List<String> favCharacters1 = new ArrayList<>();
        favCharacters1.add("Pikachu");
        favCharacters1.add("Ash Ketchum");

        List<String> genres2 = new ArrayList<>();
        genres2.add("crime");
        genres2.add("suspense");

        List<String> favCharacters2 = new ArrayList<>();
        favCharacters2.add("Walter White");
        favCharacters2.add("Saul Goodman");

        try {
            WatchList wl = new WatchList();
            wl.addShow(show1);
            wl.addShow(show2);
            JsonWriter writer = new JsonWriter("./data/testGeneralWatchList.json");
            writer.open();
            writer.write(wl);
            writer.close();


            JsonReader reader = new JsonReader("./data/testGeneralWatchList.json");
            wl = reader.read();
            List<Show> shows = wl.getShows();
            assertEquals(2, shows.size());
            checkShow("Pokemon", 1000, 7, genres1, favCharacters1, shows.get(0));
            checkShow("Breaking Bad", 63, 10, genres2, favCharacters2, shows.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}
