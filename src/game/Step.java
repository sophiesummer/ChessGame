package game;

import pieces.Pieces;

import javax.swing.*;

public class Step {
    public Icon imageIconi;
    public Icon imageIconr;
    public Pieces moving;
    public int[] prevPos;
    public int[] newPos;
    public Pieces removed;
    public boolean firstStep;



    public Step(Icon i, Icon r, Pieces moving, Pieces removed, int[] prevPos, int[] newPos, boolean firstStep) {
        this.moving = moving;
        this.removed = removed;
        imageIconi = i;
        imageIconr = r;
        this.prevPos = prevPos;
        this.newPos = newPos;
    }

    public Player movingPlayer() {
        return moving.getPlayer();
    }

}
