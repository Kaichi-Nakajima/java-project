package model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class ShowTest {
    private Show testShow1;
    private Show testShow2;
    private Show testShow3;
    private Show testShow4;

    @BeforeEach
    public void runBefore() {
        testShow1 = new Show("Breaking Bad", 62);
        testShow2 = new Show("Squid Game", 9);
        testShow3 = new Show("One Piece", 1093);
        testShow4 = new Show("Game of Thrones", 73);


    }

    @Test
    public void testConstructor() {
        assertEquals("Breaking Bad", testShow1.getName());
        assertEquals(1, testShow1.getRating());
        assertEquals(62, testShow1.getEpisodes());
        assertTrue(testShow1.getListOfGenres().isEmpty());
        assertTrue(testShow1.getFavCharacters().isEmpty());

    }

    @Test
    public void testSetRating() {
        testShow1.setRating(5);

        assertEquals(5, testShow1.getRating());

    }

    @Test
    public void testAddGenre() {
        testShow1.addGenre("Drama");

        assertEquals("Drama", testShow1.getListOfGenres().get(0));

        testShow1.addGenre("Thriller");

        assertEquals("Thriller", testShow1.getListOfGenres().get(1));

    }

    @Test
    public void testAddFavCharacter() {
        testShow1.addFavCharacter("Walter White");

        assertEquals("Walter White", testShow1.getFavCharacters().get(0));

        testShow1.addFavCharacter("Jesse Pinkman");

        assertEquals("Jesse Pinkman", testShow1.getFavCharacters().get(1));

    }

    @Test
    public void testClassifyLength() {
        assertEquals("Short", testShow2.classifyLength());
        assertEquals("Medium", testShow1.classifyLength());
        assertEquals("Long", testShow3.classifyLength());

    }

}