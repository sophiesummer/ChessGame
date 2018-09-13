package Test;

import game.Board;
import game.Game;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    public BoardTest() {
        Game game = new Game(8, 8);
        Board board = new Board(game.rank, game.file, game.player0, game.player1);

    }

    @Test
    void checkValid(){


    }

    @Test
    void putPieces() {
    }

    @Test
    void inCheck() {
    }

    @Test
    void isCheckmate() {
    }

    @Test
    void isStalemate() {
    }
}