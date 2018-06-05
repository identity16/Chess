package core;

import javax.swing.*;
import java.awt.*;

public class GameView extends JPanel {

    public GameView(int numOfPlayers) {

        JButton btn_main = new JButton("Go To Main");
        JLabel label_turn = new JLabel();

        GameManager gm = null;
        try {
            gm = new GameManager(numOfPlayers);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assert gm != null;

        btn_main.addActionListener(e -> {
            Container parent = getParent();

            getParent().add(new MainView());
            getParent().remove(this);

            parent.validate();
        });

        label_turn.setText("Turn - " + gm.getCurrentTurn().getColor().toString());

        gm.getBoard().setTurnLabel(label_turn);

        add(btn_main, BorderLayout.NORTH);
        add(gm.getBoard(), BorderLayout.WEST);
        add(label_turn, BorderLayout.EAST);
    }

}