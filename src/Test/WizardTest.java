package Test;

import game.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pieces.Type;
import pieces.Wizard;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WizardTest {

    private Game game;
    private Wizard testPiece;

    public WizardTest() {
        game = new Game(8, 8);
        testPiece = new Wizard(3, 3, game.player0);
        game.playBoard.board[3][3] = testPiece;
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
        assertEquals(null, testPiece.moving(7, 2));
        assertEquals(null, testPiece.moving(-1, -2));// invalid move test
        testPiece.setX(3);
        testPiece.setY(4);
        assertArrayEquals(new int[]{3, 4}, testPiece.getPosition());
    }

}