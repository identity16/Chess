package pieces;

import kr.ac.cau.mecs.lenerd.chess.ChessPieceSprite;
import utils.ChessColor;

import java.util.*;

import core.GameManager;

public class Knight extends ChessPiece {

    public Knight(int x, int y, ChessColor color) {
        super(x, y, color);

        ChessPieceSprite.ChessPieceSpriteType pieceType = null;

        switch(color) {
            case WHITE:
                pieceType = ChessPieceSprite.ChessPieceSpriteType.WHITE_KNIGHT;
                break;
            case RED:
                pieceType = ChessPieceSprite.ChessPieceSpriteType.RED_KNIGHT;
                break;
            case BLACK:
                pieceType = ChessPieceSprite.ChessPieceSpriteType.BLACK_KNIGHT;
                break;
            case GREEN:
                pieceType = ChessPieceSprite.ChessPieceSpriteType.GREEN_KNIGHT;
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

    public boolean[][] showMovableArea(ChessPiece[][] status, boolean[][] movableArr) {
    	GameManager gm = GameManager.runningGame;
    	ChessPiece selectedPiece = this;
    	int[] location = selectedPiece.getPosition();
		// 이 말이 적의 말인지
    	boolean isThisEnemy = this.getColor() != gm.getCurrentTurn().getColor() &&
				(gm.getNumOfPlayers() == 2 || this.getColor() != gm.getAlly(gm.getCurrentTurn()).getColor());


    	if(gm.getNumOfPlayers() == 2) {
//	    	if(selectedPiece.getColor() == gm.getCurrentTurn().getColor()) {
	    		if((location[1] - 2) >= 0 && (location[0] - 1) >= 0) {
    				if(status[location[1] - 2][location[0] - 1] == null ||
							isEnemy(status[location[1] - 2][location[0] - 1]))

    					movableArr[location[1] - 2][location[0] - 1] = true;
	    		}
	    		if((location[1] - 1) >= 0 && (location[0] - 2) >= 0) {
    				if(status[location[1] - 1][location[0] - 2] == null ||
    						isEnemy(status[location[1] - 1][location[0] - 2]) == true  || isThisEnemy)

    					movableArr[location[1] - 1][location[0] - 2] = true;
	    		}
	    		if((location[1] + 1) <= 7 && (location[0] - 2) >= 0) {
    				if(status[location[1] + 1][location[0] - 2] == null ||
    						isEnemy(status[location[1] + 1][location[0] - 2]) == true || isThisEnemy)

    					movableArr[location[1] + 1][location[0] - 2] = true;
	    		}
	    		if((location[1] + 2) <= 7 && (location[0] - 1) >= 0) {
    				if(status[location[1] + 2][location[0] - 1] == null ||
    						isEnemy(status[location[1] + 2][location[0] - 1]) == true || isThisEnemy)

    					movableArr[location[1] + 2][location[0] - 1] = true;
	    		}
	    		if((location[1] + 2 <= 7) && (location[0] + 1) <= 7) {
    				if (status[location[1] + 2][location[0] + 1] == null ||
    						isEnemy(status[location[1] + 2][location[0] + 1]) == true || isThisEnemy)

    					movableArr[location[1] + 2][location[0] + 1] = true;
	    		}
	    		if((location[1] + 1 <= 7) && (location[0] + 2) <= 7) {
    				if(status[location[1] + 1][location[0] + 2] == null ||
    						isEnemy(status[location[1] + 1][location[0] + 2]) == true || isThisEnemy)

    					movableArr[location[1] + 1][location[0] + 2] = true;
	    		}
	    		if((location[1] - 1) >= 0 && (location[0] + 2) <= 7) {
    				if(status[location[1] - 1][location[0] + 2] == null ||
    						isEnemy(status[location[1] - 1][location[0] + 2]) == true || isThisEnemy)

	    			movableArr[location[1] - 1][location[0] + 2] = true;
	    		}
	    		if((location[1] - 2) >= 0 && (location[0] + 1) <= 7) {
    				if(status[location[1] - 2][location[0] + 1] == null ||
    						isEnemy(status[location[1] - 2][location[0] + 1]) == true || isThisEnemy)

    					movableArr[location[1] - 2][location[0] + 1] = true;
	    		}
//	    	}
    	}

    	else {
			if((location[1] - 2) >= 0 && (location[0] - 1) >= 0) {
				if(status[location[1] - 2][location[0] - 1] == null ||
						isEnemy(status[location[1] - 2][location[0] - 1]) == true || isThisEnemy)

					movableArr[location[1] - 2][location[0] - 1] = true;
			}
			if((location[1] - 1) >= 0 && (location[0] - 2) >= 0) {
				if(status[location[1] - 1][location[0] - 2] == null ||
						isEnemy(status[location[1] - 1][location[0] - 2]) == true || isThisEnemy)

					movableArr[location[1] - 1][location[0] - 2] = true;
			}
			if((location[1] + 1) <= 13 && (location[0] - 2) >= 0) {
				if(status[location[1] + 1][location[0] - 2] == null ||
						isEnemy(status[location[1] + 1][location[0] - 2]) == true || isThisEnemy)

					movableArr[location[1] + 1][location[0] - 2] = true;
			}
			if((location[1] + 2) <= 13 && (location[0] - 1) >= 0) {
				if(status[location[1] + 2][location[0] - 1] == null ||
						isEnemy(status[location[1] + 2][location[0] - 1]) == true || isThisEnemy)

					movableArr[location[1] + 2][location[0] - 1] = true;
			}
			if((location[1] + 2) <= 13 && (location[0] + 1) <= 13) {
				if(status[location[1] + 2][location[0] + 1] == null ||
						isEnemy(status[location[1] + 2][location[0] + 1]) == true || isThisEnemy)

					movableArr[location[1] + 2][location[0] + 1] = true;
			}
			if((location[1] + 1) <= 13 && (location[0] + 2) <= 13) {
				if(status[location[1] + 1][location[0] + 2] == null ||
						isEnemy(status[location[1] + 1][location[0] + 2]) == true || isThisEnemy)

					movableArr[location[1] + 1][location[0] + 2] = true;
			}
			if((location[1] - 1) >= 0 &&(location[0] + 2) <= 13) {
				if(status[location[1] - 1][location[0] + 2] == null ||
						isEnemy(status[location[1] - 1][location[0] + 2]) == true || isThisEnemy)

					movableArr[location[1] - 1][location[0] + 2] = true;
			}
			if((location[1] - 2) >= 0 && (location[0] + 1) <= 13) {
				if(status[location[1] - 2][location[0] + 1] == null ||
						isEnemy(status[location[1] - 2][location[0] + 1]) == true || isThisEnemy)

					movableArr[location[1] - 2][location[0] + 1] = true;
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
                pieceType = ChessPieceSprite.ChessPieceSpriteType.WHITE_KNIGHT;
                break;
            case RED:
                pieceType = ChessPieceSprite.ChessPieceSpriteType.RED_KNIGHT;
                break;
            case BLACK:
                pieceType = ChessPieceSprite.ChessPieceSpriteType.BLACK_KNIGHT;
                break;
            case GREEN:
                pieceType = ChessPieceSprite.ChessPieceSpriteType.GREEN_KNIGHT;
                break;
        }

        setImage(ChessPieceSprite.getInstace().getChessPiece(pieceType));
    }


}