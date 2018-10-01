package Test;

import game.Game;
import game.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pieces.Type;
import pieces.Wizard;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WizardTest {

    private Wizard testPiece;

    public WizardTest() {
        Player testPlayer = new Player(0, "Sophie");
        testPiece = new Wizard(3, 3, testPlayer);
    }

    @Test
    void isValidMove() {
        assertTrue(testPiece.isValidMove(4, 1)); // upRight
        assertTrue(testPiece.isValidMove(2, 1)); // upLeft;
        assertTrue(testPiece.isValidMove(4, 5)); // downRight
        assertTrue(testPiece.isValidMove(2, 5)); // downLeft;
        assertTrue(testPiece.isValidMove(6, 0)); // upRight
        assertTrue(testPiece.isValidMove(0, 0)); // upLeft;
        assertTrue(testPiece.isValidMove(1, 5)); // downRight
        assertTrue(testPiece.isValidMove(7, 7)); // downLeft;

        assertFalse(testPiece.isValidMove(3, 7)); // move Down
        assertFalse(testPiece.isValidMove(6, 2)); // invalid move
        assertFalse(testPiece.isValidMove(-1,7)); // invalid position
    }

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
        BishopTest t = new BishopTest();
        t.moving();
    }

}