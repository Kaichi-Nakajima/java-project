package model;

import java.util.List;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class WatchListTest {
    private WatchList testWatchList;
    private Show testingShow1;
    private Show testingShow2;
    private Show testingShow3;
    private Show testingShow4;
    private Show testingShow5;
    private Show testingShow6;
    private List<String> showList;

    @BeforeEach
    public void runBefore() {
        testWatchList = new WatchList();
        testingShow1 = new Show("Jujutsu Kaisen", 48);
        testingShow2 = new Show("Pokemon", 1269);
        testingShow3 = new Show("Black Mirror", 27);
        testingShow4 = new Show("Test Show 4", 8);
        testingShow5 = new Show("Test Show 5", 66);
        testingShow6 = new Show("Test Show 6", 25);
        showList = new ArrayList<>();

    }

    @Test
    public void getShowTest() {
        testWatchList.addShow(testingShow1);

        assertEquals(testingShow1, testWatchList.getShow(0));

    }

    @Test
    public void getShowsTest() {
        testWatchList.addShow(testingShow3);
        testWatchList.addShow(testingShow1);

        WatchList wl = new WatchList();
        wl.addShow(testingShow3);
        wl.addShow(testingShow1);

        assertEquals(wl.getShows(), testWatchList.getShows());
    }

    @Test
    public void getShowNameTest() {
        assertEquals(null, testWatchList.getShowName("One Piece"));

        testWatchList.addShow(testingShow1);
        testWatchList.addShow(testingShow2);
        testWatchList.addShow(testingShow3);

        assertEquals(testingShow1, testWatchList.getShowName("Jujutsu Kaisen"));
        assertEquals(testingShow2, testWatchList.getShowName("Pokemon"));
        assertEquals(testingShow3, testWatchList.getShowName("Black Mirror"));
        assertEquals(null, testWatchList.getShowName("One Piece"));

    }

    @Test
    public void getWatchListTest() {
        assertEquals(showList, testWatchList.getShowNamesList());

        testWatchList.addShow(testingShow1);
        testWatchList.addShow(testingShow2);
        testWatchList.addShow(testingShow3);

        showList.add("Jujutsu Kaisen");
        showList.add("Pokemon");
        showList.add("Black Mirror");

        assertEquals(showList, testWatchList.getShowNamesList());
        assertFalse(testWatchList.getShowNamesList().contains(testingShow4));

    }

    @Test
    public void isContainedTest() {
        testWatchList.addShow(testingShow1);
        testWatchList.addShow(testingShow2);
        testWatchList.addShow(testingShow3);

        assertTrue(testWatchList.isContained(testingShow1));
        assertTrue(testWatchList.isContained(testingShow2));
        assertTrue(testWatchList.isContained(testingShow3));
        assertFalse(testWatchList.isContained(testingShow4));
    }

    @Test
    public void isNameContainedTest() {
        testWatchList.addShow(testingShow1);
        testWatchList.addShow(testingShow2);
        testWatchList.addShow(testingShow3);

        assertTrue(testWatchList.isNameContained("Jujutsu Kaisen"));
        assertTrue(testWatchList.isNameContained("Pokemon"));
        assertTrue(testWatchList.isNameContained("Black Mirror"));
        assertFalse(testWatchList.isNameContained("One Piece"));

    }

    @Test
    public void removeShowTest() {
        testWatchList.addShow(testingShow1);
        testWatchList.addShow(testingShow2);
        testWatchList.addShow(testingShow3);
        testWatchList.removeShow(testingShow2);

        assertFalse(testWatchList.isContained(testingShow2));
        assertTrue(testWatchList.isContained(testingShow1));
        assertTrue(testWatchList.isContained(testingShow3));

    }

    @Test
    public void numOfShowsTest() {
        assertEquals(0, testWatchList.numOfShows());
        testWatchList.addShow(testingShow1);
        testWatchList.addShow(testingShow2);
        testWatchList.addShow(testingShow3);

        assertEquals(3, testWatchList.numOfShows());

    }

    @Test
    public void numOfShortShowsTest() {
        assertEquals(0, testWatchList.numOfShortShows());
        testWatchList.addShow(testingShow1);
        testWatchList.addShow(testingShow2);
        testWatchList.addShow(testingShow3);
        testWatchList.addShow(testingShow4);
        testWatchList.addShow(testingShow5);
        testWatchList.addShow(testingShow6);

        assertEquals(2, testWatchList.numOfShortShows());

    }

    @Test
    public void numOfMediumShowsTest() {
        assertEquals(0,testWatchList.numOfMediumShows());
        testWatchList.addShow(testingShow1);
        testWatchList.addShow(testingShow2);
        testWatchList.addShow(testingShow3);
        testWatchList.addShow(testingShow4);
        testWatchList.addShow(testingShow5);
        testWatchList.addShow(testingShow6);

        assertEquals(2, testWatchList.numOfMediumShows());

    }

    @Test
    public void numOfLongShowsTest() {
        assertEquals(0, testWatchList.numOfLongShows());
        testWatchList.addShow(testingShow1);
        testWatchList.addShow(testingShow2);
        testWatchList.addShow(testingShow3);
        testWatchList.addShow(testingShow4);
        testWatchList.addShow(testingShow5);
        testWatchList.addShow(testingShow6);

        assertEquals(2, testWatchList.numOfLongShows());

    }

    @Test
    public void isEmptyTest() {
        assertTrue(testWatchList.isEmpty());

        testWatchList.addShow(testingShow1);

        assertFalse(testWatchList.isEmpty());

    }

}
