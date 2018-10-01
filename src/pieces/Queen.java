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
public class Queen extends Pieces {

    /**
     * initialize queen attributes
     * @param x the initial rank position
     * @param y the initial file position
     * @param player the player piece belong to
     */
    public Queen(int x, int y, Player player) {
        super(x, y, player);
        type = Type.Queen;
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
        return Math.abs(x - newX) == Math.abs(y - newY)
                || x == newX || y == newY;
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

}
