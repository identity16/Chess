package rules;

import core.Board;
import core.GameManager;
import core.Player;
import pieces.ChessPiece;
import pieces.King;


import pieces.Rook;
import utils.ChessColor;
import utils.Movement;

public class FourPlayersRule implements Rule {

    @Override
    public boolean IsCheck(Player player) {
        GameManager gm = GameManager.runningGame;
        Board board = gm.getBoard();
        ChessPiece[][] status = board.getStatus();

        int[] kingPosition = null;
        boolean[][] enemyMovable = new boolean[board.getN()][board.getN()];

        for(ChessPiece[] line : status) {
            for(ChessPiece piece : line) {
                if(piece == null) continue;

                if(piece.getColor() == player.getColor()) {
                    if(piece instanceof King)
                        kingPosition = piece.getPosition();
                } else if(piece.getColor() != gm.getAlly(player).getColor()) {
                    	piece.showMovableArea(status, enemyMovable, player);
                }
            }
        }

        if(kingPosition != null)
            return enemyMovable[kingPosition[1]][kingPosition[0]];

		return false;
    }

    @Override
    public boolean IsCheckMate(Player player) {
        GameManager gm = GameManager.runningGame;
        Board board = gm.getBoard();
        ChessPiece[][] status = board.getStatus();

        int[] kingPosition = null;
        boolean[][] enemyMovable;
        boolean[][] allyMovable = new boolean[board.getN()][board.getN()];

        for(ChessPiece[] line : status) {
            for(ChessPiece piece : line) {
                if(piece == null) continue;

                if(piece.getColor() == gm.getCurrentTurn().getColor()
                        || piece.getColor() == gm.getAlly(gm.getCurrentTurn()).getColor()) {
                    piece.showMovableArea(status, allyMovable);
                    if(piece instanceof King)
                        kingPosition = piece.getPosition();
                }
            }
        }

        if(kingPosition == null) return false;

        boolean isStillCheck = true;
        for(int y=0; y<board.getN(); y++) {
            for(int x=0; x<board.getN(); x++) {
                if(allyMovable[y][x]) {
                    status = board.getStatus();
                    enemyMovable = new boolean[board.getN()][board.getN()];
                    board.killPiece(status, status[y][x]);

                    for(ChessPiece[] line : status) {
                        for(ChessPiece piece : line) {
                            if(piece == null) continue;

                            if(piece.getColor() == gm.getCurrentTurn().getColor()
                                    || piece.getColor() == gm.getAlly(gm.getCurrentTurn()).getColor()) {
                                if(piece instanceof King)
                                    kingPosition = piece.getPosition();
                            } else {
                                piece.showMovableArea(status, enemyMovable);
                            }
                        }
                    }

                    if(!enemyMovable[kingPosition[1]][kingPosition[0]]) {
                        isStillCheck = false;
                        break;
                    }
                }
            }
            if(!isStillCheck)
                break;
		}

		return isStillCheck;
    }

    @Override
    public boolean IsStaleMate(Player player) {
        GameManager gm = GameManager.runningGame;
        Board board = gm.getBoard();
        ChessPiece[][] status = board.getStatus();

        boolean[][] allyMovable = new boolean[board.getN()][board.getN()];

        for(ChessPiece[] line : status) {
            for(ChessPiece piece : line) {
                if(piece == null) continue;

                if(piece.getColor() == gm.getCurrentTurn().getColor()
                        || piece.getColor() == gm.getAlly(gm.getCurrentTurn()).getColor()) {
                    piece.showMovableArea(status, allyMovable);
                }
            }
        }

        boolean isMovableAllFalse = true;
        for(boolean[] line : allyMovable) {
            for(boolean b : line) {
                if(b) {
                    isMovableAllFalse = false;
                    break;
                }
            }
        }

        return isMovableAllFalse && !IsCheck(player);
    }



