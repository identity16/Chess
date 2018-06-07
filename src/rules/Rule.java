package rules;

import core.Player;

import utils.Movement;

public interface Rule {


    boolean IsCheck(Player player);

    boolean IsCheckMate(Player player);

    boolean IsStaleMate(Player player);



    public boolean IsPawnPromotion(Movement mv);

	public boolean IsCastling(Movement mv);

}