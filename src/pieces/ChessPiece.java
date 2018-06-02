package pieces;

import utils.ChessColor;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public abstract class ChessPiece {

    private int x;
    private int y;
    private ChessColor color;
    private boolean isMoved;
    private BufferedImage image;

    public ChessPiece(int x, int y, ChessColor color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public abstract ArrayList<int[]> showMovableArea(int x, int y, ChessPiece[] board);

    public boolean isEnemy() {
        // TODO implement here
        return false;
    }

    public ChessColor getColor() {
        // TODO implement here
        return color;
    }

    public void setColor(ChessColor color) {
        this.color = color;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public int[] getPosition() {
        return new int[] {x, y};
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

}