	@Override
	public boolean IsCastling(Movement mv) {
    ChessColor playerColor = GameManager.runningGame.getCurrentTurn().getColor();
    ChessPiece piece = null;
    	if(IsCheck()== false ) {
    		if(piece instanceof King && piece.getMoveCount()==0 && piece instanceof Rook && piece.getMoveCount()==0) {
               switch(playerColor) {
    	       case RED:
    		       if(mv.getChessPiece().getPosition()[1]==3 && mv.getChessPiece().getPosition()[0]==0 && mv.getChessPiece().getPosition()[1]==10 && mv.getChessPiece().getPosition()[0]==0) {
    			  /*(1,3)&(1,10)&(1,6) movement=0 */
    		    	   if(mv.getChessPiece().getPosition()[1]!=4 && mv.getChessPiece().getPosition()[0]!=0 && mv.getChessPiece().getPosition()[1]!=5 && mv.getChessPiece().getPosition()[0]!=0 && mv.getChessPiece().getPosition()[1]!=9 && mv.getChessPiece().getPosition()[0]!=0 && mv.getChessPiece().getPosition()[1]!=8 && mv.getChessPiece().getPosition()[0]!=0)
    		    	       return true;
    		    	   else
    		    		   return false;
  		           }
    	       case BLACK:
    	    	   if(mv.getChessPiece().getPosition()[1]==0 && mv.getChessPiece().getPosition()[0]==3 && mv.getChessPiece().getPosition()[1]==0 && mv.getChessPiece().getPosition()[0]==10) {
       			  /*(1,3)&(1,10)&(1,6) movement=0 */
       		    	   if(mv.getChessPiece().getPosition()[1]!=0 && mv.getChessPiece().getPosition()[0]!=4 && mv.getChessPiece().getPosition()[1]!=0 && mv.getChessPiece().getPosition()[0]!=5 && mv.getChessPiece().getPosition()[1]!=0 && mv.getChessPiece().getPosition()[0]!=9 && mv.getChessPiece().getPosition()[1]!=0 && mv.getChessPiece().getPosition()[0]!=8)
       		    	       return true;
       		    	   else
       		    		   return false;
     		        }
    	       case GREEN:
    	    	   if(mv.getChessPiece().getPosition()[1]==10 && mv.getChessPiece().getPosition()[0]==13 && mv.getChessPiece().getPosition()[1]==3 && mv.getChessPiece().getPosition()[0]==13) {
       			  /*(1,3)&(1,10)&(1,6) movement=0 */
       		    	   if(mv.getChessPiece().getPosition()[1]!=9 && mv.getChessPiece().getPosition()[0]!=13 && mv.getChessPiece().getPosition()[1]!=8 && mv.getChessPiece().getPosition()[0]!=13 && mv.getChessPiece().getPosition()[1]!=4 && mv.getChessPiece().getPosition()[0]!=13 && mv.getChessPiece().getPosition()[1]!=5 && mv.getChessPiece().getPosition()[0]!=13)
       		    	       return true;
       		    	   else
       		    		   return false;
     		        }
    	        case WHITE:
    	    	    if(mv.getChessPiece().getPosition()[1]==13 && mv.getChessPiece().getPosition()[0]==3 && mv.getChessPiece().getPosition()[1]==13 && mv.getChessPiece().getPosition()[0]==10) {
       			  /*(1,3)&(1,10)&(1,6) movement=0 */
       		    	    if(mv.getChessPiece().getPosition()[1]!=13 && mv.getChessPiece().getPosition()[0]!=4 && mv.getChessPiece().getPosition()[1]!=13 && mv.getChessPiece().getPosition()[0]!=5 && mv.getChessPiece().getPosition()[1]!=13 && mv.getChessPiece().getPosition()[0]!=8 && mv.getChessPiece().getPosition()[1]!=13 && mv.getChessPiece().getPosition()[0]!=9)
       		    	       return true;
       		            else
       		    		   return false;
     		         }
    		    }
    		}
    	 }

    	/* �� ���� �޾ƿ���
    	  1)������ �� -> {(1,5)&&(1,6)������x}�Ӥ�{(1,9),(1,10)}������x? true -> {�����տ�����0 && (1,4)�� ������0}�Ӥ� {�����տ�����0 && (1,11)�� ������0}true -> return true;
     	   2)������ �� ->  {(5,1)&&(6,1)������x}�Ӥ�{(9,1),(10,1)}������x? true -> {�����տ�����0 && (4,1)�� ������0}�Ӥ� {�����տ�����0 && (11,1)�� ������0}true -> return true;
    	   3)�ʷϻ� �� ->  {(14,5)&&(14,6)������x}�Ӥ�{(14,9),(14,10)}������x? true -> {�տ�����0 && (14,4)�� ������0}�Ӥ� {�տ�����0 && (1,11)�� ������0}true -> return true;
    	   4)��� �� ->   {(5,14)&&(6,14)������x}�Ӥ�{(9,14),(10,14)}������x? true -> {�����տ�����0 && (4,14)�� ������0}�Ӥ� {�����տ�����0 && (11,14)�� ������0}true -> return true;
    	   */
		// TODO Auto-generated method stub
		return false;
	}










   }
