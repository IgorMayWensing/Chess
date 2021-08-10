package game;

import java.awt.Image;

import javax.swing.ImageIcon;

public abstract class Piece {

    private boolean isWhite;

    public Piece(boolean isWhite) {
        this.isWhite = isWhite;
    }

    public boolean isWhite() {
        return this.isWhite;
    }

    public abstract int[][] move(Piece[][] board, int i, int j);
    
    public abstract ImageIcon getImgIcon();
    
    public abstract boolean check(Piece[][] board, int i, int j);
    
}
