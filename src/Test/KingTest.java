package Test;

import game.Game;
import game.Player;
import org.junit.jupiter.api.Test;
import pieces.King;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class KingTest {

    private King testPiece;

    public KingTest() {
        Player testPlayer = new Player(0, "Sophie");
        testPiece = new King(3, 3, testPlayer);
    }

    @Test
    void isValidMove() {
        assertTrue(testPiece.isValidMove(4, 2)); // upRight
        assertTrue(testPiece.isValidMove(2, 2)); // upLeft
        assertTrue(testPiece.isValidMove(4, 4)); // downRight
        assertTrue(testPiece.isValidMove(2, 4)); // downLeft
        assertTrue(testPiece.isValidMove(3, 4)); // down
        assertTrue(testPiece.isValidMove(3, 2)); // up
        assertTrue(testPiece.isValidMove(2, 3)); // left
        assertTrue(testPiece.isValidMove(4, 3)); // right

        assertFalse(testPiece.isValidMove(6, 2)); // invalid move
        assertFalse(testPiece.isValidMove(-1,7)); // invalid position
    }

    @Test
    void moving() {

        List<int[]> getPath = testPiece.moving(4, 2);

        assertEquals(null, getPath);

        getPath = testPiece.moving(-9, -9);
        assertEquals(null, getPath);// invalid move test
    }

}