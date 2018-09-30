package GUI;

import game.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        restart.addActionListener(new RestartAction());

        JButton undo = new JButton();
        undo.setText("Undo");
        undo.addActionListener(new UndoAction());

        JButton forfeit = new JButton();
        forfeit.setText("Forfeit");
        forfeit.addActionListener(new ForfeitAction());

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

        Font myFont = new Font("Serif", Font.BOLD, 18);
        player1Score.setFont(myFont);
        player0Score.setFont(myFont);
        this.add(player0Score);
        this.add(player1Score);
    }

    public void updateScore() {
        player0Score.setText("Player " + game.player0.name + " Score : " + game.player0.score);
        player1Score.setText("Player " + game.player1.name + " Score : " + game.player1.score);
    }

    private class RestartAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            int val0 = JOptionPane.showConfirmDialog(null,
                    game.player0.name + " : Are you sure to restart a game?",
                    "Choose",
                    JOptionPane.YES_NO_OPTION);

            int val1 = JOptionPane.showConfirmDialog(null,
                    game.player1.name + " : Are you sure to restart a game?",
                    "Choose",
                    JOptionPane.YES_NO_OPTION);

            if ((val0 == JOptionPane.YES_OPTION) && (val1 == JOptionPane.YES_OPTION)) {
                game.startNewGame();
            } else {
                JOptionPane.showMessageDialog(null, "Fail to restart a new game");
            }
        }
    }

    private class ForfeitAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int val0 = JOptionPane.showConfirmDialog(null,
                    game.gameGUI.chessBoard.presentPlayer.name + " : Are you sure to forfeit this game?",
                    "Choose",
                    JOptionPane.YES_NO_OPTION);

            if (val0 == JOptionPane.YES_OPTION) {
                game.gameGUI.chessBoard.oppPlayer.score += 5;
                game.gameGUI.gameOperation.updateScore();
                game.startNewGame();
            }
        }
    }

    private class UndoAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int val0 = JOptionPane.showConfirmDialog(null,
                    game.gameGUI.chessBoard.presentPlayer.name + " : Are you sure to undo your last move?",
                    "Choose",
                    JOptionPane.YES_NO_OPTION);
            if (val0 == JOptionPane.YES_OPTION) {
                Player presentPlayer = game.gameGUI.chessBoard.presentPlayer;
                boolean succeed = game.playBoard.undo(presentPlayer);
                if (!succeed) {
                    JOptionPane.showMessageDialog(null, "Undo fail");
                }
            }
        }
    }
}
