package Test;

import game.Game;
import org.junit.jupiter.api.Test;
import pieces.Hopper;

import static org.junit.jupiter.api.Assertions.*;

class HopperTest {

    private Game game;
    private Hopper testPiece;

    public HopperTest() {
        game = new Game(8, 8);
        testPiece = new Hopper(3, 3, game.player0);
        game.playBoard.board[3][3] = testPiece;
    }

    @Test
    void isValidMove() {
        assertTrue(testPiece.isValidMove(5, 3)); // Right
        assertTrue(testPiece.isValidMove(1, 3)); // Left
        assertTrue(testPiece.isValidMove(3, 5)); // down
        assertTrue(testPiece.isValidMove(3, 1)); // up

        assertFalse(testPiece.isValidMove(3, 4)); // move one step
        assertFalse(testPiece.isValidMove(6, 2)); // invalid move
        assertFalse(testPiece.isValidMove(-1,7)); // invalid position
    }

    @Test
    void moving() {
        assertEquals(null, testPiece.moving(5, 3));
        testPiece.setX(0);
        testPiece.setY(1);
        assertArrayEquals(new int[]{0, 1}, testPiece.getPosition());
        assertEquals(game.player0, testPiece.getPlayer());
    }
}