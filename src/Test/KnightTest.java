package Test;

import game.Game;
import game.Player;
import org.junit.jupiter.api.Test;
import pieces.Knight;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class KnightTest {
    private Game game;
    private Knight testPiece;

    public KnightTest() {
        Player testPlayer = new Player(0, "Sophie");
        testPiece = new Knight(3, 3, testPlayer);
    }

    @Test
    void isValidMove() {
        assertTrue(testPiece.isValidMove(4, 1)); // upRight
        assertTrue(testPiece.isValidMove(2, 1)); // upLeft;
        assertTrue(testPiece.isValidMove(4, 5)); // downRight
        assertTrue(testPiece.isValidMove(2, 5)); // downLeft;

        assertFalse(testPiece.isValidMove(3, 7)); // move Down
        assertFalse(testPiece.isValidMove(6, 2)); // invalid move
        assertFalse(testPiece.isValidMove(-1,7)); // invalid position
    }

    @Test
    void moving() {
        List<int[]> getPath = testPiece.moving(6, 0);
        assertEquals(new ArrayList<>(), getPath);// invalid move test
    }
}