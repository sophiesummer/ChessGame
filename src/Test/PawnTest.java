package Test;

import game.Game;
import org.junit.jupiter.api.Test;
import pieces.Pawn;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PawnTest {

    private Game game;
    private Pawn testPiece;

    public PawnTest() {
        game = new Game(8, 8);
        testPiece = new Pawn(3, 1, game.player0);
        game.playBoard.board[3][1] = testPiece;
    }

    @Test
    void isValidMove() {
        assertTrue(testPiece.isValidMove(3, 2)); // move one step at the first step
        assertTrue(testPiece.isValidMove(3, 3)); // move two steps at the first step
        testPiece.move(3, 3);
        assertTrue(testPiece.isValidMove(3, 4)); // move one step after first step


        assertFalse(testPiece.isValidMove(3, 5)); // move two steps after first step
        assertFalse(testPiece.isValidMove(-1,8)); // invalid position

        // pawn diagonally attack validation tests are in the BoardTest
    }

    @Test
    void moving() {
        testPiece.move(3, 3); // place pawn in [3, 3] position

        List<int[]> getPath = testPiece.moving(3, 4);
        assertTrue(getPath.isEmpty()); // move one step, do not store path

        testPiece = new Pawn(3, 1, game.player0); // create a new piece
        getPath = testPiece.moving(3, 3); // first step move two steps
        assertEquals(1, getPath.size());
        assertArrayEquals(new int[]{3, 2}, getPath.get(0));
    }


}