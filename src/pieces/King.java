package pieces;

import kr.ac.cau.mecs.lenerd.chess.ChessPieceSprite;
import utils.ChessColor;

import java.util.*;

import core.GameManager;

public class King extends ChessPiece {

    public King(int x, int y, ChessColor color) {
        super(x, y, color);
        ChessPieceSprite.ChessPieceSpriteType pieceType = null;

        switch(color) {
            case WHITE:
                pieceType = ChessPieceSprite.ChessPieceSpriteType.WHITE_KING;
                break;
            case RED:
                pieceType = ChessPieceSprite.ChessPieceSpriteType.RED_KING;
                break;
            case BLACK:
                pieceType = ChessPieceSprite.ChessPieceSpriteType.BLACK_KING;
                break;
            case GREEN:
                pieceType = ChessPieceSprite.ChessPieceSpriteType.GREEN_KING;
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
    public void setColor(ChessColor color) {
        this.color = color;

        ChessPieceSprite.ChessPieceSpriteType pieceType = null;

        switch (color) {
            case WHITE:
                pieceType = ChessPieceSprite.ChessPieceSpriteType.WHITE_KING;
                break;
            case RED:
                pieceType = ChessPieceSprite.ChessPieceSpriteType.RED_KING;
                break;
            case BLACK:
                pieceType = ChessPieceSprite.ChessPieceSpriteType.BLACK_KING;
                break;
            case GREEN:
                pieceType = ChessPieceSprite.ChessPieceSpriteType.GREEN_KING;
                break;
        }

        setImage(ChessPieceSprite.getInstace().getChessPiece(pieceType));
    }
    
    
    public boolean[][] showMovableArea(ChessPiece[][] status, boolean[][] movableArr) {
    	GameManager gm = GameManager.runningGame;
    	ChessPiece selectedPiece = gm.getBoard().getSelectedPiece();
    	int[] location = selectedPiece.getPosition();
    	int i, j;

    	if(gm.getNumOfPlayers() == 2) {
    		//���� �� ��ġ üũ(1��1)
	    	for(i = 0;i < 8;i++) {
	    		for(j = 0;j < 8;j++) {
	    			ChessPiece p = status[i][j];
	    			if(p == null) {
	    				continue;
	    			}
	    			
	    			int[] enemyKing = p.getPosition();
	    			
	    			if(isEnemy(p) == true) {
	    				if(p instanceof King == true) {
	    					if((enemyKing[1] - 1) >= 0) {
    		    				if(status[enemyKing[1] - 1][enemyKing[0]] == null) {
    		    					movableArr[enemyKing[1] - 1][enemyKing[0]] = true;
    		    				}
    		    			}
	    					if((enemyKing[1] - 1) >= 0 && (enemyKing[0] + 1) <= 7) {
    		    				if(status[enemyKing[1] - 1][enemyKing[0] + 1] == null) {
    		    					movableArr[enemyKing[1] - 1][enemyKing[0] + 1] = true;
    		    				}
    		    			}
	    					if((enemyKing[0] + 1) <= 7) {
    		    				if(status[enemyKing[1]][enemyKing[0] + 1] == null) {
    		    					movableArr[enemyKing[1]][enemyKing[0] + 1] = true;
    		    				}
    		    			}
	    					if((enemyKing[1] + 1) <= 7 && (enemyKing[0] + 1) <= 7) {
    		    				if(status[enemyKing[1] + 1][enemyKing[0] + 1] == null) {
    		    					movableArr[enemyKing[1] + 1][enemyKing[0] + 1] = true;
    		    				}
    		    			}
	    					if((enemyKing[1] + 1) <= 7) {
    		    				if(status[enemyKing[1] + 1][enemyKing[0]] == null) {
    		    					movableArr[enemyKing[1] + 1][enemyKing[0]] = true;
    		    				}
    		    			}
	    					if((enemyKing[1] + 1) <= 7 && (enemyKing[0] - 1) >= 0) {
    		    				if(status[enemyKing[1] + 1][enemyKing[0] - 1] == null) {
    		    					movableArr[enemyKing[1] + 1][enemyKing[0] - 1] = true;
    		    				}
		    				}
	    					if((enemyKing[0] - 1) >= 0) {
    		    				if(status[enemyKing[1]][enemyKing[0] - 1] == null) {
    		    					movableArr[enemyKing[1]][enemyKing[0] - 1] = true;
    		    				}
    		    			}
	    					if((enemyKing[1] - 1) >= 0 && (enemyKing[0] - 1 >= 0)) {
    		    				if(status[enemyKing[1] - 1][enemyKing[0] - 1] == null) {
    		    					movableArr[enemyKing[1] - 1][enemyKing[0] - 1] = true;
    		    				}
    		    			}
	    				}
	    				else {
	    					p.showMovableArea(status, movableArr);
    		    		}
    				}
	    			else
	    				continue;
	    		}
    		}

	    	//�츮�� ŷ ��ġ üũ(1��1)
	    	if(selectedPiece.getColor() == gm.getCurrentTurn().getColor()) {
	    		if((location[1] - 1) >= 0) {
	    			if(movableArr[location[1] - 1][location[0]] == false) {
	    				if(status[location[1] - 1][location[0]] == null ||
								isEnemy(status[location[1] - 1][location[0]]) == true) {
	    					movableArr[location[1] - 1][location[0]] = true;
	    				}
	    				else
	    					movableArr[location[1] - 1][location[0]] = false;
	    			}
	    			else
	    				movableArr[location[1] - 1][location[0]] = false;
	    		}
	    		
	    		if((location[1] - 1) >= 0 && (location[0] + 1) <= 7) {
	    			if(movableArr[location[1] - 1][location[0] + 1] == false) {
	    				if(status[location[1] - 1][location[0] + 1] == null ||
								isEnemy(status[location[1] - 1][location[0] + 1]) == true) {
	    					movableArr[location[1] - 1][location[0] + 1] = true;
	    				}
	    				else
	    					movableArr[location[1] - 1][location[0] + 1] = false;
	    			}
	    			else
	    				movableArr[location[1] - 1][location[0] + 1] = false;
	    		}
	    		
	    		if((location[0] + 1) <= 7) {
	    			if(movableArr[location[1]][location[0] + 1] == false) {
	    				if(status[location[1]][location[0] + 1] == null ||
								isEnemy(status[location[1]][location[0] + 1]) == true) {
	    					movableArr[location[1]][location[0] + 1] = true;
	    				}
	    				else
	    					movableArr[location[1]][location[0] + 1] = false;
	    			}
	    			else
	    				movableArr[location[1]][location[0] + 1] = false;
	    		}
	    		
	    		if((location[1] + 1) <= 7 && (location[0] + 1) <= 7) {
	    			if(movableArr[location[1] + 1][location[0] + 1] == false) {
	    				if(status[location[1] + 1][location[0] + 1] == null ||
								isEnemy(status[location[1] + 1][location[0] + 1]) == true) {
	    					movableArr[location[1] + 1][location[0] + 1] = true;
	    				}
	    				else
	    					movableArr[location[1] + 1][location[0] + 1] = false;
	    			}
	    			else
	    				movableArr[location[1] + 1][location[0] + 1] = false;
	    		}
	    		
	    		if((location[1] + 1) <= 7) {
	    			if(movableArr[location[1] + 1][location[0]] == false) {
	    				if(status[location[1] + 1][location[0]] == null ||
								isEnemy(status[location[1] + 1][location[0]]) == true) {
	    					movableArr[location[1] + 1][location[0]] = true;
	    				}
	    				else
	    					movableArr[location[1] + 1][location[0]] = false;
	    			}
	    			else
	    				movableArr[location[1] + 1][location[0]] = false;
	    		}
	    		
	    		if((location[1] + 1) <= 7 && (location[0] - 1) >= 0) {
	    			if(movableArr[location[1] + 1][location[0] - 1] == false) {
	    				if(status[location[1] + 1][location[0] - 1] == null ||
								isEnemy(status[location[1] + 1][location[0] - 1]) == true) {
	    					movableArr[location[1] + 1][location[0] - 1] = true;
	    				}
	    				else
	    					movableArr[location[1] + 1][location[0] - 1] = false;
	    			}
	    			else
	    				movableArr[location[1] + 1][location[0] - 1] = false;
	    		}
	    		
	    		if((location[0] - 1) >= 0) {
	    			if(movableArr[location[1]][location[0] - 1] == false) {
	    				if(status[location[1]][location[0] - 1] == null ||
								isEnemy(status[location[1]][location[0] - 1]) == true) {
	    					movableArr[location[1]][location[0] - 1] = true;
	    				}
	    				else
	    					movableArr[location[1]][location[0] - 1] = false;
	    			}
	    			else
	    				movableArr[location[1]][location[0] - 1] = false;
	    		}
	    		
	    		if((location[1] - 1) >= 0 && (location[0] - 1) >= 0) {
	    			if(movableArr[location[1] - 1][location[0] - 1] == false) {
	    				if(status[location[1] - 1][location[0] - 1] == null ||
								isEnemy(status[location[1] - 1][location[0] - 1]) == true) {
	    					movableArr[location[1] - 1][location[0] - 1] = true;
	    				}
	    				else
	    					movableArr[location[1] - 1][location[0] - 1] = false;
	    			}
	    			else
	    				movableArr[location[1] - 1][location[0] - 1] = false;
	    		}
    		}
	    	
	    	for(i = 0;i < 8;i++) {
	    		for(j = 0;j < 8;j++) {
	    			if((i == (location[1] - 1) && j == location[0]) ||(i == (location[1] - 1) && j == (location[0] + 1)) ||
	    					(i == location[1] && j == (location[0] + 1)) ||(i == (location[1] + 1) && j == (location[0] + 1)) ||
	    					(i == (location[1] + 1) && j == location[0]) ||(i == (location[1] + 1) && j == (location[0] - 1)) ||
	    					(i == location[1] && j == (location[0] - 1)) ||(i == (location[1] - 1) && j == (location[0] - 1)))
	    				continue;
	    			
	    			movableArr[i][j] = false;
	    		}
	    	}
    	}

    	//���� �� ��ġ üũ(2��2)
    	else {
    		//���� �� ��ġ üũ(1��1)
    		boolean[][] kingMove = new boolean[14][14];
	    	for(i = 0;i < 14;i++) {
	    		for(j = 0;j < 14;j++) {
	    			ChessPiece p = status[i][j];
	    			if(p == null)
	    				continue;

	    			int[] enemyKing = p.getPosition();

	    			if(isEnemy(status[i][j]) == true) {
	    				if(p instanceof King == false)
	    					status[i][j].showMovableArea(status, movableArr);
	    				else {
	    					if((enemyKing[1] - 1) >= 0) {
    		    				if(status[enemyKing[1] - 1][enemyKing[0]] == null ||
										isEnemy(status[enemyKing[1] - 1][enemyKing[0]]) == true)
    		    					movableArr[enemyKing[1] - 1][enemyKing[0]] = true;
    		    			}
	    					if((enemyKing[1] - 1) >= 0 && (enemyKing[0] + 1) <= 13) {
    		    				if(status[enemyKing[1] - 1][enemyKing[0] + 1] == null ||
										isEnemy(status[enemyKing[1] - 1][enemyKing[0] + 1]) == true)
    		    					movableArr[enemyKing[1] - 1][enemyKing[0] + 1] = true;
    		    			}
	    					if((enemyKing[0] + 1) <= 13) {
    		    				if(status[enemyKing[1]][enemyKing[0] + 1] == null ||
										isEnemy(status[enemyKing[1]][enemyKing[0] + 1]) == true)
    		    					movableArr[enemyKing[1]][enemyKing[0] + 1] = true;
    		    			}
	    					if((enemyKing[1] + 1) <= 13 && (enemyKing[0] + 1) <= 13) {
    		    				if(status[enemyKing[1] + 1][enemyKing[0] + 1] == null ||
										isEnemy(status[enemyKing[1] + 1][enemyKing[0] + 1]) == true)
    		    					movableArr[enemyKing[1] + 1][enemyKing[0] + 1] = true;
    		    			}
	    					if((enemyKing[1] + 1) <= 13) {
    		    				if(status[enemyKing[1] + 1][enemyKing[0]] == null ||
										isEnemy(status[enemyKing[1] + 1][enemyKing[0]]) == true)
    		    					movableArr[enemyKing[1] + 1][enemyKing[0]] = true;
    		    			}
	    					if((enemyKing[1] + 1) <= 13 && (enemyKing[0] - 1) >= 0) {
    		    				if(status[enemyKing[1] + 1][enemyKing[0] - 1] == null ||
										isEnemy(status[enemyKing[1] + 1][enemyKing[0] - 1]) == true)
    		    					movableArr[enemyKing[1] + 1][enemyKing[0] - 1] = true;
    		    			}
	    					if((enemyKing[0] - 1) >= 0) {
    		    				if(status[enemyKing[1]][enemyKing[0] - 1] == null ||
										isEnemy(status[enemyKing[1]][enemyKing[0] - 1]) == true)
    		    					movableArr[enemyKing[1]][enemyKing[0] - 1] = true;
    		    			}
	    					if((enemyKing[1] - 1) >= 0 && (enemyKing[0] - 1 >= 0)) {
    		    				if(status[enemyKing[1] - 1][enemyKing[0] - 1] == null ||
										isEnemy(status[enemyKing[1] - 1][enemyKing[0] - 1]) == true)
    		    					movableArr[enemyKing[1] - 1][enemyKing[0] - 1] = true;
    		    			}
    		    		}
    				}
    			}
    		}

	    	//�츮�� ŷ ��ġ üũ(1��1)
	    	if(selectedPiece.getColor() == gm.getCurrentTurn().getColor()) {
	    		if((location[1] - 1) >= 0) {
	    			if(movableArr[location[1] - 1][location[0]] == false) {
	    				if(status[location[1] - 1][location[0]] == null ||
								isEnemy(status[location[1] - 1][location[0]]) == true)
	    					kingMove[location[1] - 1][location[0]] = true;
	    			}
	    		}
	    		if((location[1] - 1) >= 0 && (location[0] + 1) <= 13) {
	    			if(movableArr[location[1] - 1][location[0] + 1] == false) {
	    				if(status[location[1] - 1][location[0] + 1] == null ||
								isEnemy(status[location[1] - 1][location[0] + 1]) == true)
	    					kingMove[location[1] - 1][location[0] + 1] = true;
	    			}
	    		}
	    		if((location[0] + 1) <= 13) {
	    			if(movableArr[location[1]][location[0] + 1] == false) {
	    				if(status[location[1]][location[0] + 1] == null ||
								isEnemy(status[location[1]][location[0] + 1]) == true)
	    					kingMove[location[1]][location[0] + 1] = true;
	    			}
	    		}
	    		if((location[1] + 1) <= 13 && (location[0] + 1) <= 13) {
	    			if(movableArr[location[1] + 1][location[0] + 1] == false) {
	    				if(status[location[1] + 1][location[0] + 1] == null ||
								isEnemy(status[location[1] + 1][location[0] + 1]) == true)
	    					kingMove[location[1] + 1][location[0] + 1] = true;
	    			}
	    		}
	    		if((location[1] + 1) <= 13) {
	    			if(movableArr[location[1] + 1][location[0]] == false) {
	    				if(status[location[1] + 1][location[0]] == null ||
								isEnemy(status[location[1] + 1][location[0]]) == true)
	    					kingMove[location[1] + 1][location[0]] = true;
	    			}
	    		}
	    		if((location[1] + 1) <= 13 && (location[0] - 1) >= 0) {
	    			if(movableArr[location[1] + 1][location[0] - 1] == false) {
	    				if(status[location[1] + 1][location[0] - 1] == null ||
								isEnemy(status[location[1] + 1][location[0] - 1]) == true)
	    					kingMove[location[1] + 1][location[0] - 1] = true;
	    			}
	    		}
	    		if((location[0] - 1) >= 0) {
	    			if(movableArr[location[1]][location[0] - 1] == false) {
	    				if(status[location[1]][location[0] - 1] == null ||
								isEnemy(status[location[1]][location[0] - 1]) == true)
	    					kingMove[location[1]][location[0] - 1] = true;
	    			}
	    		}
	    		if((location[1] - 1) >= 0 && (location[0] - 1) >= 0) {
	    			if(movableArr[location[1] - 1][location[0] - 1] == false) {
	    				if(status[location[1] - 1][location[0] - 1] == null ||
								isEnemy(status[location[1] - 1][location[0] - 1]) == true)
	    					kingMove[location[1] - 1][location[0] - 1] = true;
	    			}
	    		}
    		}
	    	
    		movableArr = kingMove;
    	}

    	return movableArr;
    }
}