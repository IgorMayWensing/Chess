package game;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Pawn extends Piece {
	private ImageIcon imgIcon;
	private boolean firstMove = true;

	public Pawn(boolean isWhite) {
		super(isWhite);
		if (isWhite) {
			Image print = new ImageIcon("C:\\Users\\Igor\\eclipse-workspace\\Chess Beta\\src\\game\\whitePawn.png")
					.getImage();
			Image img = print.getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH);
			imgIcon = new ImageIcon(img);
		} else {
			Image print = new ImageIcon("C:\\Users\\Igor\\eclipse-workspace\\Chess Beta\\src\\game\\blackPawn.png")
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

		if (firstMove) {
			if (isWhite()) {
				if (i - 2 >= 0 && board[i - 2][j] == null && board[i - 1][j] == null) {
					possibility[cont][0] = i - 1;
					possibility[cont++][1] = j;
					possibility[cont][0] = i - 2;
					possibility[cont++][1] = j;
				}
				if (i - 1 >= 0 && j + 1 <= 7 && board[i - 1][j + 1] != null && !board[i - 1][j + 1].isWhite()) {
					possibility[cont][0] = i - 1;
					possibility[cont++][1] = j + 1;
				}
				if (i - 1 >= 0 && j - 1 >= 0 && board[i - 1][j - 1] != null && !board[i - 1][j - 1].isWhite()) {
					possibility[cont][0] = i - 1;
					possibility[cont++][1] = j - 1;
				}
			} else {
				if (i + 2 <= 7 && board[i + 2][j] == null && board[i + 1][j] == null) {
					possibility[cont][0] = i + 1;
					possibility[cont++][1] = j;
					possibility[cont][0] = i + 2;
					possibility[cont++][1] = j;
				}
				if (i + 1 <= 7 && j + 1 <= 7 && board[i + 1][j + 1] != null && board[i + 1][j + 1].isWhite()) {
					possibility[cont][0] = i + 1;
					possibility[cont++][1] = j + 1;
				}
				if (i + 1 <= 7 && j - 1 >= 0 && board[i + 1][j - 1] != null && board[i + 1][j - 1].isWhite()) {
					possibility[cont][0] = i + 1;
					possibility[cont++][1] = j - 1;
				}
			}
			firstMove = false;
		} else {
			if (isWhite()) {
				if (i - 1 >= 0 && board[i - 1][j] == null) {
					possibility[cont][0] = i - 1;
					possibility[cont++][1] = j;
				}
				if (i - 1 >= 0 && j + 1 <= 7 && board[i - 1][j + 1] != null && !board[i - 1][j + 1].isWhite()) {
					possibility[cont][0] = i - 1;
					possibility[cont++][1] = j + 1;
				}
				if (i - 1 >= 0 && j - 1 >= 0 && board[i - 1][j - 1] != null && !board[i - 1][j - 1].isWhite()) {
					possibility[cont][0] = i - 1;
					possibility[cont++][1] = j - 1;
				}
			} else {
				if (i + 1 <= 7 && board[i + 1][j] == null) {
					possibility[cont][0] = i + 1;
					possibility[cont++][1] = j;
				}
				if (i + 1 <= 7 && j + 1 <= 7 && board[i + 1][j + 1] != null && board[i + 1][j + 1].isWhite()) {
					possibility[cont][0] = i + 1;
					possibility[cont++][1] = j + 1;
				}
				if (i + 1 <= 7 && j - 1 >= 0 && board[i + 1][j - 1] != null && board[i + 1][j - 1].isWhite()) {
					possibility[cont][0] = i + 1;
					possibility[cont++][1] = j - 1;
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
	
	public void setFirstMove() {
		firstMove = true;
	}
	
}
