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
    	
    	/* 상대턴에 끝난후 상대방이 움직일 수 있는 모든 곳에 왕이 색칠 되어 있나? true -> Check
    	  
    	*/
        return false;
    }

    @Override
    public boolean IsCheckMate() {
    	if(IsCheck() == true) {
    		
    	}
    	else {
    		
    	}
    	/*IsCheck? True -> (턴이 바뀌기 전에 판단)for(나의 말 움직여도 왕에 색칠이 되나? true) -> CheckMate    	  
    	 */
        return false;
    }

    @Override
    public boolean IsStaleMate() {
    	
    	if(IsCheck() == false) {
    		
    	}
       /* 움직일 수 있는 말 확인 -> 왕뿐인가?? true -> IsCheck ? False -> for(왕 움직인 곳에  상대방 말 존재? true) -> StaleMate
         
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
    	
    		
    	/* 턴정보 받아오기
    	   1)빨간색 턴 -> 8번째 행에 말이 존재하는가? true -> 그 말이 빨간색 폰인가? true -> return true;  
     	   2)검정색 턴 -> 8번째 열에 말이 존재하는가? true -> 그 말이 검정색 폰인가? true -> return true;
    	   3)초록색 턴 -> 7번째 행에 말이 존재하는가? true -> 그 말이 초록색 폰인가? true -> return true;
    	   4)흰색 턴 ->  7번째 열에 말이 존재하는가? true -> 그 말이 흰색 폰인가? true -> retrun true;
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
        
    	/* 턴 정보 받아오기
    	  1)빨간색 턴 -> {(1,5)&&(1,6)말존재x}ㅣㅣ{(1,9),(1,10)}말존재x? true -> {빨강왕움직임0 && (1,4)룩 움직임0}ㅣㅣ {빨강왕움직임0 && (1,11)룩 움직임0}true -> return true;  
     	   2)검정색 턴 ->  {(5,1)&&(6,1)말존재x}ㅣㅣ{(9,1),(10,1)}말존재x? true -> {검정왕움직임0 && (4,1)룩 움직임0}ㅣㅣ {검정왕움직임0 && (11,1)룩 움직임0}true -> return true;
    	   3)초록색 턴 ->  {(14,5)&&(14,6)말존재x}ㅣㅣ{(14,9),(14,10)}말존재x? true -> {왕움직임0 && (14,4)룩 움직임0}ㅣㅣ {왕움직임0 && (1,11)룩 움직임0}true -> return true;
    	   4)흰색 턴 ->   {(5,14)&&(6,14)말존재x}ㅣㅣ{(9,14),(10,14)}말존재x? true -> {검정왕움직임0 && (4,14)룩 움직임0}ㅣㅣ {검정왕움직임0 && (11,14)룩 움직임0}true -> return true;
    	   */
		// TODO Auto-generated method stub
		return false;
	}


	


	


	

   }
