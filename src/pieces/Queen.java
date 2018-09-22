package pieces;

import game.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Queen class
 * <p>
 * The queen combines the power of the rook and bishop
 * and can move any number of squares along rank, file, or diagonal,
 * but it may not leap over other pieces.
 */
public class Queen implements Pieces {

    /** rank position of the piece */
    private int x;

    /** file position of the piece */
    private int y;

    /** the player piece belongs to */
    public Player player;

    /** the type this piece is */
    public Type type = Type.Queen;

    /**
     * initialize queen attributes
     * @param x the initial rank position
     * @param y the initial file position
     * @param player the player piece belong to
     */
    public Queen(int x, int y, Player player) {
        this.player = player;
        this.x = x;
        this.y = y;
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
     * check whether queen is moved by its own rule;
     * queen could move in all eight directions
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
        if (Math.abs(x - newX) == Math.abs(y - newY)
                || x == newX || y == newY) {
            return true;
        }
        return false;
    }

    /**
     * store the passing positions
     * a helper function for later check leap over other pieces
     * since Queen combines rules of Rook and Bishop, apply methods in both classes
     * @param newX destination rank of the piece
     * @param newY destination file of the piece
     * @return the positions that queen has leaped over
     */
    @Override
    public List<int[]> moving(int newX, int newY) {
        List<int[]> steps = new ArrayList<>();
        if (Math.abs(x - newX) == Math.abs(y - newY)) { //diagonal
            Bishop b = new Bishop(x, y, player);
            steps.addAll(b.moving(newX, newY));
        } else {
            Rook r = new Rook(x, y, player);
            steps.addAll(r.moving(newX, newY));
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
