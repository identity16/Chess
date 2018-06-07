package rules;

import java.util.*;


import core.GameManager;
import core.Player;
import pieces.Pawn;
import pieces.Rook;
import utils.ChessColor;
import utils.Direction;
import utils.Movement;
import pieces.ChessPiece;
import pieces.King;

public class FourPlayersRule implements Rule {
	
	
    
    public FourPlayersRule() {
    }


    @Override
    public boolean IsCheck() {
    	ChessPiece[][] status = GameManager.runningGame.getBoard().getStatus();
    	int kingPosition[];
    	boolean[][] movableArea = new boolean[14][14];
    	
    	/* ����Ͽ� ������ ������ ������ �� �ִ� ��� ���� ���� ��ĥ �Ǿ� �ֳ�? true -> Check
    	  
    	*/
        return false;
    }

    @Override
    public boolean IsCheckMate() {
    	if(IsCheck() == true) {
    		
    	}
    	else {
    		
    	}
    	/*IsCheck? True -> (���� �ٲ�� ���� �Ǵ�)for(���� �� �������� �տ� ��ĥ�� �ǳ�? true) -> CheckMate    	  
    	 */
        return false;
    }

    @Override
    public boolean IsStaleMate() {
    	
    	if(IsCheck() == false) {
    		
    	}
       /* ������ �� �ִ� �� Ȯ�� -> �ջ��ΰ�?? true -> IsCheck ? False -> for(�� ������ ����  ���� �� ����? true) -> StaleMate
         
        */  return false;
        
    }

    

    @Override
    public boolean IsPawnPromotion(Movement mv) {
    	Direction playerDirection;
    	ChessPiece piece = mv.getChessPiece();
    	if(piece instanceof Pawn) {
    		playerDirection = ((Pawn) piece).getDirection();
    		
	    	switch(playerDirection) {
	    		case EAST : 
	    			if(piece.getPosition()[0]==7)
	    			   return true;
	    			
	    		case SOUTH:
	    			if(piece.getPosition()[1]==7)
	    			   return true;
	    			
	    		case WEST:
	    			if(piece.getPosition()[0]==8)
	    			   return true;
	    			
	    		case NORTH:
	    			if(piece.getPosition()[1]==8)
	    			   return true;
	    			
	    		}
	    	
    	}
    	
    		
    	/* ������ �޾ƿ���
    	   1)������ �� -> 8��° �࿡ ���� �����ϴ°�? true -> �� ���� ������ ���ΰ�? true -> return true;  
     	   2)������ �� -> 8��° ���� ���� �����ϴ°�? true -> �� ���� ������ ���ΰ�? true -> return true;
    	   3)�ʷϻ� �� -> 7��° �࿡ ���� �����ϴ°�? true -> �� ���� �ʷϻ� ���ΰ�? true -> return true;
    	   4)��� �� ->  7��° ���� ���� �����ϴ°�? true -> �� ���� ��� ���ΰ�? true -> retrun true;
    	   */
        return false;
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
