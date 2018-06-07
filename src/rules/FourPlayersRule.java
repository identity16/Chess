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
    	
    	
    	/* 雌企渡拭 魁貝板 雌企号戚 崇送析 呪 赤澗 乞窮 員拭 腎戚 事張 鞠嬢 赤蟹? true -> Check
    	  
    	*/
        return false;
    }

    @Override
    public boolean IsCheckMate() {
    	if(IsCheck() == true) {
    		
    	}
    	else {
    		
    	}
    	/*IsCheck? True -> (渡戚 郊餓奄 穿拭 毒舘)for(蟹税 源 崇送食亀 腎拭 事張戚 鞠蟹? true) -> CheckMate    	  
    	 */
        return false;
    }

    @Override
    public boolean IsStaleMate() {
    	
    	if(IsCheck() == false) {
    		
    	}
       /* 崇送析 呪 赤澗 源 溌昔 -> 腎屍昔亜?? true -> IsCheck ? False -> for(腎 崇送昔 員拭  雌企号 源 糎仙? true) -> StaleMate
         
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
    	
    		
    	/* 渡舛左 閤焼神奄
    	   1)察娃事 渡 -> 8腰属 楳拭 源戚 糎仙馬澗亜? true -> 益 源戚 察娃事 肉昔亜? true -> return true;  
     	   2)伊舛事 渡 -> 8腰属 伸拭 源戚 糎仙馬澗亜? true -> 益 源戚 伊舛事 肉昔亜? true -> return true;
    	   3)段系事 渡 -> 7腰属 楳拭 源戚 糎仙馬澗亜? true -> 益 源戚 段系事 肉昔亜? true -> return true;
    	   4)避事 渡 ->  7腰属 伸拭 源戚 糎仙馬澗亜? true -> 益 源戚 避事 肉昔亜? true -> retrun true;
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
        
    	/* 渡 舛左 閤焼神奄
    	  1)察娃事 渡 -> {(1,5)&&(1,6)源糎仙x}びび{(1,9),(1,10)}源糎仙x? true -> {察悪腎崇送績0 && (1,4)決 崇送績0}びび {察悪腎崇送績0 && (1,11)決 崇送績0}true -> return true;  
     	   2)伊舛事 渡 ->  {(5,1)&&(6,1)源糎仙x}びび{(9,1),(10,1)}源糎仙x? true -> {伊舛腎崇送績0 && (4,1)決 崇送績0}びび {伊舛腎崇送績0 && (11,1)決 崇送績0}true -> return true;
    	   3)段系事 渡 ->  {(14,5)&&(14,6)源糎仙x}びび{(14,9),(14,10)}源糎仙x? true -> {腎崇送績0 && (14,4)決 崇送績0}びび {腎崇送績0 && (1,11)決 崇送績0}true -> return true;
    	   4)避事 渡 ->   {(5,14)&&(6,14)源糎仙x}びび{(9,14),(10,14)}源糎仙x? true -> {伊舛腎崇送績0 && (4,14)決 崇送績0}びび {伊舛腎崇送績0 && (11,14)決 崇送績0}true -> return true;
    	   */
		// TODO Auto-generated method stub
		return false;
	}


	


	


	

   }
