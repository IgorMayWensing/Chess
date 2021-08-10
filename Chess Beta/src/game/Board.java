package game;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Board extends JFrame implements ActionListener, MouseListener, Serializable {

    private Piece[][] board;
    private Button[][] button;
    private Button face;
    private int x, y;
    private boolean turn = true;

    public Board() {
        super("Chess beta 0.8v");
        board = new Piece[8][8];
        setSize(700, 700);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.startGame();
        JPanel mainPanel = new JPanel(new GridLayout(8, 8));
        button = new Button[8][8];
        
        for (int i = 0;i < 8;i++) {
            for (int j = 0;j < 8;j++) {
                button[i][j] = new Button(i, j);
                mainPanel.add(button[i][j]);
                button[i][j].addActionListener(this);
                button[i][j].addMouseListener(this);
            }
        }
        
        for (int i = 0;i < 8;i++) {
            for (int j = 0;j < 8;j++) {
                if (i%2==0) {
                    if (j%2==0) {
                        button[i][j].setBackground(new Color(97, 151, 206));
                    }else{
                        button[i][j].setBackground(new Color(1, 82, 163));
                    }
                }else{
                    if (j%2==0) {
                        button[i][j].setBackground(new Color(1, 82, 163));
                    }else{
                        button[i][j].setBackground(new Color(97, 151, 206));
                    }
                }
            }
        }
        
        for (int i = 0;i < 8;i++) {
            for (int j = 0;j < 8;j++) {
                if (board[i][j] != null) {
                	button[i][j].setIcon(board[i][j].getImgIcon());
                }
            }
        }
     
        add(mainPanel);
        setVisible(true);
    }

    public void startGame() {
        board[0][0] = new Rook(false);
        board[0][1] = new Knight(false);
        board[0][2] = new Bishop(false);
        board[0][3] = new Queen(false);
        board[0][4] = new King(false);
        board[0][5] = new Bishop(false);
        board[0][6] = new Knight(false);
        board[0][7] = new Rook(false);
        
        board[7][0] = new Rook(true);
        board[7][1] = new Knight(true);
        board[7][2] = new Bishop(true);
        board[7][3] = new Queen(true);
        board[7][4] = new King(true);
        board[7][5] = new Bishop(true);
        board[7][6] = new Knight(true);
        board[7][7] = new Rook(true);

        for (int j = 0; j < 8; j++) {
            board[1][j] = new Pawn(false);
        }

        for (int j = 0; j < 8; j++) {
            board[6][j] = new Pawn(true);
        }
    }
   
    @Override
    public void actionPerformed(ActionEvent e) {
    	int i = ((Button) e.getSource()).getI();
    	int j = ((Button) e.getSource()).getJ();
    	int[][] possibility = new int[27][2];
    	int a = -1;
    	int b = -1;
    	
    	for (int cont = 0; cont < 27; cont++) {
    		possibility[cont][0] = -1;
    		possibility[cont][1] = -1;
    	}
        	
    	if (face == null && board[i][j] != null) {
    		if (board[i][j].isWhite() == turn) {
    			face = (Button) e.getSource();
	            x = i;
	            y = j;
	            
	            possibility = board[i][j].move(board, i, j);
	            
	            int m = 0;
	            int n = 0;
	       
	            
	            for (int cont = 0; possibility[cont][0] >= 0; cont++) {
	            	if(board[possibility[cont][0]][possibility[cont][1]] == null ) {
	            		button[possibility[cont][0]][possibility[cont][1]].setBackground(new Color(88, 163, 81));
	            	}else {
	            		button[possibility[cont][0]][possibility[cont][1]].setBackground(new Color(255, 36, 0));
	            	}
	            		
		        }
	            
	            turn = !turn;	            
    		}
	            
        }else{
        	if(face != null) {
        		
	           	if ((button[i][j].getBackground().getRed() == 88 && button[i][j].getBackground().getGreen() == 163 && button[i][j].getBackground().getBlue() == 81)|| (button[i][j].getBackground().getRed() == 255 && button[i][j].getBackground().getGreen() == 36 && button[i][j].getBackground().getBlue() == 0)) {
	           		((Button)e.getSource()).setIcon(face.getIcon());
			        face.setIcon(new ImageIcon());
			        board[i][j] = board[x][y];     
			        			        
			        if(board[i][j].check(board, i, j)) {
			        	JOptionPane.showMessageDialog(this, "Check!");
			        }
			        
			        
			        
			        
			        board[x][y] = null;
			        face = null;
			            
			        for (int m = 0;m < 8;m++) {
			            for (int n = 0;n < 8;n++) {
			                if (m%2==0) {
			                    if (n%2==0) {
			                        button[m][n].setBackground(new Color(97, 151, 206));
			                    }else{
			                        button[m][n].setBackground(new Color(1, 82, 163));
			                    }
			                }else{
			                    if (n%2==0) {
			                        button[m][n].setBackground(new Color(1, 82, 163));
			                    }else{
			                        button[m][n].setBackground(new Color(97, 151, 206));
			                    }
			                }
			            }
			        }
			         
	           	}	
	         
            }
	        	
        }
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
    	if (e.getButton() == MouseEvent.BUTTON3 && face != null) {
    		face = null;
    		turn = !turn;
    		if (board[x][y] instanceof Pawn) {
    			((Pawn) board[x][y]).setFirstMove();
    		}
    		for (int m = 0;m < 8;m++) {
	            for (int n = 0;n < 8;n++) {
	                if (m%2==0) {
	                    if (n%2==0) {
	                        button[m][n].setBackground(new Color(97, 151, 206));
	                    }else{
	                        button[m][n].setBackground(new Color(1, 82, 163));
	                    }
	                }else{
	                    if (n%2==0) {
	                        button[m][n].setBackground(new Color(1, 82, 163));
	                    }else{
	                        button[m][n].setBackground(new Color(97, 151, 206));
	                    }
	                }
	            }
	        }
    	}
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    	
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
    	
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
    	
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
    	
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
