package rules;

import core.Player;

public interface Rule {


    boolean IsCheck(Player player);

    boolean IsCheckMate(Player player);

    boolean IsStaleMate(Player player);

    boolean IsCastling();

    boolean IsPawnPromotion();

    boolean IsEnpassant();

}