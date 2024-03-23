package persistence;

import model.Show;
import model.WatchList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Code Reference: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            WatchList wl = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass exception
        }
    }

    @Test
    void testReaderEmptyWatchList() {
        JsonReader reader = new JsonReader("./data/testEmptyWatchList.json");
        try {
            WatchList wl = reader.read();
            assertEquals(0, wl.numOfShows());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWatchList() {
        List<String> genres1 = new ArrayList<>();
        genres1.add("crime");
        genres1.add("suspense");

        List<String> genres2 = new ArrayList<>();
        genres2.add("action");
        genres2.add("adventure");

        List<String> favCharacters1 = new ArrayList<>();
        favCharacters1.add("Walter White");
        favCharacters1.add("Saul Goodman");

        List<String> favCharacters2 = new ArrayList<>();
        favCharacters2.add("Pikachu");
        favCharacters2.add("Ash Ketchum");

        JsonReader reader = new JsonReader("./data/testGeneralWatchList.json");
        try {
            WatchList wl = reader.read();
            List<Show> shows = wl.getShows();
            assertEquals(2, shows.size());
            checkShow("Pokemon", 1000, 7, genres2, favCharacters2, shows.get(0));
            checkShow("Breaking Bad", 63, 10, genres1, favCharacters1, shows.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
