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
    
    //ChessPiece's status
    public ChessPiece(int x, int y, ChessColor color) {
        this.x = x;
        this.y = y;
        this.color = color;

        this.moveCount = 0;
        this.gm = GameManager.runningGame;
    }
    
    //Return ChessPiece's MovableArea
    public abstract boolean[][] showMovableArea(ChessPiece[][] status);
    //Set boolean[][] true if ChessPiece can move Chess square
    public abstract boolean[][] showMovableArea(ChessPiece[][] status, boolean[][] movableArr);

    //Check the ChessPiece is not enemy
    public boolean isEnemy(ChessPiece selectedPiece) {
    	gm = GameManager.runningGame;
    	if(selectedPiece == null)
    		return false;

    	if(gm.getNumOfPlayers() == 2) {
    		if(selectedPiece.getColor() != this.getColor())
    			return true;
    		else
    			return false;
    	}
    	else {
	    	if(selectedPiece.getColor() != this.getColor() &&
	    			selectedPiece.getColor() != gm.getAlly(gm.getPlayer(this.getColor())).getColor())
	    		return true;
	    	else
	    		return false;
	    	}
    	}

    //Get ChessPiece's color
    public ChessColor getColor() {
        return color;
    }

    //Set ChessPiece's color
    public abstract void setColor(ChessColor color);

    //Get ChessPiece's image
    public BufferedImage getImage() {
        return image;
    }

    //Set ChessPiece's image
    protected void setImage(BufferedImage image) {
        this.image = image;
    }

    //Get how many times ChessPiece is moved
    public int getMoveCount() {
        return this.moveCount;
    }

    //Add Count if ChessPiece is moved
    public void addMoveCount() {
    	moveCount++;
    }

    //Get ChessPiece's location
    public int[] getPosition() {
        return new int[] {x, y};
    }
    
    //Set ChessPiece's location
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
}