package pieces;

import game.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Pawn class
 * <p>
 * The pawn may move forward to the unoccupied square
 * immediately in front of it on the same file;
 * or on its first move it may advance two squares along the same file
 * provided both squares are unoccupied;
 * or it may move to a square occupied by an opponent's piece
 * which is diagonally in front of it on an adjacent file, capturing that piece.
 */
public class Pawn extends Pieces {

    /** the flag to ensure whether this Pawn has moved before */
    private boolean firstStep = true;

    /** the board this piece is moving on */
    private Board playBoard;

    /**
     * initialize pawn attributes
     * @param x the initial rank position
     * @param y the initial file position
     * @param player the player piece belong to
     */
    public Pawn(int x, int y, Player player, Board playBoard) {
        super(x, y, player);
        type = Type.Pawn;
        this.playBoard = playBoard;
    }


    /**
     * check whether pawn is moved by its own rule;
     * pawn could only move forward, only one step except for the first move
     * @param newX newX destination rank of the piece
     * @param newY destination file of the piece
     * @return whether this step is a valid move
     */
    @Override
    public boolean isValidMove(int newX, int newY) {  // not include attack piece
        if (newX < 0 || newX > 7 || newY < 0 || newY > 7
                || (x == newX && y == newY)) {
            return false;
        }

        //pawn capture case, it could only attack diagonally
        if (playBoard.board[newX][newY] != null) {
            if (player.color == 0) {
                return Math.abs(newX - x) == 1 && newY - y == 1;
            } else {
                return Math.abs(newX - x) == 1 && newY - y == -1;
            }
        } else {
            // check move forward and the number of steps
            if (player.color == 0) {
                return (firstStep && newX == x && (newY - y == 1 || newY - y == 2))
                        || (!firstStep && newX == x && newY - y == 1);
            } else {
                return (firstStep && newX == x && (newY - y == -1 || newY - y == -2))
                        || (!firstStep && newX == x && newY - y == -1);
            }
        }
    }

    /**
     * store the passing positions
     * a helper function for later check leap over other pieces
     * @param newX destination rank of the piece
     * @param newY destination file of the piece
     * @return only if pawn move two steps, we store the leaped over position
     */
    @Override
    public List<int[]> moving(int newX, int newY) {
        List<int[]> steps = new ArrayList<>();
        if (Math.abs(newY - y) == 2)  {
            steps.add(new int[]{x, (newY + y) / 2});
        }
        return steps;
    }

    /**
     * update the piece position
     * @param newX destination rank of the piece
     * @param newY destination file of the piece
     */
    @Override
    public void move(int newX, int newY) {
        firstStep = false;
        x = newX;
        y = newY;
    }

}
