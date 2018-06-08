package pieces;

import core.Player;
import kr.ac.cau.mecs.lenerd.chess.ChessPieceSprite;
import utils.ChessColor;


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

	@Override
	public boolean[][] showMovableArea(ChessPiece[][] status, boolean[][] movableArr) {
		return showMovableArea(status, movableArr, gm.getCurrentTurn());
	}

    @Override
    public boolean[][] showMovableArea(ChessPiece[][] status, boolean[][] movableArr, Player turn) {
    	GameManager gm = GameManager.runningGame;
    	ChessPiece selectedPiece = this;
    	int[] location = selectedPiece.getPosition();
		// 이 말이 적의 말인지
    	boolean isThisEnemy = this.getColor() != turn.getColor() &&
				(gm.getNumOfPlayers() == 2 || this.getColor() != gm.getAlly(turn).getColor());

    	//Knight's move(1vs1)
    	if(gm.getNumOfPlayers() == 2) {
    		//two times up, one time left move
    		if((location[1] - 2) >= 0 && (location[0] - 1) >= 0) {
				if(status[location[1] - 2][location[0] - 1] == null ||
						isEnemy(status[location[1] - 2][location[0] - 1]))

					movableArr[location[1] - 2][location[0] - 1] = true;
    		}
    		//one time up, two times left move
    		if((location[1] - 1) >= 0 && (location[0] - 2) >= 0) {
				if(status[location[1] - 1][location[0] - 2] == null ||
						isEnemy(status[location[1] - 1][location[0] - 2]) == true  || isThisEnemy)

					movableArr[location[1] - 1][location[0] - 2] = true;
    		}
    		//one time down, two times left move
    		if((location[1] + 1) <= 7 && (location[0] - 2) >= 0) {
				if(status[location[1] + 1][location[0] - 2] == null ||
						isEnemy(status[location[1] + 1][location[0] - 2]) == true || isThisEnemy)

					movableArr[location[1] + 1][location[0] - 2] = true;
    		}
    		//two times down,one time left move
    		if((location[1] + 2) <= 7 && (location[0] - 1) >= 0) {
				if(status[location[1] + 2][location[0] - 1] == null ||
						isEnemy(status[location[1] + 2][location[0] - 1]) == true || isThisEnemy)

					movableArr[location[1] + 2][location[0] - 1] = true;
    		}
    		//two times down, one time right move
    		if((location[1] + 2 <= 7) && (location[0] + 1) <= 7) {
				if (status[location[1] + 2][location[0] + 1] == null ||
						isEnemy(status[location[1] + 2][location[0] + 1]) == true || isThisEnemy)

					movableArr[location[1] + 2][location[0] + 1] = true;
    		}
    		//one time down, two times right move
    		if((location[1] + 1 <= 7) && (location[0] + 2) <= 7) {
				if(status[location[1] + 1][location[0] + 2] == null ||
						isEnemy(status[location[1] + 1][location[0] + 2]) == true || isThisEnemy)

					movableArr[location[1] + 1][location[0] + 2] = true;
    		}
    		//one time up, two times right move
    		if((location[1] - 1) >= 0 && (location[0] + 2) <= 7) {
				if(status[location[1] - 1][location[0] + 2] == null ||
						isEnemy(status[location[1] - 1][location[0] + 2]) == true || isThisEnemy)

    			movableArr[location[1] - 1][location[0] + 2] = true;
    		}
    		//two times up, one time left move
    		if((location[1] - 2) >= 0 && (location[0] + 1) <= 7) {
				if(status[location[1] - 2][location[0] + 1] == null ||
						isEnemy(status[location[1] - 2][location[0] + 1]) == true || isThisEnemy)

					movableArr[location[1] - 2][location[0] + 1] = true;
    		}
    	}

    	//Knight's move(2vs2)
    	else {
    		//two times up, one time left move
			if((location[1] - 2) >= 0 && (location[0] - 1) >= 0) {
				if(status[location[1] - 2][location[0] - 1] == null ||
						isEnemy(status[location[1] - 2][location[0] - 1]) == true || isThisEnemy)

					movableArr[location[1] - 2][location[0] - 1] = true;
			}
			//one time up, two times left move
			if((location[1] - 1) >= 0 && (location[0] - 2) >= 0) {
				if(status[location[1] - 1][location[0] - 2] == null ||
						isEnemy(status[location[1] - 1][location[0] - 2]) == true || isThisEnemy)

					movableArr[location[1] - 1][location[0] - 2] = true;
			}
			//one time down, two times left move
			if((location[1] + 1) <= 13 && (location[0] - 2) >= 0) {
				if(status[location[1] + 1][location[0] - 2] == null ||
						isEnemy(status[location[1] + 1][location[0] - 2]) == true || isThisEnemy)

					movableArr[location[1] + 1][location[0] - 2] = true;
			}
			//two times down, one time left move
			if((location[1] + 2) <= 13 && (location[0] - 1) >= 0) {
				if(status[location[1] + 2][location[0] - 1] == null ||
						isEnemy(status[location[1] + 2][location[0] - 1]) == true || isThisEnemy)

					movableArr[location[1] + 2][location[0] - 1] = true;
			}
			//two times down, one time right move
			if((location[1] + 2) <= 13 && (location[0] + 1) <= 13) {
				if(status[location[1] + 2][location[0] + 1] == null ||
						isEnemy(status[location[1] + 2][location[0] + 1]) == true || isThisEnemy)

					movableArr[location[1] + 2][location[0] + 1] = true;
			}
			//one time down, two times right move
			if((location[1] + 1) <= 13 && (location[0] + 2) <= 13) {
				if(status[location[1] + 1][location[0] + 2] == null ||
						isEnemy(status[location[1] + 1][location[0] + 2]) == true || isThisEnemy)

					movableArr[location[1] + 1][location[0] + 2] = true;
			}
			//one time up, two times right move
			if((location[1] - 1) >= 0 &&(location[0] + 2) <= 13) {
				if(status[location[1] - 1][location[0] + 2] == null ||
						isEnemy(status[location[1] - 1][location[0] + 2]) == true || isThisEnemy)

					movableArr[location[1] - 1][location[0] + 2] = true;
			}
			//two times up, one time right move
			if((location[1] - 2) >= 0 && (location[0] + 1) <= 13) {
				if(status[location[1] - 2][location[0] + 1] == null ||
						isEnemy(status[location[1] - 2][location[0] + 1]) == true || isThisEnemy)

					movableArr[location[1] - 2][location[0] + 1] = true;
			}
    	}

		if(!isThisEnemy) {
			for (int y = 0; y < movableArr.length; y++) {
				for (int x = 0; x < movableArr[y].length; x++) {
					if (!movableArr[y][x]) continue;

					ChessPiece[][] testStatus = gm.getBoard().getStatus();
					gm.getBoard().movePiece(testStatus, this, x, y);

					int[] origPos = this.getPosition().clone();
					this.setPosition(x, y);
					if (gm.getRule().IsCheck(testStatus, gm.getCurrentTurn()))
						movableArr[y][x] = false;

					this.setPosition(origPos[0], origPos[1]);
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