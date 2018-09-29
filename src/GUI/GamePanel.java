package GUI;

import game.Game;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private JLabel player0Score;
    private JLabel player1Score;
    public Game game;

    public GamePanel(Game game) {
        super(new GridLayout(2, 3));
        setBackground(Color.lightGray);
        setPreferredSize(new Dimension(640, 80));

        JButton restart = new JButton();
        restart.setText("Restart");
        JButton undo = new JButton();
        undo.setText("Undo");
        JButton forfeit = new JButton();
        forfeit.setText("Forfeit");
        this.game = game;

        this.add(undo);
        this.add(restart);
        this.add(forfeit);
        setScoreText();
        this.setBackground(Color.PINK);
    }

    public void setScoreText() {
        player0Score = new JLabel();
        player0Score.setText("Player " + game.player0.name + " Score : " + game.player0.score);
        player1Score = new JLabel();
        player1Score.setText("Player " + game.player1.name + " Score : " + game.player1.score);

        Font myFont = new Font("Serif", Font.BOLD, 20);
        player1Score.setFont(myFont);
        player0Score.setFont(myFont);
        this.add(player0Score);
        this.add(player1Score);
    }
}
