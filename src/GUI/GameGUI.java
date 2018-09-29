package GUI;

import game.Game;
import game.Player;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class GameGUI {

    private JFrame window;
    private BoardGUI chessBoard;
    public boolean customize;
    public Game presentGame;
    public Player player0;
    public Player player1;

    public GameGUI(Game game) {
        window = new JFrame("MY Chess Game");
        window.setResizable(false);
        window.setLayout(new BorderLayout());

        window.setLocation(100, 100);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(new Dimension(640, 720));
        //setUpMenu(window);
        presentGame = game;

        playerSettings();
        presentGame.player0 = player0;
        presentGame.player1 = player1;
        gameSettings();
        presentGame.addCustomPiece = customize;

        chessBoard = new BoardGUI(presentGame); // user input to choose
        window.add(chessBoard, BorderLayout.CENTER);

        window.add(new GamePanel(presentGame), BorderLayout.NORTH);
        window.setVisible(true); // set lastly
    }

    private void setUpMenu(JFrame window) {
        JMenuBar menubar = new JMenuBar();

        JMenu game = new JMenu("Game");
        JMenuItem player = new JMenuItem("Choose Player");
        JMenuItem restart = new JMenuItem("Start a new Game");
        JMenuItem customPiece = new JMenuItem("Add Custom Pieces");
        game.add(player);
        game.add(restart);
        game.add(customPiece);

        menubar.add(game);
        window.setJMenuBar(menubar);
    }

    private void playerSettings() {
        String player0Name = "";
        while (player0Name == null || player0Name.equals("")) {
            player0Name = JOptionPane.showInputDialog("Enter your name: ");
        }

        String player0Color = "";
        while (!player0Color.equalsIgnoreCase("w") && !player0Color.equalsIgnoreCase("b")) {
            player0Color = JOptionPane.showInputDialog("Choose your pieces color. Enter 'w' for White, 'b' for Black: ");
        }

        String player1Name = "";
        while (player1Name == null || player1Name.equals("") || player1Name.equals(player0Name)) {
            player1Name = JOptionPane.showInputDialog("Enter your name: ");
        }

        if (player0Color.equalsIgnoreCase("w")) {
            player0 = new Player(0, player1Name);
            player1 = new Player(1, player0Name);
        } else {
            player0 = new Player(0, player0Name);
            player1 = new Player(1, player1Name);
        }
    }

    private void gameSettings() {
        int selectedOption = JOptionPane.showConfirmDialog(null,
                "Do you wanna add custom pieces?",
                "Choose",
                JOptionPane.YES_NO_OPTION);
        if (selectedOption == JOptionPane.YES_OPTION) {
            customize = true;
        } else {
            customize = false;
        }
    }

}
