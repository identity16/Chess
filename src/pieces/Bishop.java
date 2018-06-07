package pieces;

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
	
	public boolean[][] showMovableArea(ChessPiece[][] status, boolean[][] movableArr) {
		GameManager gm = GameManager.runningGame;
    	ChessPiece selectedPiece = gm.getBoard().getSelectedPiece();
		int[] location = selectedPiece.getPosition();
		int i, j;
		
		if(gm.getNumOfPlayers() == 2) {
			if(selectedPiece.getColor() == (gm.getCurrentTurn()).getColor()) {
				//right up cross
				for(i = location[1] - 1, j = location[0] + 1;i >= 0 && j <= 7;i--, j++) {
					if(status[i][j] == null)
						movableArr[i][j] = true;
					else if(isEnemy(status[i][j]) == true) {
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
					else if(isEnemy(status[i][j]) == true) {
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
					else if(isEnemy(status[i][j]) == true) {
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
					else if(isEnemy(status[i][j]) == true) {
						movableArr[i][j] = true;
						break;
					}
					else if(isEnemy(status[i][j]) == false)
						break;
				}	
			}
		}
		else {
			if(selectedPiece.getColor() == gm.getCurrentTurn().getColor()) {
				//right up cross
				for(i = location[1] - 1, j = location[0] + 1;i >= 0 && j <= 13;i--, j++) {
					if(status[i][j] == null)
						movableArr[i][j] = true;
					else if(isEnemy(status[i][j]) == true) {
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
					else if(isEnemy(status[i][j]) == true) {
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
					else if(isEnemy(status[i][j]) == true) {
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
					else if(isEnemy(status[i][j]) == true) {
						movableArr[i][j] = true;
						break;
					}
					else if(isEnemy(status[i][j]) == false)
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