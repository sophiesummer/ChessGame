package GUI;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;


public class BoardGUI extends JPanel {
    private Dimension boardDimension = new Dimension(640, 640);
    private Dimension pieceDimension = new Dimension(80, 80);
    public Button[][] grids;


    public BoardGUI() {
        super(new GridLayout(8, 8));
        setBackground(Color.white);
        setPreferredSize(boardDimension);
        setLocation(0, 0);

        grids = new Button[8][8];
        setPieceInitial();
    }

    public void setPieceInitial() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                GridBagConstraints gbc = new GridBagConstraints();
                Button btn = new Button(j, i);
                grids[j][i] = btn;

                gbc.gridx = j;
                gbc.gridy = i;

                btn.setVisible(true);

                if (i == 1) {
                    btn.setIcon(new ImageIcon("img/black_pawn.gif"));

                }
                if (i == 6) {
                    btn.setIcon(new ImageIcon("img/white_pawn.gif"));
                }

                if (i == 0 && (j == 0 || j == 7)) {
                    btn.setIcon(new ImageIcon("img/black_rook.gif"));
                }
                if (i == 0 && (j == 1 || j == 6)) {
                    btn.setIcon(new ImageIcon("img/black_knight.gif"));
                }

                if (i == 0 && (j == 2 || j == 5)) {
                    btn.setIcon(new ImageIcon("img/black_bishop.gif"));
                }

                if (i == 0 && j == 3) {
                    btn.setIcon(new ImageIcon("img/black_queen.gif"));
                }

                if (i == 0 && j == 4) {
                    btn.setIcon(new ImageIcon("img/black_king.gif"));
                }

                if (i == 7 && (j == 0 || j == 7)) {
                    btn.setIcon(new ImageIcon("img/white_rook.gif"));
                }
                if (i == 7 && (j == 1 || j == 6)) {
                    btn.setIcon(new ImageIcon("img/white_knight.gif"));
                }

                if (i == 7 && (j == 2 || j == 5)) {
                    btn.setIcon(new ImageIcon("img/white_bishop.gif"));
                }

                if (i == 7 && j == 3) {
                    btn.setIcon(new ImageIcon("img/white_queen.gif"));
                }

                if (i == 7 && j == 4) {
                    btn.setIcon(new ImageIcon("img/white_king.gif"));
                }
                btn.setPreferredSize(pieceDimension);
                btn.setBorderPainted(false);

                btn.setOpaque(true);
                //btn.addActionListener();

                if ((i + j) % 2 == 0) {
                    btn.setBackground(Color.white);
                } else {
                    btn.setBackground(Color.pink);
                }

                add(btn, gbc);
            }
        }

    }
}


