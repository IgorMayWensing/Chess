package game;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Rook extends Piece {
	ImageIcon imgIcon;

	public Rook(boolean isWhite) {
		super(isWhite);
		if (isWhite) {
			Image print = new ImageIcon("C:\\Users\\Igor\\eclipse-workspace\\Chess Beta\\src\\game\\whiteRook.png")
					.getImage();
			Image img = print.getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH);
			imgIcon = new ImageIcon(img);
		} else {
			Image print = new ImageIcon("C:\\Users\\Igor\\eclipse-workspace\\Chess Beta\\src\\game\\blackRook.png")
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

		for (int x = 1; x <= 7-i; x++) {
			if (board[i + x][j] == null) {
				possibility[cont][0] = i + x;
				possibility[cont++][1] = j;
			} else {
				if (isWhite() != board[i + x][j].isWhite()) {
					possibility[cont][0] = i + x;
					possibility[cont++][1] = j;
					break;
				} else {
					break;
				}
			}
		}

		for (int x = 1; x <= 7-j; x++) {
			if (board[i][j + x] == null) {
				possibility[cont][0] = i;
				possibility[cont++][1] = j + x;
			} else {
				if (isWhite() != board[i][j + x].isWhite()) {
					possibility[cont][0] = i;
					possibility[cont++][1] = j + x;
					break;
				} else {
					break;
				}
			}
		}

		for (int x = 1; x <= i; x++) {
			if (board[i - x][j] == null) {
				possibility[cont][0] = i - x;
				possibility[cont++][1] = j;
			} else {
				if (isWhite() != board[i - x][j].isWhite()) {
					possibility[cont][0] = i - x;
					possibility[cont++][1] = j;
					break;
				} else {
					break;
				}
			}
		}

		for (int x = 1; x <= j; x++) {
			if (board[i][j - x] == null) {
				possibility[cont][0] = i;
				possibility[cont++][1] = j - x;
			} else {
				if (isWhite() != board[i][j - x].isWhite()) {
					possibility[cont][0] = i;
					possibility[cont++][1] = j - x;
					break;
				} else {
					break;
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
