package views;

import core.GameManager;
import core.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class GameView extends JPanel {

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

			btn_draw.setBackground(new Color(0xB7, 0x68, 0x00));
			btn_surr.setBackground(new Color(0xB7, 0x68, 0x00));

			btn_draw.setContentAreaFilled(false);
			btn_surr.setContentAreaFilled(false);

			btn_draw.setOpaque(true);
			btn_surr.setOpaque(true);

			btn_draw.setBorderPainted(false);
			btn_surr.setBorderPainted(false);

			btn_draw.setForeground(Color.WHITE);
			btn_surr.setForeground(Color.WHITE);


			btn_draw.setFont(new Font("Sans-Serif", Font.BOLD, 25));
			btn_surr.setFont(new Font("Sans-Serif", Font.BOLD, 25));

			btn_draw.setPreferredSize(new Dimension(239, 60));
			btn_surr.setPreferredSize(new Dimension(239, 60));

			// Button Action Listeners
			btn_draw.addActionListener(e -> {
				gm.getCurrentTurn().setDrawRequest(true);

				// 적의 말 중에서 Draw 요청이 있으면 무승부로 끝
				for(Player p : gm.getPlayers()) {
					if(p != gm.getCurrentTurn()
							&& p != gm.getAlly(gm.getCurrentTurn())) {

						if(p.getDrawRequest()) {
							endGame(null);
							return;
						}
					}
				}
			});

			btn_surr.addActionListener(e -> {
				gm.getCurrentTurn().setSurrenderRequest(true);

				// 1 vs 1
				if(gm.getAlly(gm.getCurrentTurn()) == null) {
					gm.changeTurn();

					endGame(gm.getCurrentTurn());

				}
				// 2 vs 2
				else {

					// 팀원까지 항복한 경우
					if(!gm.getAlly(gm.getCurrentTurn()).isAlive()
							|| gm.getAlly(gm.getCurrentTurn()).getSurrenderRequest()) {
						gm.changeTurn();

						endGame(gm.getCurrentTurn(), gm.getAlly(gm.getCurrentTurn()));

					}
				}
			});



			// Turn Label
			String turn_color = gm.getCurrentTurn().getColor().toString();
			label_turn.setAlignmentX(CENTER_ALIGNMENT);
			label_turn.setFont(new Font("Sans-Serif", Font.BOLD, 45));
			label_turn.setText("<html><font color='"+turn_color+"'>"+turn_color+"</font> TURN</html>");
			gm.getBoard().setTurnLabel(label_turn);


			// Component 배치
			label_turn.setHorizontalAlignment(SwingConstants.CENTER);
			btn_draw.setAlignmentX(CENTER_ALIGNMENT);
			btn_surr.setAlignmentX(CENTER_ALIGNMENT);

			gamePanel.add(gm.getBoard(), gamePanelC);

			infoPanel.add(Box.createVerticalGlue());
			infoPanel.add(label_turn, infoPanelC);
			infoPanel.add(Box.createVerticalStrut(50));
			infoPanel.add(btn_draw, infoPanelC);
			infoPanel.add(Box.createVerticalStrut(20));
			infoPanel.add(btn_surr, infoPanelC);
			infoPanel.add(Box.createVerticalGlue());

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

	public void endGame(Player winner) {
		Container parent = this.getParent();

		parent.add(new ResultView(winner));
		parent.remove(this);

		GameManager.runningGame = null;

		parent.validate();
	}

	public void endGame(Player winner1, Player winner2) {
		Container parent = this.getParent();

		parent.add(new ResultView(winner1, winner2));
		parent.remove(this);

		GameManager.runningGame = null;

		parent.validate();
	}

}