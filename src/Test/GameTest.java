package Test;

import game.Game;
import game.Player;
import org.junit.jupiter.api.Test;
import pieces.Pieces;
import pieces.Type;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void start() {
        Game game = new Game(8, 8, true, "", "");

        // test whether pieces initially placed correctly
        assertTrue(game.player0.pieces.size() == 16);
        assertTrue(game.player1.pieces.size() == 16);
        for (int i = 0; i < 8; i++) {
            assertEquals(Type.Pawn, game.playBoard.board[i][1].getType());
            assertEquals(Type.Pawn, game.playBoard.board[i][6].getType());
            assertEquals(game.player0, game.playBoard.board[i][1].getPlayer());
            assertEquals(game.player1, game.playBoard.board[i][6].getPlayer());
        }
    }
}