package pieces;

import game.Player;

import java.util.List;

/**
 * Hopper class
 * <p>
 * Hopper moves by jumping over its direct piece,
 * Hopper only jump to its forward, backward, left and right direction,
 * Hopper can only jump once each time.
 */
public class Hopper implements Pieces{

    /** rank position of the piece */
    private int x;

    /** file position of the piece */
    private int y;

    /** the player piece belongs to */
    public Player player;

    /** the type this piece is */
    public Type type =  Type.Hopper;

    /**
     * initialize hopper attributes
     * @param x the initial rank position
     * @param y the initial file position
     * @param player the player piece belong to
     */
    public Hopper(int x, int y, Player player) {
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
     * check whether hopper is moved by its own rule,
     * hopper can at most move two squares each time
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
        return (x == newX && Math.abs(newY - y) == 2)
                || (y == newY && Math.abs(x - newX) == 2);
    }

    /**
     * store the passing positions
     * a helper function for later check leap over other pieces
     * @param newX destination rank of the piece
     * @param newY destination file of the piece
     * @return a null array, since hopper must leap over one piece
     */
    @Override
    public List<int[]> moving(int newX, int newY) {
        return null;
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
