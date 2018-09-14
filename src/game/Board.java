package game;

import pieces.*;
import pieces.Type;

import java.util.*;

public class Board {

    public Pieces[][] board;
    private Player player0;
    private Player player1;
    private int rank;
    private int file;
    private int count = 0; // count whether over 100 turns no captures, which is a stalemate.


    public Board(int rank, int file, Player player0, Player player1) {
        this.rank = rank;
        this.file = file;
        board = new Pieces[rank][file];

        this.player0 = player0;
        this.player1 = player1;
    }

    /**
     * put all players' pieces and assign the pieces to them
     */
    public void setPieces() {
        player0.getPieces(setPlayer0Pieces());
        player1.getPieces(setPlayer1Pieces());
    }


    //initialize pieces position
    private List<Pieces> setPlayer0Pieces() {
        List<Pieces> player0Pieces = new ArrayList<>();
        //pawn
        for (int i = 0; i < file; i++) {
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
        player0.pieces = player0Pieces;

        return player0Pieces;
    }

    private List<Pieces> setPlayer1Pieces() {
        List<Pieces> player1Pieces = new ArrayList<>();

        //pawn
        for (int i = 0; i < 8; i++) {
            Pieces p1 = new Pawn(i, 6, player1);
            player1Pieces.add(p1);
            board[i][6] = p1;
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
        player1.pieces = player1Pieces;

        return player1Pieces;
    }


    // check whether this step is valid;
    public boolean checkValid(Player player, int prevX, int prevY, int newX, int newY) {
        Pieces piece = board[prevX][prevY];
        return player.hasThePiece(piece)
                && isValidMove(piece, newX, newY)
                && isValidMove(piece.moving(newX, newY));
    }


    /**
     * put the piece into correct position and do capture
     * @param player the player who is moving pieces
     * @param prevX
     * @param prevY
     * @param newX
     * @param newY
     * @return  pieces which is removed from the board
     */
    public Pieces putPieces(Player player, int prevX, int prevY, int newX, int newY) {
        Pieces piece = board[prevX][prevY];
        if (!checkValid(player, prevX, prevY, newX, newY)) {
            return null;
        }
        count++; // count increasing for each turn
        Pieces prevPiece = board[newX][newY];
        if (board[newX][newY] != null) {
            count = 0; // if capture happen, count from start.
            Player standingPlayer = prevPiece.getPlayer();
            standingPlayer.pieces.remove(prevPiece); // remove defeated piece
        }
        if (prevPiece instanceof King) {
         prevPiece.getPlayer().isLose = true;   // lose the king
        }
        board[newX][newY] = piece; //put pieces into the new position
        piece.move(newX, newY);
        board[prevX][prevY] = null;
        return prevPiece;
    }


    /**
     * check whether the piece leap over others
     * @param moves  the path of its moving, which stores all the squares it past
     * @return
     */
    private boolean isValidMove(List<int[]> moves) {
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
    private boolean isValidMove(Pieces p, int newX, int newY) {

        if (board[newX][newY] != null) {
            Player movingPlayer = p.getPlayer();
            Player standingPlayer = board[newX][newY].getPlayer();
            if (movingPlayer == standingPlayer) { // destination cannot be an ally piece
                return false;
            }

            //pawn capture case, it could only attack diagonally
            if (p instanceof Pawn) {
                Pawn pawn = (Pawn)p;
                if (movingPlayer.color == 0) {
                    return Math.abs(newX - pawn.getPosition()[0]) == 1
                            && newY - pawn.getPosition()[1] == 1;
                } else {
                    return Math.abs(newX - pawn.getPosition()[0]) == 1
                            && newY - pawn.getPosition()[1] == -1;
                }
            }
        }
        return p.isValidMove(newX, newY);
    }


    /**
     * check whether exists pieces can capture opponent's king
     * @param movingPlayer   the player who is now moving a piece.
     * @param opponent the player who is now waiting for the next round
     * @return
     */
    public boolean inCheck(Player movingPlayer, Player opponent) {
        List<Pieces> opponentPieces = opponent.pieces;
        int[] oppKingPos = new int[2];
        for (Pieces opp: opponentPieces) {
            if (opp instanceof King) {
                oppKingPos = opp.getPosition();
                break;
            }
        }

        for (Pieces movingPiece: movingPlayer.pieces) {
            int[] pos = movingPiece.getPosition();
            // check whether there's a piece can reach the opponent's king
            if (checkValid(movingPlayer, pos[0], pos[1], oppKingPos[0], oppKingPos[1])) {
                return true;
            }
        }
        return false;
    }


    /**
     * opponent's king is in check and there's no legal move, then checkmate
     * @param movingPlayer the player who is now moving a piece.
     * @param opponent  the player who is now waiting for the next round
     * @return whether opponent is lose.
     */
    public boolean isCheckmate(Player movingPlayer, Player opponent) {
        if (inCheck(movingPlayer, opponent) && !hasLegalMove(movingPlayer, opponent)) {
            opponent.isLose = true;
            System.out.println("CheckMate!");
            return true;
        } else {
            return false;
        }
    }

    /**
     * king is not in check, but there's no legal move for opponent to remove danger.
     * Or over 50 turns no capture happen
     * Or both players only have their king piece
     * @param movingPlayer the player who is now moving a piece.
     * @param opponent  the player who is now waiting for the next round
     * @return whether it's a draw.
     */
    public boolean isStalemate(Player movingPlayer, Player opponent) {
        if (!inCheck(movingPlayer, opponent) && !hasLegalMove(movingPlayer, opponent)) {
          return true;
        }
        if (!player1.isLose && !player0.isLose
                && player0.pieces.size() == 1 && player1.pieces.size() == 1) {
            return true;
        }

        if (count > 100){
            return true;
        }
        return false;
    }


    /**
     *  for all the possibilities, see whether the checked player still in check
     *  in other word, check whether checkedPlayer has no legal move.
     * @param movingPlayer  the player now is checking the opponent
     * @param checkedPlayer  the player now is checked
     * @return
     */
    private boolean hasLegalMove(Player movingPlayer, Player checkedPlayer) {
        for (Pieces eachPieces : checkedPlayer.pieces) {
            int[] prevPos = eachPieces.getPosition();
            for (int i = 0; i < rank; i++) {
                for (int j = 0; j < file; j++) {
                    if (checkValid(checkedPlayer, prevPos[0], prevPos[1], i, j)) {
                        Pieces removed = putPieces(checkedPlayer, prevPos[0], prevPos[1], i, j);
                        if (!inCheck(movingPlayer, checkedPlayer)) {
                            // go back to last step
                            board[prevPos[0]][prevPos[1]] = eachPieces;
                            eachPieces.setX(prevPos[0]);
                            eachPieces.setY(prevPos[1]);
                            if (removed != null) {
                                board[i][j] = removed;
                                removed.getPlayer().pieces.add(removed);
                            }
                            System.out.print(prevPos[0]);
                            System.out.println(prevPos[1]);
                            return true;
                        }
                        // go back to last step
                        board[prevPos[0]][prevPos[1]] = eachPieces;
                        eachPieces.setX(prevPos[0]);
                        eachPieces.setY(prevPos[1]);
                        if (removed != null) {
                            board[i][j] = removed;
                            removed.getPlayer().pieces.add(removed);
                        }
                    }
                }
            }
        }
        return false;
    }
}
