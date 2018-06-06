package pieces;

import utils.ChessColor;
import java.awt.image.BufferedImage;

import core.GameManager;

public abstract class ChessPiece {

    private int x;
    private int y;
    protected ChessColor color;
    private int moveCount;
    private BufferedImage image;

    GameManager gm;

    public ChessPiece(int x, int y, ChessColor color) {
        this.x = x;
        this.y = y;
        this.color = color;

        this.moveCount = 0;
        this.gm = GameManager.runningGame;
    }

    public abstract boolean[][] showMovableArea(ChessPiece[][] status);
    public abstract boolean[][] showMovableArea(ChessPiece[][] status, boolean[][] movableArr);

    public boolean isEnemy(ChessPiece selectedPiece) {
    	gm = GameManager.runningGame;
    	if(selectedPiece == null)
    		return false;

    	if(gm.getNumOfPlayers() == 2) {
    		if(selectedPiece.getColor() != gm.getCurrentTurn().getColor())
    			return true;
    		else
    			return false;
    	}
    	else {
	    	if(selectedPiece.getColor() != gm.getCurrentTurn().getColor() &&
	    			selectedPiece.getColor() != gm.getAlly(gm.getCurrentTurn()).getColor())
	    		return true;
	    	else
	    		return false;
	    	}
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

    public void addMoveCount() {
    	moveCount++;
    }

    public int[] getPosition() {
        return new int[] {x, y};
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
}