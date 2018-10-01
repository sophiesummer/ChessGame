package Test;

import game.Player;
import org.junit.jupiter.api.Test;
import pieces.Bishop;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BishopTest {

    private Bishop testPiece;

    public BishopTest() {
        Player testPlayer = new Player(0, "Sophie");
        testPiece = new Bishop(3, 3, testPlayer);
    }

    /**
     * test whether the bishop follows its own rule
     */
    @Test
    void isValidMove() {

        assertTrue(testPiece.isValidMove(6, 0)); // upRight
        assertTrue(testPiece.isValidMove(0, 0)); // upLeft;
        assertTrue(testPiece.isValidMove(1, 5)); // downRight
        assertTrue(testPiece.isValidMove(7, 7)); // downLeft;

        assertFalse(testPiece.isValidMove(3, 7)); // move Down
        assertFalse(testPiece.isValidMove(6, 2)); // invalid move
        assertFalse(testPiece.isValidMove(-1,7)); // invalid position
    }

    /**
     * test whether the path is stored correctly
     */
    @Test
    void moving() {
        List<int[]> getPath = testPiece.moving(6, 0);
        List<int[]> correctPath = new ArrayList<>();
        correctPath.add(new int[]{4, 2});
        correctPath.add(new int[]{5, 1});

        assertEquals(getPath.size(), correctPath.size());
        for (int i = 0; i < getPath.size(); i++) {
            assertArrayEquals(getPath.get(i), correctPath.get(i));
        }

        getPath = testPiece.moving(-9, -9);
        assertEquals(null, getPath);// invalid move test
    }

}