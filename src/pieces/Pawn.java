package pieces;

import kr.ac.cau.mecs.lenerd.chess.ChessPieceSprite;
import utils.ChessColor;
import utils.Direction;

import java.util.*;

import core.GameManager;

public class Pawn extends ChessPiece {

    private Direction direction;

    public Pawn(int x, int y, ChessColor color, Direction direction) {
        super(x, y, color);
        this.direction = direction;

        ChessPieceSprite.ChessPieceSpriteType pieceType = null;

        switch(color) {
            case WHITE:
                pieceType = ChessPieceSprite.ChessPieceSpriteType.WHITE_PAWN;
                break;
            case RED:
                pieceType = ChessPieceSprite.ChessPieceSpriteType.RED_PAWN;
                break;
            case BLACK:
                pieceType = ChessPieceSprite.ChessPieceSpriteType.BLACK_PAWN;
                break;
            case GREEN:
                pieceType = ChessPieceSprite.ChessPieceSpriteType.GREEN_PAWN;
                break;
        }

        setImage(ChessPieceSprite.getInstace().getChessPiece(pieceType));
    }

    @Override
    public boolean[][] showMovableArea(ChessPiece[][] status) {
    	gm = GameManager.runningGame;
    	if(gm.getNumOfPlayers() == 2)
    		return showMovableArea(status, new boolean[8][8]);
    	else
    		return showMovableArea(status, new boolean[14][14]);
    }
    
