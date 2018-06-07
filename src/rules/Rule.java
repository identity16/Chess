package rules;

import core.Player;

import pieces.King;
import utils.Movement;

public interface Rule {


    boolean IsCheck(Player player);

    boolean IsCheckMate(Player player);

    boolean IsStaleMate(Player player);



    boolean IsPawnPromotion(Movement mv);

	int[][] IsCastling(King king);
}