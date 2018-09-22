package pieces;

import game.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Knight class
 * <p>
 * The knight moves to any of the closest squares that are not on the same rank,
 * file, or diagonal, thus the move forms an "L"-shape:
 * two squares vertically and one square horizontally,
 * or two squares horizontally and one square vertically.
 * The knight is the only piece that can leap over other pieces.
 */
public class Knight implements Pieces {

    /** rank position of the piece */
    private int x;

    /** file position of the piece */
    private int y;

    /** the player piece belongs to */
    public Player player;

    /** the type this piece is */
    public Type type = Type.Knight;

    /**
     * initialize knight attributes
     * @param x the initial rank position
     * @param y the initial file position
     * @param player the player piece belong to
     */
    public Knight(int x, int y, Player player) {
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
        return new int[]{x, y};
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
     * check whether knight is moved by its own rule;
     * knight could only move in 'L' way
     * @param newX destination rank of the piece
     * @param newY destination file of the piece
     * @return whether this step is a valid move
     */
    @Override
    public boolean isValidMove(int newX, int newY) {
        if (newX < 0 || newX > 7 || newY < 0 || newY > 7
                || x == newX || y == newY) {
            return false;
        }

        return ((Math.abs(newX - x) == 2 && Math.abs(newY - y) == 1)
                || (Math.abs(newX - x) == 1 && Math.abs(newY - y) == 2) );
    }

    /**
     * store the passing positions
     * a helper function for later check leap over other pieces
     * @param newX destination rank of the piece
     * @param newY destination file of the piece
     * @return knight can leap over other pieces, so return an empty list
     */
    @Override
    public List<int[]> moving(int newX, int newY) {
        return new ArrayList<>(); //empty
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
