package Test;

import game.Board;
import game.Game;
import org.junit.jupiter.api.Test;
import pieces.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    private Game game;
    private Board playBoard;
    private List<Pieces> player0Pieces;
    private List<Pieces> player1Pieces;

    BoardTest() {
        game = new Game();
        playBoard = new Board(game);
        game.playBoard = playBoard;
        player0Pieces = new ArrayList<>();
        player1Pieces = new ArrayList<>();
        game.player0.pieces = player0Pieces;
        game.player1.pieces = player1Pieces;
    }

    @Test
    void checkValidationAndMovePieces() {

        // add pieces to player0
        Pieces rookPlayer0 = new Rook(3, 3, game.player0);
        playBoard.board[3][3] = rookPlayer0;
        player0Pieces.add(rookPlayer0);

        Pieces knightPlayer0 = new Knight(3, 2, game.player0);
        playBoard.board[3][2] = knightPlayer0;
        player0Pieces.add(knightPlayer0);

        Pieces pawnPlayer0 = new Pawn(5, 1, game.player0, playBoard);
        playBoard.board[5][1] = pawnPlayer0;
        player0Pieces.add(pawnPlayer0);

        Pieces bishopPlayer0 = new Bishop(5, 2, game.player0);
        playBoard.board[5][2] = bishopPlayer0;
        player0Pieces.add(bishopPlayer0);

        Pieces hopperPlayer0 = new Hopper(4, 1, game.player0, playBoard);
        playBoard.board[4][1] = hopperPlayer0;
        player0Pieces.add(hopperPlayer0);

        Pieces wizardPlayer0 = new Wizard(2, 3, game.player0);
        playBoard.board[2][3] = wizardPlayer0;
        player0Pieces.add(wizardPlayer0);

        // add pieces to player1
        Pieces queenPlayer1 = new Queen(1, 5, game.player1);
        playBoard.board[1][5] = queenPlayer1;
        player1Pieces.add(queenPlayer1);

        Pieces knightPlayer1 = new Knight(3, 5, game.player1);
        playBoard.board[3][5] = knightPlayer1;
        player1Pieces.add(knightPlayer1);

        Pieces pawnPlayer1 = new Pawn(5, 6, game.player1, playBoard);
        playBoard.board[5][6] = pawnPlayer1;
        player1Pieces.add(pawnPlayer1);

        Pieces knight1Player1 = new Knight(5, 2, game.player1);
        playBoard.board[5][2] = knight1Player1;
        player1Pieces.add(knight1Player1);

        Pieces rookPlayer1 = new Rook(4, 2, game.player1);
        playBoard.board[4][2] = rookPlayer1;
        player1Pieces.add(rookPlayer1);

        Pieces hopperPlayer1 = new Hopper(0, 5, game.player1, playBoard);
        playBoard.board[0][5] = hopperPlayer1;
        player1Pieces.add(hopperPlayer1);

        Pieces wizardPlayer1 = new Wizard(1, 4, game.player1);
        playBoard.board[1][4] = wizardPlayer1;
        player1Pieces.add(wizardPlayer1);


        // test pieces cannot leap over other pieces
        assertFalse(playBoard.checkValid(game.player1, 1, 5,  4, 2));
        assertFalse(playBoard.checkValid(game.player0, 3, 3,  3, 6));

        // test knight can leap over other pieces
        assertTrue(playBoard.checkValid(game.player1, 3, 5, 2, 3));
        assertTrue(playBoard.checkValid(game.player0, 3, 2, 4, 4));

        // test destination stands ally pieces
        assertFalse(playBoard.checkValid(game.player0, 3, 2,  5, 1));
        assertFalse(playBoard.checkValid(game.player1, 1, 5,  3, 5));

        // test Hopper can jump over a piece
        assertTrue(hopperPlayer0.isValidMove(4, 3));
        assertTrue(playBoard.board[4][1].getType() == Type.Hopper);
        assertTrue(playBoard.checkValid(game.player0, 4, 1, 4, 3));
        assertTrue(playBoard.checkValid(game.player1, 0, 5, 2, 5));
        playBoard.putPieces(game.player1, 0, 5, 2, 5);
        assertTrue(playBoard.board[2][5].getType() == Type.Hopper);

        // test Pawn attack
        // cannot attack along file
        assertFalse(playBoard.checkValid(game.player0, 5, 1, 5, 2));
        // cannot attack step two grids along file
        assertFalse(playBoard.checkValid(game.player1, 5, 6, 5, 6));
        // attack diagonally
        assertTrue(playBoard.checkValid(game.player0, 5, 1, 4, 2));

        // player1's knight1 attack player0's rook
        assertTrue(playBoard.checkValid(game.player1, 5, 2, 3,3));
        assertEquals(rookPlayer0, playBoard.putPieces(game.player1, 5, 2, 3, 3));
        assertFalse(game.player0.hasThePiece(rookPlayer0)); // player0 no longer has the attacked piece
        assertEquals(knight1Player1, playBoard.board[3][3]); // bishop goes to the new position
        assertEquals(null, playBoard.board[5][2]); // the previous position now is null
        // player1's knight1's position has been update
        assertArrayEquals(knight1Player1.getPosition(), new int[]{3, 3});

        //player0's Pawn attack player1's Rook diagonally
        // check pawn attack validation
        assertTrue(playBoard.checkValid(game.player0, 5, 1, 4, 2));
        // check removed pieces
        assertEquals(rookPlayer1, playBoard.putPieces(game.player0, 5, 1, 4, 2));
        assertFalse(game.player1.hasThePiece(rookPlayer1));// player1 no longer has the attacked piece
        assertEquals(pawnPlayer0, playBoard.board[4][2]); // pawn goes to the new position
        assertEquals(null, playBoard.board[5][2]); // previous position has no pieces now
        assertArrayEquals(pawnPlayer0.getPosition(), new int[]{4, 2}); // pawn's position has been updated

        playBoard.putPieces(game.player1, 1, 4, 0, 3);
        assertTrue(playBoard.board[0][3].getType() == Type.Wizard);
        assertTrue(playBoard.board[0][3].getPlayer() == game.player1);
        assertArrayEquals(new int[]{0, 3}, playBoard.board[0][3].getPosition());

    }

    @Test
    void isSetPiecesCorrect() {

        playBoard.setPieces();
        for (int i = 0; i < 8; i++) {
            assertEquals(playBoard.board[i][1].getType(), Type.Pawn);
            assertEquals(playBoard.board[i][1].getPlayer(), game.player0);
        }
        assertEquals(playBoard.board[5][7].getType(), Type.Bishop);
        assertEquals(playBoard.board[5][7].getPlayer(), game.player1);
        assertEquals(playBoard.board[0][7].getType(), Type.Rook);
        assertEquals(playBoard.board[0][7].getPlayer(), game.player1);

        assertEquals(playBoard.board[4][0].getType(), Type.King);
        assertEquals(playBoard.board[4][0].getPlayer(), game.player0);
        assertEquals(playBoard.board[3][0].getType(), Type.Queen);
        assertEquals(playBoard.board[3][0].getPlayer(), game.player0);

        assertEquals(playBoard.board[1][0].getType(), Type.Knight);
        assertEquals(playBoard.board[1][0].getPlayer(), game.player0);
    }

    @Test
    void isCheckmateCase1() {
        // Fool's Mate case
        // https://en.wikipedia.org/wiki/Checkmate#Examples

        playBoard.setPieces();
        // first round
        assertTrue(playBoard.checkValid(game.player0, 4, 1, 4, 3));
        playBoard.putPieces(game.player0, 4, 1, 4, 3);
        assertTrue(playBoard.checkValid(game.player1, 5, 6, 5, 5));
        playBoard.putPieces(game.player1, 5, 6, 5, 5);

        // second round
        assertTrue(playBoard.checkValid(game.player0, 3, 0, 7, 4));
        playBoard.putPieces(game.player0, 3, 0, 7, 4);
        assertTrue(playBoard.checkValid(game.player1, 6, 6, 6, 4));
        playBoard.putPieces(game.player1, 6, 6, 6, 4);

        assertTrue(playBoard.inCheck(game.player0, game.player1)); // player1's king is in check
        assertTrue(playBoard.isCheckmate(game.player0, game.player1)); // checkMate
    }

    @Test
    void isCheckmateCase2() {
        // D. Byrne vs. Fischer case
        // https://en.wikipedia.org/wiki/Checkmate#Examples

        // add pieces to player0
        Pieces kingPlayer0 = new King(6, 1, game.player0);
        playBoard.board[6][1] = kingPlayer0;
        player0Pieces.add(kingPlayer0);

        Pieces pawn0Player0 = new Pawn(5, 1, game.player0, playBoard);
        playBoard.board[5][1] = pawn0Player0;
        player0Pieces.add(pawn0Player0);

        Pieces pawn1Player0 = new Pawn(6, 2, game.player0, playBoard);
        playBoard.board[6][2] = pawn1Player0;
        player0Pieces.add(pawn1Player0);

        Pieces pawn2Player0 = new Pawn(7, 3, game.player0, playBoard);
        playBoard.board[7][3] = pawn2Player0;
        player0Pieces.add(pawn2Player0);

        Pieces pawn3Player0 = new Pawn(2, 2, game.player0, playBoard);
        playBoard.board[2][2] = pawn3Player0;
        player0Pieces.add(pawn3Player0);

        Pieces pawn4Player0 = new Pawn(1, 3, game.player0, playBoard);
        playBoard.board[1][3] = pawn4Player0;
        player0Pieces.add(pawn4Player0);

        for (int i = 4; i <= 5; i++) {
            Pieces bishopPlayer0 = new Bishop(1, i, game.player0);
            playBoard.board[1][i] = bishopPlayer0;
            player0Pieces.add(bishopPlayer0);
        }

        Pieces rookPlayer0 = new Rook(2, 6, game.player0);
        playBoard.board[2][6] = rookPlayer0;
        player0Pieces.add(rookPlayer0);

        Pieces knightPlayer0 = new Knight(2, 5, game.player0);
        playBoard.board[2][5] = knightPlayer0;
        player0Pieces.add(knightPlayer0);

        // add pieces to player1
        Pieces kingPlayer1 = new King(2, 7, game.player1);
        playBoard.board[2][7] = kingPlayer1;
        player1Pieces.add(kingPlayer1);

        Pieces queenPlayer1 = new Queen(1, 0, game.player1);
        playBoard.board[2][7] = queenPlayer1;
        player1Pieces.add(queenPlayer1);

        Pieces knightPlayer1 = new Knight(4, 3, game.player1);
        playBoard.board[4][3] = knightPlayer1;
        player1Pieces.add(knightPlayer1);

        Pieces pawn0Player1 = new Pawn(7, 4, game.player1, playBoard);
        playBoard.board[7][4] = pawn0Player1;
        player1Pieces.add(pawn0Player1);

        Pieces pawn1Player1 = new Pawn(6, 6, game.player1, playBoard);
        playBoard.board[6][6] = pawn1Player1;
        player1Pieces.add(pawn1Player1);

        assertTrue(playBoard.isCheckmate(game.player0, game.player1));
        assertTrue(game.judge(game.player0, game.player1));
        assertTrue(game.start());
    }

    @Test
    void isCheckmateCase3() {
        // Checkmate with a rook case
        // https://en.wikipedia.org/wiki/Checkmate#Examples

        // add pieces to player0
        Pieces kingPlayer0 = new King(7, 3, game.player0);
        playBoard.board[7][3] = kingPlayer0;
        player0Pieces.add(kingPlayer0);

        // add pieces to player1
        Pieces kingPlayer1 = new King(5, 3, game.player1);
        playBoard.board[5][3] = kingPlayer1;
        player1Pieces.add(kingPlayer1);

        Pieces rookPlayer1 = new Rook(7, 7, game.player1);
        playBoard.board[7][7] = rookPlayer1;
        player1Pieces.add(rookPlayer1);
        player1Pieces.add(rookPlayer1);


        assertTrue(playBoard.isCheckmate(game.player1, game.player0));
        assertTrue(game.judge(game.player0, game.player1));
        assertTrue(game.start());
    }


    @Test
    void isStalemateCase1() {
        // the first simple example on wiki
        // https://en.wikipedia.org/wiki/Stalemate#Simple_examples

        // add pieces to player0
        Pieces kingPlayer0 = new King(5, 0, game.player0);
        playBoard.board[5][0] = kingPlayer0;
        player0Pieces.add(kingPlayer0);

        // add pieces to player1
        Pieces kingPlayer1 = new King(5, 2, game.player1);
        playBoard.board[5][2] = kingPlayer1;
        player1Pieces.add(kingPlayer1);

        Pieces pawnPlayer1 = new Pawn(5, 1, game.player1, playBoard);
        playBoard.board[5][1] = pawnPlayer1;
        player1Pieces.add(pawnPlayer1);

        assertTrue(playBoard.isStalemate(game.player1, game.player0));
    }

    @Test
    void isStalemateCase2() {
        // the second simple example on wiki
        // https://en.wikipedia.org/wiki/Stalemate#Simple_examples

        // add pieces to player0
        Pieces kingPlayer0 = new King(0, 0, game.player0);
        playBoard.board[0][0] = kingPlayer0;
        player0Pieces.add(kingPlayer0);

        Pieces bishopPlayer0 = new Bishop(1, 0, game.player0);
        playBoard.board[1][0] = bishopPlayer0;
        player0Pieces.add(bishopPlayer0);

        // add pieces to player1
        Pieces kingPlayer1 = new King(1, 2, game.player1);
        playBoard.board[1][2] = kingPlayer1;
        player1Pieces.add(kingPlayer1);

        Pieces rookPlayer1 = new Rook(7, 0, game.player1);
        playBoard.board[7][0] = rookPlayer1;
        player1Pieces.add(rookPlayer1);

        assertTrue(playBoard.isStalemate(game.player1, game.player0));
    }

    @Test
    void isStalemateCase3() {
        // the third simple example on wiki
        // https://en.wikipedia.org/wiki/Stalemate#Simple_examples

        // add pieces to player0
        Pieces kingPlayer0 = new King(0, 7, game.player0);
        playBoard.board[0][7] = kingPlayer0;
        player0Pieces.add(kingPlayer0);

        // add pieces to player1
        Pieces kingPlayer1 = new King(2, 5, game.player1);
        playBoard.board[2][5] = kingPlayer1;
        player1Pieces.add(kingPlayer1);

        Pieces rookPlayer1 = new Rook(1, 6, game.player1);
        playBoard.board[1][6] = rookPlayer1;
        player1Pieces.add(rookPlayer1);

        assertTrue(playBoard.isStalemate(game.player1, game.player0));
    }

    @Test
    void isStalemateCase4() {
        // the fifth simple example on wiki
        // https://en.wikipedia.org/wiki/Stalemate#Simple_examples

        // add pieces to player0
        Pieces kingPlayer0 = new King(0, 0, game.player0);
        playBoard.board[0][0] = kingPlayer0;
        player0Pieces.add(kingPlayer0);


        // add pieces to player1
        Pieces kingPlayer1 = new King(0, 2, game.player1);
        playBoard.board[0][2] = kingPlayer1;
        player1Pieces.add(kingPlayer1);

        Pieces pawnPlayer1 = new Pawn(0, 1, game.player1, playBoard);
        playBoard.board[0][1] = pawnPlayer1;
        player1Pieces.add(pawnPlayer1);

        Pieces queenPlayer1 = new Pawn(5, 4, game.player1, playBoard);
        playBoard.board[5][4] = queenPlayer1;
        player1Pieces.add(queenPlayer1);

        assertTrue(playBoard.isStalemate(game.player1, game.player0));
    }

}