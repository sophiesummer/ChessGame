package Test;

import game.Game;
import org.junit.jupiter.api.Test;
import pieces.*;

import static org.junit.jupiter.api.Assertions.*;

class HopperTest {

    private Game game;
    private Hopper testPiece;

    public HopperTest() {
        game = new Game();
        testPiece = new Hopper(3, 3, game.player0, game.playBoard);
        game.playBoard.board[3][3] = testPiece;

        Pieces rookPlayer0 = new Rook(4, 3, game.player0);
        game.playBoard.board[4][3] = rookPlayer0;

        Pieces knightPlayer0 = new Knight(2, 3, game.player0);
        game.playBoard.board[2][3] = knightPlayer0;

        Pieces pawnPlayer0 = new Pawn(3, 2, game.player0, game.playBoard);
        game.playBoard.board[3][2] = pawnPlayer0;

        Pieces bishopPlayer0 = new Bishop(3, 4, game.player0);
        game.playBoard.board[3][4] = bishopPlayer0;
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