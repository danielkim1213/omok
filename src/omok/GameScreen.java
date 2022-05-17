/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package omok;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author kyw40
 */
public class GameScreen extends javax.swing.JFrame {

    StartScreen ss;
    private JLabel[][] lblSpace;
    private ImageIcon black, white, preBlack, preWhite;
    private int[] coord = new int[2];
    private char[][] spaceColor = new char[19][19];
    private char turn = 'b';
    boolean gameOver = false;
    
    /**
     * Creates new form GameScreen
     */
    public GameScreen(StartScreen ss) {
        initComponents();
        this.ss = ss;
        makeSpaces();
        stoneImages();
    }
    
    private void makeSpaces()
    {
        lblSpace = new JLabel[19][19];
        for(int i=0; i<19; i++)
        {
            for(int k=0; k<19; k++)
            {
                lblSpace[i][k] = new JLabel();
                jPanel1.add(lblSpace[i][k], new org.netbeans.lib.awtextra.AbsoluteConstraints((int)(8+36.37*k), (int)(8+36.37*i), 30, 30));
            }
        }
        
        JLabel lblBackground = new JLabel();
        lblBackground.setIcon(new ImageIcon(getClass().getResource("/omok/Images/Blank_Go_board.png"))); 
        lblBackground.setOpaque(true);
        jPanel1.add(lblBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
    }
    
    private void stoneImages()
    {
        BufferedImage blackStone;
        BufferedImage whiteStone;
        BufferedImage preBlackStone;
        BufferedImage preWhiteStone;
        URL url0 = GameScreen.class.getResource("Images/black.png");
        URL url1 = GameScreen.class.getResource("Images/white.png");
        URL url2 = GameScreen.class.getResource("Images/preBlack.png");
        URL url3 = GameScreen.class.getResource("Images/preWhite.png");
        try {
            blackStone = ImageIO.read(url0);
            black = new ImageIcon(blackStone.getScaledInstance(30, 30, Image.SCALE_SMOOTH));
            whiteStone = ImageIO.read(url1);
            white = new ImageIcon(whiteStone.getScaledInstance(30, 30, Image.SCALE_SMOOTH));
            preBlackStone = ImageIO.read(url2);
            preBlack = new ImageIcon(preBlackStone.getScaledInstance(30, 30, Image.SCALE_SMOOTH));
            preWhiteStone = ImageIO.read(url3);
            preWhite = new ImageIcon(preWhiteStone.getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        } catch (IOException ex) {
            System.out.println(ex);
        }
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnMainMenu = new javax.swing.JButton();
        lblLocation = new javax.swing.JLabel();
        lblWin = new javax.swing.JLabel();
        btnQuit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1280, 720));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.CROSSHAIR_CURSOR));
        jPanel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jPanel1MouseMoved(evt);
            }
        });
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 700, 700));

        btnMainMenu.setText("Main Menu");
        btnMainMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMainMenuActionPerformed(evt);
            }
        });
        getContentPane().add(btnMainMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 10, -1, -1));

        lblLocation.setText("jLabel1");
        getContentPane().add(lblLocation, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 110, -1, -1));

        lblWin.setText("jLabel1");
        getContentPane().add(lblWin, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 180, -1, -1));

        btnQuit.setText("Quit");
        btnQuit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitActionPerformed(evt);
            }
        });
        getContentPane().add(btnQuit, new org.netbeans.lib.awtextra.AbsoluteConstraints(1173, 50, 80, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnMainMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMainMenuActionPerformed
        ss.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnMainMenuActionPerformed

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
        if(turn == 'b')
        {
            int x = (int)((evt.getX()-4)/36.37);
            int y = (int)((evt.getY()-4)/36.37);

            x = rounding(x);
            y = rounding(y);

            boolean isOmok = false;

            if(spaceColor[y][x] == 0)
            {
                spaceColor[y][x] = 'b';
                lblSpace[y][x].setIcon(black);
                if(checkOmok(y, x))
                {
                    lblWin.setText("You Win!");
                }
                turn = 'w';
                AI_Movement();
            }
        }
    }//GEN-LAST:event_jPanel1MouseClicked

    private void AI_Movement()
    {
        
        turn = 'b';
    }
    
    private void jPanel1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseMoved
        int x = (int)((evt.getX()-4)/36.37);
        int y = (int)((evt.getY()-4)/36.37);
        
        x = rounding(x);
        y = rounding(y);
        
        lblLocation.setText(x + ", " + y);
        
        if(spaceColor[coord[1]][coord[0]] == 0)
        {
            lblSpace[coord[1]][coord[0]].setIcon(null);
        }
        if(spaceColor[y][x] == 0)
        {
                lblSpace[y][x].setIcon(preBlack);
        }
        coord[0] = x;
        coord[1] = y;
    }//GEN-LAST:event_jPanel1MouseMoved

    private void btnQuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnQuitActionPerformed

    private boolean checkOmok(int row, int col)
    {
        int countX = count(row, col, turn, new int[]{0, -1}, 0) + count(row, col, turn, new int[]{0, 1}, 0);
        int countY = count(row, col, turn, new int[]{-1, 0}, 0) + count(row, col, turn, new int[]{1, 0}, 0);
        int countNegative = count(row, col, turn, new int[]{-1, -1}, 0) + count(row, col, turn, new int[]{1, 1}, 0);
        int countPositive = count(row, col, turn, new int[]{1, -1}, 0) + count(row, col, turn, new int[]{-1, 1}, 0);
        
        if(countX == 4 || countY == 4 || countNegative == 4 || countPositive == 4)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    private int count(int row, int col, char color, int[] direction, int depth)
    {
        row += direction[0];
        col += direction[1];
        
        if(row < 0 || row > 18 || col< 0 || col > 18)
        {
            return depth;
        }
        
        if(spaceColor[row][col] != color)
        {
            return depth;
        }
        else
        {
            return count(row, col, color, direction, depth+1);
        }
    }

    private int rounding(int a)
    {
        if(a > 18)
        {
            a = 18;
        }
        return a;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMainMenu;
    private javax.swing.JButton btnQuit;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblLocation;
    private javax.swing.JLabel lblWin;
    // End of variables declaration//GEN-END:variables
}
