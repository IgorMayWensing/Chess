package game;

import java.awt.Image;

import javax.swing.ImageIcon;

public class King extends Piece {
    ImageIcon imgIcon;
	
    public King(boolean isWhite) {
        super(isWhite);
        if (isWhite) {
        	Image print = new ImageIcon("C:\\Users\\Igor\\eclipse-workspace\\Chess Beta\\src\\game\\whiteKing.png").getImage();
            Image img = print.getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH);
            imgIcon = new ImageIcon(img);
        }else {
        	Image print = new ImageIcon("C:\\Users\\Igor\\eclipse-workspace\\Chess Beta\\src\\game\\blackKing.png").getImage();
            Image img = print.getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH);
            imgIcon = new ImageIcon(img);
        }
    }

    @Override
    public int[][] move(Piece[][] board, int i, int j) {
    	int[][] possibility = new int[27][2];
    	int cont = 0;

		for (int c = 0; c < 27; c++) {
			possibility[c][0] = -1;
			possibility[c][1] = -1;
		}

		if (i <7) {
			if (j < 7) {
				if (board[i][j + 1] == null || isWhite() != board[i][j + 1].isWhite()) {
					possibility[cont][0] = i ;
					possibility[cont++][1] = j + 1;
				}
				if (board[i+1][j + 1] == null || isWhite() != board[i+1][j + 1].isWhite()) {
					possibility[cont][0] = i +1;
					possibility[cont++][1] = j + 1;
				}
				if (board[i + 1][j] == null || isWhite() != board[i + 1][j ].isWhite()) {
					possibility[cont][0] = i + 1 ;
					possibility[cont++][1] = j;
				}
			}
			if (j > 0) {
				if (board[i][j - 1] == null || isWhite() != board[i][j -1].isWhite()) {
					possibility[cont][0] = i ;
					possibility[cont++][1] = j - 1;
				}
				if (board[i+1][j - 1] == null || isWhite() != board[i+1][j - 1].isWhite()) {
					possibility[cont][0] = i +1;
					possibility[cont++][1] = j - 1;
				}
				if (board[i + 1][j] == null || isWhite() != board[i + 1][j ].isWhite()) {
					possibility[cont][0] = i + 1 ;
					possibility[cont++][1] = j;
				}
			}
		}
		
		if (i >0) {
			if (j < 7) {
				if (board[i][j + 1] == null || isWhite() != board[i][j + 1].isWhite()) {
					possibility[cont][0] = i ;
					possibility[cont++][1] = j + 1;
				}
				if (board[i-1][j + 1] == null || isWhite() != board[i-1][j + 1].isWhite()) {
					possibility[cont][0] = i -1;
					possibility[cont++][1] = j + 1;
				}
				if (board[i - 1][j] == null || isWhite() != board[i - 1][j ].isWhite()) {
					possibility[cont][0] = i - 1 ;
					possibility[cont++][1] = j;
				}
			}
			if (j > 0) {
				if (board[i][j - 1] == null || isWhite() != board[i][j -1].isWhite()) {
					possibility[cont][0] = i ;
					possibility[cont++][1] = j - 1;
				}
				if (board[i-1][j - 1] == null || isWhite() != board[i-1][j - 1].isWhite()) {
					possibility[cont][0] = i -1;
					possibility[cont++][1] = j - 1;
				}
				if (board[i - 1][j] == null || isWhite() != board[i - 1][j ].isWhite()) {
					possibility[cont][0] = i - 1 ;
					possibility[cont++][1] = j;
				}
			}
		}
		
    	return possibility;
    }

	@Override
    public ImageIcon getImgIcon() {
		return imgIcon;
	}  
	
	@Override
	public boolean check(Piece[][] board,int i,int j) {		
		return false;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