    public boolean[][] showMovableArea(ChessPiece[][] status, boolean[][] movableArr) {
    	gm = GameManager.runningGame;
    	ChessPiece selectedPiece = gm.getBoard().getSelectedPiece();
    	if(selectedPiece == null)
    		return movableArr;
    		
    	int[] location = selectedPiece.getPosition();
    	
    	if(gm.getNumOfPlayers() == 2) {
    		//direction = NORTH(1vs1)
    		if(selectedPiece != status[0][0] && selectedPiece != status[0][1] &&
    				selectedPiece != status[0][2] && selectedPiece != status[0][3] &&
    				selectedPiece != status[0][4] && selectedPiece != status[0][5] &&
    				selectedPiece != status[0][6] && selectedPiece != status[0][7]) {
		    	if(direction == Direction.NORTH) {
		    		//direction = left_cross = enemy -> check
		    		if((location[0] - 1) >= 0 && isEnemy(status[location[1] - 1][location[0] - 1]) == true)
		    			movableArr[location[1] - 1][location[0] - 1] = true;
		    		//direction = right_cross = enemy -> check
		    		if((location[0] + 1) <= 7 && isEnemy(status[location[1] - 1][location[0] + 1]) == true)
		    			movableArr[location[1] - 1][location[0] + 1] = true;
		    	
			    	//first move
			    	if(getMoveCount() == 0) {
			    		if(status[location[1] - 1][location[0]] == null) {
			    			movableArr[location[1] - 1][location[0]] = true;
			    			
			    			if(status[location[1] - 2][location[0]] == null)
			    				movableArr[location[1] - 2][location[0]] = true;
			    		}
			    	}
			    	//not first move
			    	else {
			    		if(status[location[1] - 1][location[0]] == null)
			    			movableArr[location[1] - 1][location[0]] = true;
			    	}
	    		}
    		}
    		//direction = SOUTH(1vs1)
    		if(selectedPiece != status[7][0] && selectedPiece != status[7][1] &&
	    			selectedPiece != status[7][2] && selectedPiece != status[7][3] &&
	    			selectedPiece != status[7][4] && selectedPiece != status[7][5] &&
	    			selectedPiece != status[7][6] && selectedPiece != status[7][7]) {
		    	if(direction == Direction.SOUTH) {
		    		//direction = left_cross = enemy -> check
		    		if((location[0] + 1) <= 7 && isEnemy(status[location[1] + 1][location[0] + 1]) == true)
		    			movableArr[location[1] + 1][location[0] + 1] = true;
		    		//direction = right_cross = enemy -> check
		    		if((location[0] - 1) >= 0 && isEnemy(status[location[1] + 1][location[0] - 1]) == true)
		    			movableArr[location[1] + 1][location[0] - 1] = true;
		    	
		    		//first move
			    	if(getMoveCount() == 0) {
			    		if(status[location[1] + 1][location[0]] == null) {
			    			movableArr[location[1] + 1][location[0]] = true;
			    			
			    			if(status[location[1] + 2][location[0]] == null)
			    				movableArr[location[1] + 2][location[0]] = true;
			    		}
			    	}
			    	//not first move
			    	else {
			    		if(status[location[1] + 1][location[0]] == null)
			    			movableArr[location[1] + 1][location[0]] = true;
			    	}
		    	}
    		}
    		//direction = WEST(1vs1)
    		if(selectedPiece != status[0][0] && selectedPiece != status[1][0] &&
	    			selectedPiece != status[2][0] && selectedPiece != status[3][0] &&
	    			selectedPiece != status[4][0] && selectedPiece != status[5][0] &&
	    			selectedPiece != status[6][0] && selectedPiece != status[7][0]) {
		    	if(direction == Direction.WEST) {
		    		//direction = left_cross = enemy -> check
		    		if((location[1] + 1) <= 7 && isEnemy(status[location[1] + 1][location[0] - 1]) == true)
		    			movableArr[location[1] + 1][location[0] - 1] = true;
		    		//direction = right_cross = enemy -> check
		    		if((location[1] - 1) >= 0 && isEnemy(status[location[1] - 1][location[0] - 1]) == true)
		    			movableArr[location[1] - 1][location[0] - 1] = true;

		    		
			    	//first move
			    	if(getMoveCount() == 0) {
			    		if(status[location[1]][location[0] - 1] == null) {
			    			movableArr[location[1]][location[0] - 1] = true;
			    			
			    			if(status[location[1]][location[0] - 2] == null)
			    				movableArr[location[1]][location[0] - 2] = true;
			    		}
			    	}
			    	//not first move
			    	else {
			    		if(status[location[1]][location[0] - 1] == null)
			    			movableArr[location[1]][location[0] - 1] = true;
			    	}
		    	}
    		}
    		
    		//direction = EAST(1vs1)
    		if(selectedPiece != status[0][7] && selectedPiece != status[1][7] &&
	    			selectedPiece != status[2][7] && selectedPiece != status[3][7] &&
	    			selectedPiece != status[4][7] && selectedPiece != status[5][7] &&
	    			selectedPiece != status[6][7] && selectedPiece != status[7][7]) {
		    	if(direction == Direction.EAST) {
		    		//direction = left_cross = enemy -> check
		    		if((location[1] - 1) >= 0 && isEnemy(status[location[1] - 1][location[0] + 1]) == true)
		    			movableArr[location[1] - 1][location[0] + 1] = true;
		    		//direction = right_cross = enemy -> check
		    		if((location[1] + 1) <= 7 && isEnemy(status[location[1] + 1][location[0] + 1]) == true)
		    			movableArr[location[1] + 1][location[0] + 1] = true;
		    	
			    	//first move
			    	if(getMoveCount() == 0) {
			    		if(status[location[1]][location[0] + 1] == null) {
			    			movableArr[location[1]][location[0] + 1] = true;
			    			
			    			if(status[location[1]][location[0] + 2] == null)
			    				movableArr[location[1]][location[0] + 2] = true;
			    		}
			    	}
			    	//not first move
			    	else {
			    		if(status[location[1]][location[0] + 1] == null)
			    			movableArr[location[1]][location[0] + 1] = true;
			    	}
		    	}
    		}
    	}
    	
    	//direction = NORTH(2vs2)
    	else {
    		if(selectedPiece != status[0][3] && selectedPiece != status[0][4] &&
			selectedPiece != status[0][5] && selectedPiece != status[0][6] &&
			selectedPiece != status[0][7] && selectedPiece != status[0][8] &&
			selectedPiece != status[0][9] && selectedPiece != status[0][10] &&
			selectedPiece != status[3][0] && selectedPiece != status[3][1] &&
			selectedPiece != status[3][12] && selectedPiece != status[3][13]) {
		    	if(direction == Direction.NORTH) {
		    		//direction = left_cross = enemy -> check
		    		if(isEnemy(status[location[1] - 1][location[0] - 1]) == true)
		    			movableArr[location[1] - 1][location[0] - 1] = true;
		    		//direction = right_cross = enemy -> check
		    		if(isEnemy(status[location[1] - 1][location[0] + 1]) == true)
		    			movableArr[location[1] - 1][location[0] + 1] = true;
		    	
			    	//first move
			    	if(getMoveCount() == 0) {
			    		if(status[location[1] - 1][location[0]] == null) {
			    			movableArr[location[1] - 1][location[0]] = true;
			    			
			    			if(status[location[1] - 2][location[0]] == null)
			    				movableArr[location[1] - 2][location[0]] = true;
			    		}
			    	}
			    	//not first move
			    	else {
			    		if(status[location[1] - 1][location[0]] == null)
			    			movableArr[location[1] - 1][location[0]] = true;
			    	}
		    	}
    		}
    	
    		//direction = SOUTH(2vs2)
    		if(selectedPiece != status[10][0] && selectedPiece != status[10][1] &&
	    			selectedPiece != status[10][12] && selectedPiece != status[10][13] &&
	    			selectedPiece != status[13][3] && selectedPiece != status[13][4] &&
	    			selectedPiece != status[13][5] && selectedPiece != status[13][6] &&
	    			selectedPiece != status[13][7] && selectedPiece != status[13][8] &&
	    			selectedPiece != status[13][9] && selectedPiece != status[13][10]) {
    			if(direction == Direction.SOUTH) {
		    		if(isEnemy(status[location[1] + 1][location[0] + 1]) == true)
		    			movableArr[location[1] + 1][location[0] + 1] = true;
		    		//direction = left_cross -> check
		    		if(isEnemy(status[location[1] + 1][location[0] - 1]) == true)
		    			movableArr[location[1] + 1][location[0] - 1] = true;
		    		//direction = right_cross -> check
		    	
			    	//first move
			    	if(getMoveCount() == 0) {
			    		if(status[location[1] + 1][location[0]] == null) {
			    			movableArr[location[1] + 1][location[0]] = true;
			    			
			    			if(status[location[1] + 2][location[0]] == null)
			    				movableArr[location[1] + 2][location[0]] = true;
			    		}
			    	}
			    	//not first move
			    	else {
			    		if(status[location[1] + 1][location[0]] == null)
			    			movableArr[location[1] + 1][location[0]] = true;
			    	}
	    		}
    		}
    		
    		//direction = WEST(2vs2)
    		if(selectedPiece != status[3][0] && selectedPiece != status[4][0] &&
	    			selectedPiece != status[5][0] && selectedPiece != status[6][0] &&
	    			selectedPiece != status[7][0] && selectedPiece != status[8][0] &&
	    			selectedPiece != status[9][0] && selectedPiece != status[10][0] &&
	    			selectedPiece != status[0][3] && selectedPiece != status[1][3] &&
	    			selectedPiece != status[12][3] && selectedPiece != status[13][3]) {
		    	if(direction == Direction.WEST) {
		    		//direction = left_cross = enemy -> check
		    		if(isEnemy(status[location[1] + 1][location[0] - 1]) == true)
		    			movableArr[location[1] + 1][location[0] - 1] = true;
		    		//direction = right_cross = enemy -> check
		    		if(isEnemy(status[location[1] - 1][location[0] - 1]) == true)
		    			movableArr[location[1] - 1][location[0] - 1] = true;

		    	
		    		//first move
			    	if(getMoveCount() == 0) {
			    		if(status[location[1]][location[0] - 1] == null) {
			    			movableArr[location[1]][location[0] - 1] = true;
			    			
			    			if(status[location[1]][location[0] - 2] == null)
			    				movableArr[location[1]][location[0] - 2] = true;
			    		}
			    	}
			    	//not first move
			    	else {
			    		if(status[location[1]][location[0] - 1] == null)
			    			movableArr[location[1]][location[0] - 1] = true;
			    	}
	    		}
    		}
    	
    		//direction = EAST(2vs2)
	    	if(selectedPiece != status[0][10] && selectedPiece != status[1][10] &&
	    			selectedPiece != status[12][10] && selectedPiece != status[13][10] &&
	    			selectedPiece != status[3][13] && selectedPiece != status[4][13] &&
	    			selectedPiece != status[5][13] && selectedPiece != status[6][13] &&
	    			selectedPiece != status[7][13] && selectedPiece != status[8][13] &&
	    			selectedPiece != status[9][13] && selectedPiece != status[10][13]) {
		    	if(direction == Direction.EAST) {
		    		//direction = left_cross = enemy -> check
		    		if(isEnemy(status[location[1] - 1][location[0] + 1]) == true)
		    			movableArr[location[1] - 1][location[0] + 1] = true;
		    		//direction = right_cross = enemy -> check
		    		if(isEnemy(status[location[1] + 1][location[0] + 1]) == true)
		    			movableArr[location[1] + 1][location[0] + 1] = true;
			    	
			    	//first move
			    	if(getMoveCount() == 0) {
			    		if(status[location[1]][location[0] + 1] == null) {
			    			movableArr[location[1]][location[0] + 1] = true;
			    			
			    			if(status[location[1]][location[0] + 2] == null)
			    				movableArr[location[1]][location[0] + 2] = true;
			    		}
			    	}
			    	//not first move
			    	else {
			    		if(status[location[1]][location[0] + 1] == null)
			    			movableArr[location[1]][location[0] + 1] = true;
			    	}
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
                pieceType = ChessPieceSprite.ChessPieceSpriteType.WHITE_PAWN;
                break;
            case RED:
                pieceType = ChessPieceSprite.ChessPieceSpriteType.RED_PAWN;
                break;
            case BLACK:
                pieceType = ChessPieceSprite.ChessPieceSpriteType.BLACK_PAWN;
                break;
            case GREEN:
                pieceType = ChessPieceSprite.ChessPieceSpriteType.GREEN_PAWN;
                break;
        }

        setImage(ChessPieceSprite.getInstace().getChessPiece(pieceType));
    }
}