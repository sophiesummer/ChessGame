package Test;

import game.Game;
import org.junit.jupiter.api.Test;
import pieces.Pawn;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PawnTest {

    private Game game;
    private Pawn testPiece0;
    private Pawn testPiece1;

    public PawnTest() {
        game = new Game(8, 8);
        testPiece0 = new Pawn(3, 1, game.player0, game.playBoard);
        testPiece1 = new Pawn(3, 6, game.player1, game.playBoard);
        game.playBoard.board[3][1] = testPiece0;
    }

    @Test
    void isValidMove() {
        assertTrue(testPiece0.isValidMove(3, 2)); // move one step at the first step
        assertTrue(testPiece0.isValidMove(3, 3)); // move two steps at the first step
        testPiece0.move(3, 3);
        assertTrue(testPiece0.isValidMove(3, 4)); // move one step after first step

        assertFalse(testPiece0.isValidMove(3, 2)); // cannot move backwards
        assertFalse(testPiece0.isValidMove(3, 5)); // move two steps after first step
        assertFalse(testPiece0.isValidMove(4, 3)); // cannot move right
        assertFalse(testPiece0.isValidMove(2, 3)); // cannot move left
        assertFalse(testPiece0.isValidMove(-1,8)); // invalid position

        // test backwards move for player1 direction
        testPiece1.move(3, 4);
        assertFalse(testPiece1.isValidMove(3, 5));

        // pawn diagonally attack validation tests are in the BoardTest
    }

    @Test
    void moving() {
        testPiece0.move(3, 3); // place pawn in [3, 3] position

        List<int[]> getPath = testPiece0.moving(3, 4);
        assertTrue(getPath.isEmpty()); // move one step, do not store path

        testPiece0 = new Pawn(3, 1, game.player0, game.playBoard); // create a new piece
        getPath = testPiece0.moving(3, 3); // first step move two steps
        assertEquals(1, getPath.size());
        assertArrayEquals(new int[]{3, 2}, getPath.get(0));
    }


}