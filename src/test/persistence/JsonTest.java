package persistence;

import model.Show;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkShow(String name,
                             int episodes,
                             int rating,
                             List<String> genres,
                             List<String> favCharacters,
                             Show show ) {
        assertEquals(name, show.getName());
        assertEquals(episodes, show.getEpisodes());
        assertEquals(rating, show.getRating());
        assertEquals(genres, show.getListOfGenres());
        assertEquals(favCharacters, show.getFavCharacters());
    }
}
