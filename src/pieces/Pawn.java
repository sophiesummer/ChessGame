package pieces;

import game.Player;

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
public class Pawn implements Pieces {

    /** rank position of the piece */
    private int x;

    /** file position of the piece */
    private int y;

    /** the player piece belongs to */
    public Player player;

    /** the type this piece is */
    public Type type = Type.Pawn;

    /** the flag to ensure whether this Pawn has moved before */
    private boolean firstStep = true;

    /**
     * initialize pawn attributes
     * @param x the initial rank position
     * @param y the initial file position
     * @param player the player piece belong to
     */
    public Pawn(int x, int y, Player player) {
        this.x = x;
        this.y = y;
        this.player = player;
    }

    /**
     * get position of the piece
     * @return an array contains two elements, x position and y position.
     */
    @Override
    public int[] getPosition() {
        return new int[]{this.x, this.y};
    }

    /**
     * get Type of the piece
     * @return the enum type of the piece
     */
    @Override
    public Type getType() {
        return type;
    }

    /**
     * get Player reference of the piece
     * @return the player this piece belongs to
     */
    @Override
    public Player getPlayer() {
        return player;
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

        // check move forward and the number of steps
        if (player.color == 0) {
            if (firstStep && newX == x && (newY - y == 1 || newY - y == 2)) {
                return true;
            }

            if (!firstStep && newX == x && newY - y == 1) {
                return true;
            }
        } else {
            if (firstStep && newX == x && (newY - y == -1 || newY - y == -2)) {
                return true;
            }

            if (!firstStep && newX == x && newY - y == -1) {
                return true;
            }
        }
        return false;
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

    /**
     * set piece's rank position
     * @param x new rank position
     */
    @Override
    public void setX(int x) {
        this.x = x;
    }

    /**
     * set piece's file position
     * @param y new file position
     */
    @Override
    public void setY(int y) {
        this.y = y;
    }


}
