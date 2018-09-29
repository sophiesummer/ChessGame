package GUI;
import game.*;
import pieces.Pieces;

import javax.swing.*;
import javax.swing.plaf.basic.BasicOptionPaneUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Deque;
import java.util.LinkedList;


public class BoardGUI extends JPanel {
    private Dimension boardDimension = new Dimension(640, 640);
    private Dimension pieceDimension = new Dimension(80, 80);
    public Button[][] grids;
    public boolean setCustomPiece;
    Button selectedBtn;
    private Game game;
    private Deque<Pieces> stack = new LinkedList<>();


    public BoardGUI(Game game) {
        super(new GridLayout(8, 8));
        setBackground(Color.white);
        this.game = game;
        setPreferredSize(boardDimension);
        setLocation(0, 0);
        this.setCustomPiece = game.addCustomPiece;
        grids = new Button[8][8];
        setPieceInitial(setCustomPiece);
        selectedBtn = null;
    }

    public void setPieceInitial(boolean setCustomPiece) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                GridBagConstraints gbc = new GridBagConstraints();
                Button btn = new Button(j, i);
                grids[j][i] = btn;

                gbc.gridx = j;
                gbc.gridy = i;

                ImageIcon icon = getImageIcon(i, j, setCustomPiece);
                if (icon != null) {
                    btn.setIcon(icon);
                }

                btn.setPreferredSize(pieceDimension);
                btn.setBorderPainted(false);
                btn.setVisible(true);
                btn.setOpaque(true);
                //btn.addActionListener();

                setBtnBackgroundColor(btn);
                add(btn, gbc);
                btn.addActionListener(new ButtonAction());
            }
        }
    }

    /**
     * decide the button's original background is pink or white
     * @param btn
     */
    private void setBtnBackgroundColor(Button btn) {
        if ((btn.x + btn.y) % 2 == 0) {
            btn.setBackground(Color.white);
        } else {
            btn.setBackground(Color.pink);
        }
    }

    class ButtonAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Button btn = (Button)e.getSource();
            if (selectedBtn == null && btn.getIcon() == null) {
                JOptionPane.showMessageDialog(null, "Invalid movement! Please try another move.");
                return;
            }
            if (btn.getIcon() != null && selectedBtn == null) {
                selectedBtn = btn;
                btn.setBackground(Color.lightGray);
            } else {
                if (checkValidation(selectedBtn, btn)) {
                    executeMovement(selectedBtn, btn);
                    selectedBtn = null;
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid movement! Please try another move.");
                    setBtnBackgroundColor(selectedBtn);
                    selectedBtn = null;
                }
            }
        }
    }

    private boolean checkValidation(Button prevBtn, Button newBtn) {
        Player present = game.playBoard.board[prevBtn.x][prevBtn.y].getPlayer();
        return game.playBoard.checkValid(present, prevBtn.x, prevBtn.y, newBtn.x, newBtn.y);
    }

    /**
     * after check validation, just execute movement in this function
     * @param prevBtn
     * @param newBtn
     */
    private void executeMovement(Button prevBtn, Button newBtn) {
        if (newBtn.getIcon() != null) {
            // store ;

        }
        newBtn.setIcon(prevBtn.getIcon());
        prevBtn.setIcon(null);
        Player present = game.playBoard.board[prevBtn.x][prevBtn.y].getPlayer();
        game.playBoard.putPieces(present, prevBtn.x, prevBtn.y, newBtn.x, newBtn.y);  //forfeit
        setBtnBackgroundColor(prevBtn);
        setBtnBackgroundColor(newBtn);
    }

    private ImageIcon getImageIcon(int i, int j, boolean setCustomPiece) {
        if (i == 1) {
            return new ImageIcon("img/black_pawn.gif");
        }

        if (i == 6) {
            return new ImageIcon("img/white_pawn.gif");
        }

        if (i == 0 && (j == 0 || j == 7)) {
            return new ImageIcon("img/black_rook.gif");
        }

        if (i == 0 && j == 6) {
            return new ImageIcon("img/black_knight.gif");
        }

        if (i == 0 && j == 1) {
            if (!setCustomPiece) {
                return new ImageIcon("img/black_knight.gif");
            } else {
                return new ImageIcon("img/black_hopper.png");
            }
        }

        if (i == 0 && j == 2) {
            return new ImageIcon("img/black_bishop.gif");
        }

        if (i == 0 && j == 5) {
            if (!setCustomPiece) {
                return new ImageIcon("img/black_bishop.gif");
            } else {
                return new ImageIcon("img/black_wizard.png");
            }
        }

        if (i == 0 && j == 3) {
            return new ImageIcon("img/black_queen.gif");
        }

        if (i == 0 && j == 4) {
            return new ImageIcon("img/black_king.gif");
        }

        if (i == 7 && (j == 0 || j == 7)) {
            return new ImageIcon("img/white_rook.gif");
        }

        if (i == 7 && j == 6) {
            return new ImageIcon("img/white_knight.gif");
        }

        if (i == 7 && j == 1) {
            if (!setCustomPiece) {
                return new ImageIcon("img/white_knight.gif");
            } else {
                return new ImageIcon("img/white_hopper.png");
            }
        }

        if (i == 7 && j == 2) {
            return new ImageIcon("img/white_bishop.gif");
        }

        if (i == 7 && j == 5) {
            if (!setCustomPiece) {
                return new ImageIcon("img/white_bishop.gif");
            } else {
                return new ImageIcon("img/white_wizard.png");
            }
        }

        if (i == 7 && j == 3) {
            return new ImageIcon("img/white_queen.gif");
        }

        if (i == 7 && j == 4) {
            return new ImageIcon("img/white_king.gif");
        }
        return null;
    }
}
