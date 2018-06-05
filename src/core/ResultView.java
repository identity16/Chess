package core;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class ResultView extends JPanel {

	private ResultView() {
		setLayout(new GridBagLayout());
		addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Container parent = getParent();

				parent.add(new MainView());
				parent.remove((Component) e.getSource());

				GameManager.runningGame = null;

				parent.validate();
			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}
		});
	}

	ResultView(Player winner) {
		this();

		GridBagConstraints c = new GridBagConstraints();
		JLabel label_winner = new JLabel(winner.getColor().toString());


		Color bgColor = null;
		Color textColor = Color.BLACK;

		switch (winner.getColor()) {
			case BLACK:
				bgColor = Color.BLACK;
				textColor = Color.WHITE;
				break;
			case RED:
				bgColor = Color.RED;
				break;
			case WHITE:
				bgColor = Color.WHITE;
				break;
			case GREEN:
				bgColor = Color.GREEN;
				break;
		}

		c.weighty = 1;

		setBackground(bgColor);

		label_winner.setForeground(textColor);
		label_winner.setFont(new Font("Sans-Serif", Font.BOLD, 20));

		add(label_winner, c);
	}

	ResultView(Player winner1, Player winner2) {
		this();

		GridBagConstraints c = new GridBagConstraints();
		c.weighty = 1;


		JPanel panel_left = new JPanel();
		JPanel panel_right = new JPanel();

		JLabel label_winner1 = new JLabel(winner1.getColor().toString());
		JLabel label_winner2 = new JLabel(winner2.getColor().toString());


		Color bgColor1 = null;
		Color bgColor2 = null;
		Color textColor1 = Color.BLACK;
		Color textColor2 = Color.BLACK;

		switch (winner1.getColor()) {
			case BLACK:
			case WHITE:
				bgColor1 = Color.BLACK;
				textColor1 = Color.WHITE;
				bgColor2 = Color.WHITE;
				textColor2 = Color.BLACK;
				break;
			case RED:
			case GREEN:
				bgColor1 = Color.RED;
				textColor1 = Color.WHITE;
				bgColor2 = Color.GREEN;
				textColor2 = Color.WHITE;
				break;
		}


		label_winner1.setForeground(textColor1);
		label_winner2.setForeground(textColor2);
		panel_left.setBackground(bgColor1);
		panel_right.setBackground(bgColor2);
		label_winner1.setFont(new Font("Sans-Serif", Font.BOLD, 50));
		label_winner2.setFont(new Font("Sans-Serif", Font.BOLD, 50));

		c.weighty = 1;
		c.anchor = GridBagConstraints.CENTER;

		panel_left.add(label_winner1, c);
		panel_right.add(label_winner2, c);

		c.weightx = 0.5;
		c.fill = GridBagConstraints.BOTH;

		add(panel_left, c);
		add(panel_right, c);
	}
}
