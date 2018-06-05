package core;

import javax.swing.*;
import java.awt.*;

class GameView extends JPanel {

    GameView(int numOfPlayers) {
        setLayout(new GridBagLayout());
        setBackground(Color.RED);

        GridBagConstraints gameViewC = new GridBagConstraints();
		gameViewC.fill = GridBagConstraints.BOTH;
		gameViewC.weighty = 1;


        JPanel gamePanel = new JPanel();
        JPanel infoPanel = new JPanel();

        JButton btn_main = new JButton("Go To Main");
        JLabel label_turn = new JLabel();

        gamePanel.setLayout(new GridBagLayout());
		infoPanel.setLayout(new GridBagLayout());

		GridBagConstraints gamePanelC = new GridBagConstraints();
		GridBagConstraints infoPanelC = new GridBagConstraints();


		gamePanel.setBackground(Color.WHITE);
		infoPanel.setBackground(Color.BLUE);


		try {
			GameManager gm = new GameManager(numOfPlayers);

			btn_main.addActionListener(e -> {
				Container parent = this.getParent();

				parent.add(new MainView());
				parent.remove(this);

				GameManager.runningGame = null;

				parent.validate();
			});

			label_turn.setText("Turn - " + gm.getCurrentTurn().getColor().toString());
			gm.getBoard().setTurnLabel(label_turn);

			gamePanel.add(gm.getBoard(), gamePanelC);
			infoPanel.add(label_turn, infoPanelC);
			infoPanel.add(btn_main, infoPanelC);

		} catch (Exception e) {
			e.printStackTrace();
		}

		gameViewC.weightx = 0.6;
		add(gamePanel, gameViewC);
		gameViewC.weightx = 0.4;
		add(infoPanel, gameViewC);
    }

}