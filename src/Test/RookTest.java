package Test;

import game.Game;
import game.Player;
import org.junit.jupiter.api.Test;
import pieces.Rook;
import pieces.Type;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RookTest {

    private Rook testPiece;

    public RookTest() {
        Player testPlayer = new Player(0, "Sophie");
        testPiece = new Rook(3, 3, testPlayer);
    }

    @Test
    void isValidMove() {
        assertTrue(testPiece.isValidMove(1, 3)); // Left
        assertTrue(testPiece.isValidMove(6, 3)); // Right;
        assertTrue(testPiece.isValidMove(3, 1)); // Up
        assertTrue(testPiece.isValidMove(3, 6)); // Down;

        assertFalse(testPiece.isValidMove(6, 6)); // move diagonally
        assertFalse(testPiece.isValidMove(6, 2)); // invalid move
        assertFalse(testPiece.isValidMove(-1,7)); // invalid position
    }

    @Test
    void moving() {
        List<int[]> getPath = testPiece.moving(7, 3);
        List<int[]> correctPath = new ArrayList<>();
        correctPath.add(new int[]{4, 3});
        correctPath.add(new int[]{5, 3});
        correctPath.add(new int[]{6, 3});

        assertEquals(getPath.size(), correctPath.size());
        for (int i = 0; i < getPath.size(); i++) {
            assertArrayEquals(getPath.get(i), correctPath.get(i));
        }

        getPath = testPiece.moving(-9, -9);
        assertEquals(null, getPath);// invalid move test
    }

    @Test
    void initialization() {
        testPiece.setX(0);
        testPiece.setY(0);
        assertArrayEquals(testPiece.getPosition(), new int[]{0, 0});
        testPiece.move(0, 7);
        assertArrayEquals(testPiece.getPosition(), new int[]{0, 7});

    }
}