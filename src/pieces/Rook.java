package pieces;

import kr.ac.cau.mecs.lenerd.chess.ChessPieceSprite;
import utils.ChessColor;

import java.util.*;

import core.GameManager;

public class Rook extends ChessPiece {

    public Rook(int x, int y, ChessColor color) {
        super(x, y, color);
        ChessPieceSprite.ChessPieceSpriteType pieceType = null;

        switch(color) {
            case WHITE:
                pieceType = ChessPieceSprite.ChessPieceSpriteType.WHITE_LOOK;
                break;
            case RED:
                pieceType = ChessPieceSprite.ChessPieceSpriteType.RED_LOOK;
                break;
            case BLACK:
                pieceType = ChessPieceSprite.ChessPieceSpriteType.BLACK_LOOK;
                break;
            case GREEN:
                pieceType = ChessPieceSprite.ChessPieceSpriteType.GREEN_LOOK;
                break;
        }

        setImage(ChessPieceSprite.getInstace().getChessPiece(pieceType));
    }

    @Override
    public boolean[][] showMovableArea(ChessPiece[][] status) {

    	if(GameManager.runningGame.getNumOfPlayers() == 2)
    		return showMovableArea(status, new boolean[8][8]);
    	else
    		return showMovableArea(status, new boolean[14][14]);
    }

    @Override
    public boolean[][] showMovableArea(ChessPiece[][] status, boolean[][] movableArr) {
    	GameManager gm = GameManager.runningGame;
    	ChessPiece selectedPiece = gm.getBoard().getSelectedPiece();
    	int[] location = selectedPiece.getPosition();
    	int i, j;

    	if(gm.getNumOfPlayers() == 2) {
	    	if(selectedPiece.getColor() == gm.getCurrentTurn().getColor()) {
	    		//up
				for(i = location[1] - 1;i >= 0;i--) {
					if(status[i][location[0]] == null)
						movableArr[i][location[0]] = true;
					else if(isEnemy(status[i][location[0]]) == true) {
						movableArr[i][location[0]] = true;
						break;
					}
					else if(isEnemy(status[i][location[0]]) == false)
						break;
				}
				//down
				for(i = location[1] + 1;i <= 7;i++) {
					if(status[i][location[0]] == null)
						movableArr[i][location[0]] = true;
					else if(isEnemy(status[i][location[0]]) == true) {
						movableArr[i][location[0]] = true;
						break;
					}
					else if(isEnemy(status[i][location[0]]) == false)
						break;
				}
				//right
				for(j = location[0] - 1;j >= 0;j--) {
					if(status[location[1]][j] == null)
						movableArr[location[1]][j] = true;
					else if(isEnemy(status[location[1]][j]) == true) {
						movableArr[location[1]][j] = true;
						break;
					}
					else if(isEnemy(status[location[1]][j]) == false)
						break;
				}
				//left
				for(j = location[0] + 1;j <= 7;j++) {
					if(status[location[1]][j] == null)
						movableArr[location[1]][j] = true;
					else if(isEnemy(status[location[1]][j]) == true) {
						movableArr[location[1]][j] = true;
						break;
					}
					else if(isEnemy(status[location[1]][j]) == false)
						break;
				}
	    	}
    	}
    	else {
    		if(selectedPiece.getColor() == gm.getCurrentTurn().getColor()) {
	    		//up
				for(i = location[1] - 1;i >= 0;i--) {
					if(status[i][location[0]] == null)
						movableArr[i][location[0]] = true;
					else if(isEnemy(status[i][location[0]]) == true) {
						movableArr[i][location[0]] = true;
						break;
					}
					else if(isEnemy(status[i][location[0]]) == false)
						break;
				}
				//down
				for(i = location[1] + 1;i <= 13;i++) {
					if(status[i][location[0]] == null)
						movableArr[i][location[0]] = true;
					else if(isEnemy(status[i][location[0]]) == true) {
						movableArr[i][location[0]] = true;
						break;
					}
					else if(isEnemy(status[i][location[0]]) == false)
						break;
				}
				//right
				for(j = location[0] - 1;j >= 0;j--) {
					if(status[location[1]][j] == null)
						movableArr[location[1]][j] = true;
					else if(isEnemy(status[location[1]][j]) == true) {
						movableArr[location[1]][j] = true;
						break;
					}
					else if(isEnemy(status[location[1]][j]) == false)
						break;
				}
				//left
				for(j = location[0] + 1;j <= 13;j++) {
					if(status[location[1]][j] == null)
						movableArr[location[1]][j] = true;
					else if(isEnemy(status[location[1]][j]) == true) {
						movableArr[location[1]][j] = true;
						break;
					}
					else if(isEnemy(status[location[1]][j]) == false)
						break;
				}
	    	}
    	}

        return movableArr;
    }

    @Override
    public void setColor(ChessColor color) {
        this.color = color;

        ChessPieceSprite.ChessPieceSpriteType pieceType = null;

        switch (color) {
            case WHITE:
                pieceType = ChessPieceSprite.ChessPieceSpriteType.WHITE_LOOK;
                break;
            case RED:
                pieceType = ChessPieceSprite.ChessPieceSpriteType.RED_LOOK;
                break;
            case BLACK:
                pieceType = ChessPieceSprite.ChessPieceSpriteType.BLACK_LOOK;
                break;
            case GREEN:
                pieceType = ChessPieceSprite.ChessPieceSpriteType.GREEN_LOOK;
                break;
        }

        setImage(ChessPieceSprite.getInstace().getChessPiece(pieceType));
    }

}
