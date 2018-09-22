package pieces;

import game.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Bishop class
 * <p>
 * The bishop can move any number of squares diagonally,
 * but may not leap over other pieces.
 */
public class Bishop implements Pieces {

    /** rank position of the piece */
    private int x;

    /** file position of the piece */
    private int y;

    /** the player piece belongs to */
    public Player player;

    /** the type this piece is */
    public Type type =  Type.Bishop;

    /**
     * initialize bishop attributes
     * @param x the initial rank position
     * @param y the initial file position
     * @param player the player piece belong to
     */
    public Bishop(int x, int y, Player player) {
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
     * check whether bishop is moved by its own rule;
     * bishop could only move diagonally
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

        return (Math.abs(x - newX) == Math.abs(y - newY));
    }

    /**
     * store the passing positions
     * a helper function for later check leap over other pieces
     * @param newX destination rank of the piece
     * @param newY destination file of the piece
     * @return a list of squares that the piece passing
     */
    @Override
    public List<int[]> moving(int newX, int newY) {
        if (!isValidMove(newX, newY)) {
            return null;
        }

        // go in which direction
        boolean addX = false;
        boolean addY = false;

        if (newX > x) {
            addX = true;
        }

        if (newY > y) {
            addY = true;
        }

        int currX = x;
        int currY = y;

        List<int[]> steps = new ArrayList<>();

        while (currX != newX && currY != newY) {
            if (addX) {
                currX++;
            } else {
                currX--;
            }

            if (addY) {
                currY++;
            } else {
                currY--;
            }
            steps.add(new int[]{currX, currY});
        }
        steps.remove(steps.size() - 1); // remove the destination position
        return steps;
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
