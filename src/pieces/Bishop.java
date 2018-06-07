package pieces;

import core.Player;
import kr.ac.cau.mecs.lenerd.chess.ChessPieceSprite;
import utils.ChessColor;

import java.util.*;

import core.GameManager;

public class Bishop extends ChessPiece {

	public Bishop(int x, int y, ChessColor color) {
		super(x, y, color);

		ChessPieceSprite.ChessPieceSpriteType pieceType = null;

		switch (color) {
			case WHITE:
				pieceType = ChessPieceSprite.ChessPieceSpriteType.WHITE_BISHOP;
				break;
			case RED:
				pieceType = ChessPieceSprite.ChessPieceSpriteType.RED_BISHOP;
				break;
			case BLACK:
				pieceType = ChessPieceSprite.ChessPieceSpriteType.BLACK_BISHOP;
				break;
			case GREEN:
				pieceType = ChessPieceSprite.ChessPieceSpriteType.GREEN_BISHOP;
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
		}
		else {
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
		}

		if(!isThisEnemy) {
			for (int y = 0; y < movableArr.length; y++) {
				for (int x = 0; x < movableArr[y].length; x++) {
					if (!movableArr[y][x]) continue;
					int[] origPos = this.getPosition().clone();

					ChessPiece[][] testStatus = gm.getBoard().getStatus();
					gm.getBoard().movePiece(testStatus, this, x, y);

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
				pieceType = ChessPieceSprite.ChessPieceSpriteType.WHITE_BISHOP;
				break;
			case RED:
				pieceType = ChessPieceSprite.ChessPieceSpriteType.RED_BISHOP;
				break;
			case BLACK:
				pieceType = ChessPieceSprite.ChessPieceSpriteType.BLACK_BISHOP;
				break;
			case GREEN:
				pieceType = ChessPieceSprite.ChessPieceSpriteType.GREEN_BISHOP;
				break;
		}

		setImage(ChessPieceSprite.getInstace().getChessPiece(pieceType));
	}
}