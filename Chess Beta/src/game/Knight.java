package game;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Knight extends Piece {
	ImageIcon imgIcon;

	public Knight(boolean isWhite) {
		super(isWhite);
		if (isWhite) {
			Image print = new ImageIcon("C:\\Users\\Igor\\eclipse-workspace\\Chess Beta\\src\\game\\whiteKnight.png")
					.getImage();
			Image img = print.getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH);
			imgIcon = new ImageIcon(img);
		} else {
			Image print = new ImageIcon("C:\\Users\\Igor\\eclipse-workspace\\Chess Beta\\src\\game\\blackKnight.png")
					.getImage();
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

		if (i > 1) {
			if (j < 7) {
				if (board[i - 2][j + 1] == null || isWhite() != board[i - 2][j + 1].isWhite()) {
					possibility[cont][0] = i - 2;
					possibility[cont++][1] = j + 1;
				}
			}
			if (j > 0) {
				if (board[i - 2][j - 1] == null || isWhite() != board[i - 2][j - 1].isWhite()) {
					possibility[cont][0] = i - 2;
					possibility[cont++][1] = j - 1;
				}
			}
		}

		if (i < 6) {
			if (j < 7) {
				if (board[i + 2][j + 1] == null || isWhite() != board[i + 2][j + 1].isWhite()) {
					possibility[cont][0] = i + 2;
					possibility[cont++][1] = j + 1;
				}
			}
			if (j > 0) {
				if (board[i + 2][j - 1] == null || isWhite() != board[i + 2][j - 1].isWhite()) {
					possibility[cont][0] = i + 2;
					possibility[cont++][1] = j - 1;
				}
			}
		}

		if (j > 1) {
			if (i > 0) {
				if (board[i - 1][j - 2] == null || isWhite() != board[i - 1][j - 2].isWhite()) {
					possibility[cont][0] = i - 1;
					possibility[cont++][1] = j - 2;
				}
			}
			if (i < 7) {
				if (board[i + 1][j - 2] == null || isWhite() != board[i + 1][j - 2].isWhite()) {
					possibility[cont][0] = i + 1;
					possibility[cont++][1] = j - 2;
				}
			}
		}

		if (j < 6) {
			if (i > 0) {
				if (board[i - 1][j + 2] == null || isWhite() != board[i - 1][j + 2].isWhite()) {
					possibility[cont][0] = i - 1;
					possibility[cont++][1] = j + 2;
				}
			}
			if (i < 7) {
				if (board[i + 1][j + 2] == null || isWhite() != board[i + 1][j + 2].isWhite()) {
					possibility[cont][0] = i + 1;
					possibility[cont++][1] = j + 2;
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
		int[][] possibility = move(board, i, j);
		
		for( int c = 0; possibility[c][0] >= 0; c++ ) { 
			if(board[possibility[c][0]][possibility[c][1]] instanceof King && board[possibility[c][0]][possibility[c][1]].isWhite() != isWhite()) {
				return true;
			}
		}
		return false;
	}
}
