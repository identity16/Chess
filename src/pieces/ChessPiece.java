package pieces;

import utils.ChessColor;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public abstract class ChessPiece {

    public ChessPiece() {
    }

    private int row;
    private int column;
    private ChessColor color;
    private boolean isMoved;
    private BufferedImage image;

    public ChessPiece(int row, int column, ChessColor color) {
        // TODO implement here
    }

    public abstract ArrayList<int[]> showMovableArea(int row, int column, ChessPiece[] board);

    public boolean isEnemy() {
        // TODO implement here
        return false;
    }

    public ChessColor getColor() {
        // TODO implement here
        return null;
    }

    public void setColor(ChessColor color) {
        // TODO implement here
    }

    public BufferedImage getImage() {
        // TODO implement here
        return null;
    }

    public int[] getPosition() {
        return null;
    }

    public void setPosition(int row, int column) {
        // TODO implement here
    }

}