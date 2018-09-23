package Test;

import game.Game;
import org.junit.jupiter.api.Test;
import pieces.Queen;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QueenTest {
    private Game game;
    private Queen testPiece;

    public QueenTest() {
        game = new Game(8, 8);
        testPiece = new Queen(3, 3, game.player0);
        game.playBoard.board[3][3] = testPiece;
    }

    @Test
    void isValidMove() {
        assertTrue(testPiece.isValidMove(0, 0)); // upLeft
        assertTrue(testPiece.isValidMove(3, 0)); // up
        assertTrue(testPiece.isValidMove(6, 0)); // upRight
        assertTrue(testPiece.isValidMove(0, 3)); // Left
        assertTrue(testPiece.isValidMove(1, 5)); // downLeft
        assertTrue(testPiece.isValidMove(3, 5)); // down
        assertTrue(testPiece.isValidMove(5, 5)); // downRight
        assertTrue(testPiece.isValidMove(5, 3)); // Right

        assertFalse(testPiece.isValidMove(-1, 9)); // invalid position
        assertFalse(testPiece.isValidMove(2,  1)); // invalid move

        // Leap over are tested in BoardTest
    }

    @Test
    void moving() {
        // test for moving diagonally
        List<int[]> getPath = testPiece.moving(0, 0);
        List<int[]> correctPath = new ArrayList<>();
        correctPath.add(new int[]{2, 2});
        correctPath.add(new int[]{1, 1});

        assertEquals(getPath.size(), correctPath.size());
        for (int i = 0; i < getPath.size(); i++) {
            assertArrayEquals(getPath.get(i), correctPath.get(i));
        }

        // test for moving along file
        getPath = testPiece.moving(3, 6);
        correctPath.clear();
        correctPath.add(new int[]{3, 4});
        correctPath.add(new int[]{3, 5});

        assertEquals(getPath.size(), correctPath.size());
        for (int i = 0; i < getPath.size(); i++) {
            assertArrayEquals(getPath.get(i), correctPath.get(i));
        }
    }
}