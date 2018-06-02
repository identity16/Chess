package rules;

import java.util.*;

public interface Rule {


    public boolean IsCheck();

    public boolean IsCheckMate();

    public boolean IsStaleMate();

    public boolean IsCastling();

    public boolean IsPawnPromotion();

    public boolean IsEnpassant();

}