package pieces;

import game.Player;

import java.util.ArrayList;
import java.util.List;

public class Rook implements Pieces {

    public int x;
    public int y;
    public Player player;
    public Type type = Type.Rook;

    public Rook(int x, int y, Player player) {
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
    public boolean isValidMove(int newX, int newY) {
        if (newX < 0 || newX > 7 || newY < 0 || newY > 7
                || (x == newX && y == newY)) {
            return false;
        }

        if (newX == x || newY == y) {
            return true;
        }

        return false;
    }

    @Override
    public List<int[]> moving(int newX, int newY) {
        List<int[]> steps = new ArrayList<>();

        int currX = x;
        int currY = y;

        if (x == newX) {
            if (newY > y) {
                while (currY != newY) {
                    currY++;
                    steps.add(new int[]{x, currY});
                }
            } else {
                while (currY != newY) {
                    currY--;
                    steps.add(new int[]{x, currY});
                }
            }
        } else if (y == newY) {
            if (newX > x) {
                while (currX != newX) {
                    currX++;
                    steps.add(new int[]{currX, y});
                }
            } else {
                while (currX != newX) {
                    currX--;
                    steps.add(new int[]{currX, y});
                }
            }
        }
        return steps;
    }

    @Override
    public void move(int newX, int newY) {
        x = newX;
        y = newY;
    }
}