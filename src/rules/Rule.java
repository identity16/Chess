package rules;

import java.util.*;

import utils.Movement;

public interface Rule {


    public boolean IsCheck();

    public boolean IsCheckMate();

    public boolean IsStaleMate();

   

    public boolean IsPawnPromotion(Movement mv);

	public boolean IsCastling(Movement mv);

}