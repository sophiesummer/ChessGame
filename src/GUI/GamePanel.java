package GUI;

import game.Game;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private JLabel player0Score;
    private JLabel player1Score;
    public Game game;

    public GamePanel(Game game) {
        super(new GridLayout(1, 2));
        setBackground(Color.lightGray);
        setPreferredSize(new Dimension(640, 80));


        player0Score = new JLabel();
        player0Score.setText("Player black score is ");
//        player0Score.setText("Player " + game.player0.name + "'s Score : " + game.player0.score);
        player1Score = new JLabel();
//        player1Score.setText("Player " + game.player1.name + "'s Score : " + game.player1.score);
        player1Score.setText("Player white score is ");

        Font myFont = new Font("Serif", Font.BOLD, 20);
        player1Score.setFont(myFont);
        player0Score.setFont(myFont);

        this.add(player0Score);
        this.add(player1Score);
        this.setBackground(Color.PINK);
    }
    

}
