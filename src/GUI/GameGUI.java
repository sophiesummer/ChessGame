package GUI;

import game.Game;
import game.Player;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class GameGUI {

    private JFrame window;
    public BoardGUI chessBoard;
    public Game presentGame;
    public Player player0;
    public Player player1;
    public GamePanel gameOperation;

    public GameGUI(Game game) {
        window = new JFrame("MY Chess Game");
        window.setResizable(false);
        window.setLayout(new BorderLayout());

        window.setLocation(100, 100);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(new Dimension(640, 720));
        presentGame = game;

        playerSettings();
        presentGame.player0 = player0;
        presentGame.player1 = player1;
        chessBoard = new BoardGUI(presentGame); // user input to choose
        window.add(chessBoard, BorderLayout.CENTER);
        gameOperation = new GamePanel(presentGame);
        window.add(gameOperation, BorderLayout.NORTH);
        window.setVisible(true); // set lastly
    }

    public void updateGameGUI(BoardGUI newBoard) {
        window.getContentPane().removeAll();
        chessBoard = newBoard;
        window.add(newBoard);
        window.add(gameOperation, BorderLayout.NORTH);
        window.setVisible(true);
        window.repaint();
    }


    private void playerSettings() {
        String player0Name = "";
        while (player0Name == null || player0Name.equals("")) {
            player0Name = JOptionPane.showInputDialog("Enter your name: ");
        }

        String[] color = {"White", "Black"};
        int c = JOptionPane.showOptionDialog(null, "Choose your pieces color",
                "Choose a color", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
                color, color[0]);

        String player1Name = "";
        while (player1Name == null || player1Name.equals("") || player1Name.equals(player0Name)) {
            player1Name = JOptionPane.showInputDialog("Enter your name: ");
        }

        if (c == 0) {
            player0 = new Player(0, player1Name);
            player1 = new Player(1, player0Name);
        } else {
            player0 = new Player(0, player0Name);
            player1 = new Player(1, player1Name);
        }
    }

}
