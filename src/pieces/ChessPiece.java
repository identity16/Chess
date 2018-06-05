package pieces;

import utils.ChessColor;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public abstract class ChessPiece {

    private int x;
    private int y;
    protected ChessColor color;
    private int moveCount;
    private BufferedImage image;

    public ChessPiece(int x, int y, ChessColor color) {
        this.x = x;
        this.y = y;
        this.color = color;

        this.moveCount = 0;
    }

    public abstract ArrayList<boolean[]> showMovableArea(ChessPiece[][] status);

    public boolean isEnemy() {
        // TODO implement here
        return false;
    }

    public ChessColor getColor() {
        return color;
    }

    public abstract void setColor(ChessColor color);

    public BufferedImage getImage() {
        return image;
    }

    protected void setImage(BufferedImage image) {
        this.image = image;
    }

    public int getMoveCount() {
        return this.moveCount;
    }

    public void addMoveCount() {}

    public int[] getPosition() {
        return new int[] {x, y};
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }



}