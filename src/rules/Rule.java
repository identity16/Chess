package rules;

import core.Player;

import pieces.ChessPiece;
import pieces.King;
import utils.Movement;

import java.util.List;

public interface Rule {


    boolean IsCheck(Player player);
	boolean IsCheck(ChessPiece[][] status, Player player);

    boolean IsCheckMate(Player player);

    boolean IsStaleMate(Player player);



    boolean IsPawnPromotion(Movement mv);

	List<int[][]> IsCastling(King king);
}