package rules;

import java.util.*;

public class FourPlayersRule implements Rule {

    public FourPlayersRule() {
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
    public boolean IsCastling() {
        return false;
    }

    @Override
    public boolean IsPawnPromotion() {
        return false;
    }

    @Override
    public boolean IsEnpassant() {
        return false;
    }
}