package views;

import core.GameManager;
import core.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

class GameView extends JPanel {

    GameView(int numOfPlayers) {
        setLayout(new GridBagLayout());

        GridBagConstraints gameViewC = new GridBagConstraints();
		gameViewC.fill = GridBagConstraints.BOTH;
		gameViewC.weighty = 1;

		// Panels
        JPanel gamePanel = new JPanel();
        gamePanel.setLayout(new GridBagLayout());
		gamePanel.setBackground(new Color(0, 0,0, 0));

        JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
		infoPanel.setBackground(new Color(0xD5, 0x97, 0x59));

		GridBagConstraints gamePanelC = new GridBagConstraints();
		GridBagConstraints infoPanelC = new GridBagConstraints();


        JButton btn_draw = new JButton("무승부 요청");
		JButton btn_surr = new JButton("항복 요청");
        JLabel label_turn = new JLabel();

		try {
			GameManager gm = new GameManager(numOfPlayers);


			// Button Action Listeners
			btn_draw.addActionListener(e -> {
				gm.getCurrentTurn().setDrawRequest(true);

				// 적의 말 중에서 Draw 요청이 있으면 무승부로 끝
				for(Player p : gm.getPlayers()) {
					if(p != gm.getCurrentTurn()
							&& p != gm.getAlly(gm.getCurrentTurn())) {

						if(p.getDrawRequest()) {
							Container parent = this.getParent();

							parent.add(new ResultView(null));
							parent.remove(this);

							GameManager.runningGame = null;

							parent.validate();
							return;
						}
					}
				}
			});





			// Turn Label
			String turn_color = gm.getCurrentTurn().getColor().toString();
			label_turn.setAlignmentX(CENTER_ALIGNMENT);
			label_turn.setFont(new Font("Sans-Serif", Font.BOLD, 35));
			label_turn.setText("<html><font color='"+turn_color+"'>"+turn_color+"</font> TURN</html>");
			gm.getBoard().setTurnLabel(label_turn);

			label_turn.setHorizontalAlignment(SwingConstants.CENTER);
			btn_draw.setAlignmentX(CENTER_ALIGNMENT);

			gamePanel.add(gm.getBoard(), gamePanelC);

			infoPanel.add(Box.createVerticalStrut(50));
			infoPanel.add(label_turn, infoPanelC);
			infoPanel.add(Box.createVerticalGlue());
			infoPanel.add(btn_draw, infoPanelC);
			infoPanel.add(Box.createVerticalStrut(50));

		} catch (Exception e) {
			e.printStackTrace();
		}

		gamePanel.setPreferredSize(new Dimension((int)(getWidth()*0.6), getHeight()));
		infoPanel.setPreferredSize(new Dimension((int)(getWidth()*0.4), getHeight()));

		gameViewC.weightx = 0.6;
		add(gamePanel, gameViewC);
		gameViewC.weightx = 0.4;
		add(infoPanel, gameViewC);
    }


	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		try {
			Image bg = ImageIO.read(new File("wooden_bg.jpg"));
			g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}