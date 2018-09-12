package pieces;

import game.Player;

import java.util.ArrayList;
import java.util.List;

public class Pawn implements Pieces {

    public int x;
    public int y;
    public Player player;
    public Type type = Type.Pawn;

    private boolean firstStep = true;

    public Pawn(int x, int y, Player player) {
        this.x = x;
        this.y = y;
        this.player = player;
    }


    @Override
    public int[] getPosition() {
        return new int[]{this.x, this.y};
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public boolean isValidMove(int newX, int newY) {  // not include attack piece
        if (newX < 0 || newX > 7 || newY < 0 || newY > 7
                || (x == newX && y == newY)) {
            return false;
        }

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

    @Override
    public List<int[]> moving(int newX, int newY) {
        List<int[]> steps = new ArrayList<>();
        if (Math.abs(newY - y) == 2)  {
            steps.add(new int[]{x, (newY + y) / 2});
        }
        return steps;
    }

    @Override
    public void move(int newX, int newY) {
        x = newX;
        y = newY;
        firstStep = false;
    }

}
