package pieces;

import core.Player;
import kr.ac.cau.mecs.lenerd.chess.ChessPieceSprite;
import utils.ChessColor;

import core.GameManager;

public class Queen extends ChessPiece {

    public Queen(int x, int y, ChessColor color) {
        super(x, y, color);

        ChessPieceSprite.ChessPieceSpriteType pieceType = null;

        switch(color) {
            case WHITE:
                pieceType = ChessPieceSprite.ChessPieceSpriteType.WHITE_QUEEN;
                break;
            case RED:
                pieceType = ChessPieceSprite.ChessPieceSpriteType.RED_QUEEN;
                break;
            case BLACK:
                pieceType = ChessPieceSprite.ChessPieceSpriteType.BLACK_QUEEN;
                break;
            case GREEN:
                pieceType = ChessPieceSprite.ChessPieceSpriteType.GREEN_QUEEN;
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
    	int i, j;
    	// 이 말이 적의 말인지
    	boolean isThisEnemy = this.getColor() != turn.getColor() &&
				(gm.getNumOfPlayers() == 2 || this.getColor() != gm.getAlly(turn).getColor());

		if(gm.getNumOfPlayers() == 2) {
			//right up cross
			for(i = location[1] - 1, j = location[0] + 1;i >= 0 && j <= 7;i--, j++) {
				if(status[i][j] == null)
					movableArr[i][j] = true;
				else if(isEnemy(status[i][j]) == true || isThisEnemy) {
					movableArr[i][j] = true;
					break;
				}
				else if(isEnemy(status[i][j]) == false)
					break;
			}
			//left up cross
			for(i = location[1] - 1, j = location[0] - 1;i >= 0 && j >= 0;i--, j--) {
				if(status[i][j] == null)
					movableArr[i][j] = true;
				else if(isEnemy(status[i][j]) == true || isThisEnemy) {
					movableArr[i][j] = true;
					break;
				}
				else if(isEnemy(status[i][j]) == false)
					break;
			}
			//right down cross
			for(i = location[1] + 1, j = location[0] + 1;i <= 7 &&j <= 7;i++,j++) {
				if(status[i][j] == null)
					movableArr[i][j] = true;
				else if(isEnemy(status[i][j]) == true || isThisEnemy) {
					movableArr[i][j] = true;
					break;
				}
				else if(isEnemy(status[i][j]) == false)
					break;
			}
			//left down cross
			for(i = location[1] + 1, j = location[0] - 1;i <= 7 && j >= 0;i++, j--) {
				if(status[i][j] == null)
					movableArr[i][j] = true;
				else if(isEnemy(status[i][j]) == true || isThisEnemy) {
					movableArr[i][j] = true;
					break;
				}
				else if(isEnemy(status[i][j]) == false)
					break;
			}
			//up
			for(i = location[1] - 1;i >= 0;i--) {
				if(status[i][location[0]] == null)
					movableArr[i][location[0]] = true;
				else if(isEnemy(status[i][location[0]]) == true || isThisEnemy) {
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
				else if(isEnemy(status[i][location[0]]) == true || isThisEnemy) {
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
				else if(isEnemy(status[location[1]][j]) == true || isThisEnemy) {
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
				else if(isEnemy(status[location[1]][j]) == true || isThisEnemy) {
					movableArr[location[1]][j] = true;
					break;
				}
				else if(isEnemy(status[location[1]][j]) == false)
					break;
			}
		}
		else {
//			if(selectedPiece.getColor() == gm.getCurrentTurn().getColor()) {
				//right up cross
				for(i = location[1] - 1, j = location[0] + 1;i >= 0 && j <= 13;i--, j++) {
					if(status[i][j] == null)
						movableArr[i][j] = true;
					else if(isEnemy(status[i][j]) == true || isThisEnemy) {
						movableArr[i][j] = true;
						break;
					}
					else if(isEnemy(status[i][j]) == false)
						break;
				}
				//left up cross
				for(i = location[1] - 1, j = location[0] - 1;i >= 0 && j >= 0;i--, j--) {
					if(status[i][j] == null)
						movableArr[i][j] = true;
					else if(isEnemy(status[i][j]) == true || isThisEnemy) {
						movableArr[i][j] = true;
						break;
					}
					else if(isEnemy(status[i][j]) == false)
						break;
				}
				//right down cross
				for(i = location[1] + 1, j = location[0] + 1;i <= 13 &&j <= 13;i++,j++) {
					if(status[i][j] == null)
						movableArr[i][j] = true;
					else if(isEnemy(status[i][j]) == true || isThisEnemy) {
						movableArr[i][j] = true;
						break;
					}
					else if(isEnemy(status[i][j]) == false)
						break;
				}
				//left down cross
				for(i = location[1] + 1, j = location[0] - 1;i <= 13 && j >= 0;i++, j--) {
					if(status[i][j] == null)
						movableArr[i][j] = true;
					else if(isEnemy(status[i][j]) == true || isThisEnemy) {
						movableArr[i][j] = true;
						break;
					}
					else if(isEnemy(status[i][j]) == false)
						break;
				}
				//up
				for(i = location[1] - 1;i >= 0;i--) {
					if(status[i][location[0]] == null)
						movableArr[i][location[0]] = true;
					else if(isEnemy(status[i][location[0]]) == true || isThisEnemy) {
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
					else if(isEnemy(status[i][location[0]]) == true || isThisEnemy) {
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
					else if(isEnemy(status[location[1]][j]) == true || isThisEnemy) {
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
					else if(isEnemy(status[location[1]][j]) == true || isThisEnemy) {
						movableArr[location[1]][j] = true;
						break;
					}
					else if(isEnemy(status[location[1]][j]) == false)
						break;
				}
			}
//		}

    	return movableArr;
    }

    @Override
    public void setColor(ChessColor color) {
        this.color = color;

        ChessPieceSprite.ChessPieceSpriteType pieceType = null;

        switch (color) {
            case WHITE:
                pieceType = ChessPieceSprite.ChessPieceSpriteType.WHITE_QUEEN;
                break;
            case RED:
                pieceType = ChessPieceSprite.ChessPieceSpriteType.RED_QUEEN;
                break;
            case BLACK:
                pieceType = ChessPieceSprite.ChessPieceSpriteType.BLACK_QUEEN;
                break;
            case GREEN:
                pieceType = ChessPieceSprite.ChessPieceSpriteType.GREEN_QUEEN;
                break;
        }

        setImage(ChessPieceSprite.getInstace().getChessPiece(pieceType));
    }


}