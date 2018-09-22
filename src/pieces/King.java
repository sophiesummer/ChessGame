package pieces;

import game.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * King class
 * <p>
 * The king moves one square in any direction.
 */
public class King implements Pieces{

    /** rank position of the piece */
    private int x;

    /** file position of the piece */
    private int y;

    /** the player piece belongs to */
    public Player player;

    /** the type this piece is */
    public Type type = Type.King;

    /**
     * initialize king attributes
     * @param x the initial rank position
     * @param y the initial file position
     * @param player the player piece belong to
     */
    public King(int x, int y, Player player) {
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
     * check whether king is moved by its own rule;
     * king could only move one step but can in all directions
     * @param newX destination rank of the piece
     * @param newY destination file of the piece
     * @return whether this step is a valid move
     */
    @Override
    public boolean isValidMove(int newX, int newY) {
        if (newX < 0 || newX > 7 || newY < 0 || newY > 7
                || (x == newX && y == newY)) {
            return false;
        }

        return (Math.abs(newX - x) <= 1 && Math.abs(newY - y) <= 1);
    }

    /**
     * store the passing positions
     * a helper function for later check leap over other pieces
     * @param newX destination rank of the piece
     * @param newY destination file of the piece
     * @return since king only move one step, there's no square to leap over
     */
    @Override
    public List<int[]> moving(int newX, int newY) {
        return null; //empty
    }

    /**
     * update the piece position
     * @param newX destination rank of the piece
     * @param newY destination file of the piece
     */
    @Override
    public void move(int newX, int newY) {
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
