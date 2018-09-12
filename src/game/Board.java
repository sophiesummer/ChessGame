package game;

import pieces.*;
import pieces.Type;

import java.util.*;

public class Board {

    public Pieces[][] board;
    private Player player0;
    private Player player1;


    //initialize pieces position

    public Board(Player player0, Player player1) {
        board = new Pieces[8][8];

        this.player0 = player0;
        this.player1 = player1;

        player0.getPieces(setPlayer0Pieces());
        player1.getPieces(setPlayer1Pieces());
    }


    private List<Pieces> setPlayer0Pieces() {
        List<Pieces> player0Pieces = new ArrayList<>();
        //pawn
        for (int i = 0; i < 8; i++) {
            Pieces p0 = new Pawn(i, 1, player0);
            player0Pieces.add(p0);
            board[i][1] = p0;
        }

        Pieces rook1 = new Rook(0,0, player0);
        Pieces rook2 = new Rook(7,0, player0);
        board[0][0] = rook1;
        board[7][0] = rook2;
        player0Pieces.add(rook1);
        player0Pieces.add(rook2);

        Pieces knight1 = new Knight(1,0, player0);
        Pieces knight2 = new Knight(6,0, player0);
        board[1][0] = knight1;
        board[6][0] = knight2;
        player0Pieces.add(knight1);
        player0Pieces.add(knight2);

        Pieces bishop1 = new Bishop(2, 0, player0);
        Pieces bishop2 = new Bishop(5, 0, player0);
        board[2][0] = bishop1;
        board[5][0] = bishop2;
        player0Pieces.add(bishop1);
        player0Pieces.add(bishop2);

        Pieces queen = new Queen(3, 0, player0);
        Pieces king = new King(4, 0, player0);
        board[3][0] = queen;
        board[4][0] = king;
        player0Pieces.add(queen);
        player0Pieces.add(king);

        return player0Pieces;
    }

    private List<Pieces> setPlayer1Pieces() {
        List<Pieces> player1Pieces = new ArrayList<>();

        //pawn
        for (int i = 0; i < 8; i++) {
            Pieces p1 = new Pawn(i, 6, player1);
            player1Pieces.add(p1);
            board[i][1] = p1;
        }

        Pieces rook1 = new Rook(0,7, player1);
        Pieces rook2 = new Rook(7,7, player1);
        board[0][7] = rook1;
        board[7][7] = rook2;
        player1Pieces.add(rook1);
        player1Pieces.add(rook2);

        Pieces knight1 = new Knight(1,7, player1);
        Pieces knight2 = new Knight(6,7, player1);
        board[1][7] = knight1;
        board[6][7] = knight2;
        player1Pieces.add(knight1);
        player1Pieces.add(knight2);

        Pieces bishop1 = new Bishop(2,7, player1);
        Pieces bishop2 = new Bishop(5,7, player1);
        board[2][7] = bishop1;
        board[5][7] = bishop2;
        player1Pieces.add(bishop1);
        player1Pieces.add(bishop2);

        Pieces queen = new Queen(3,7, player1);
        Pieces king = new King(4,7, player1);
        board[3][7] = queen;
        board[4][7] = king;
        player1Pieces.add(queen);
        player1Pieces.add(king);

        return player1Pieces;
    }


    public boolean checkValid(Player player, Pieces piece, int newX, int newY) {
        return isValidMove(piece, newX, newY) && isValidMove(piece, piece.moving(newX, newY));
    }

    public void putPieces(Player player, Pieces piece, int newX, int newY) {
        if (!checkValid(player, piece, newX, newY)) {
            return;
        }

        if (board[newX][newY] != null) {
            Player movingPlayer = piece.getPlayer();
            Player standingPlayer = board[newX][newY].getPlayer();
            Pieces prevPiece = board[newX][newY];

            board[newX][newY] = piece;
            piece.move(newX, newY);

            standingPlayer.pieces.remove(prevPiece); // remove defeated piece
        }
    }

    // check whether the piece leap over others
    public boolean isValidMove(Pieces p, List<int[]> moves) {
        if (moves == null || moves.isEmpty()) {
            return true;
        }

        for (int[] m : moves) {
            if (board[m[0]][m[1]] != null) {
                return false;
            }
        }
        return true;
    }

    // check whether the destination is valid
    public boolean isValidMove(Pieces p, int newX, int newY) {

        if (board[newX][newY] != null) {
            Player movingPlayer = p.getPlayer();
            Player standingPlayer = board[newX][newY].getPlayer();
            if (movingPlayer == standingPlayer) {
                return false;
            }

            //pawn capture case
            if (p instanceof Pawn) {
                Pawn pawn = (Pawn)p;
                if (movingPlayer.color == 0) {
                    if (Math.abs(newX - pawn.x) == 1 && newY - pawn.y == 1) {
                        return true;
                    }
                } else {
                    if (Math.abs(newX - pawn.x) == 1 && newY - pawn.y == -1) {
                        return true;
                    }
                }
            }
        }

        if (!p.isValidMove(newX, newY)) { // pieces own rules
            return false;
        }
        return true;
    }

    public boolean fight(Pieces coming, Pieces standing) {

        return true;
    }

    public void updateBoard(Type p, int[] prevPos, int[] newPos) {

    }



}
