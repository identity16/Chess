package rules;

import java.util.*;

import utils.Movement;

public class TwoPlayersRule implements Rule {

    public TwoPlayersRule() {
    }


    @Override
    public boolean IsCheck() {
        return false;
    }

    @Override
    public boolean IsCheckMate() {
        return false;
    }

    @Override
    public boolean IsStaleMate() {
        return false;
    }

    

    @Override
    public boolean IsPawnPromotion(Movement mv) {
        return false;
    }


	@Override
	public boolean IsCastling(Movement mv) {
		// TODO Auto-generated method stub
		return false;
	}
   
}

    