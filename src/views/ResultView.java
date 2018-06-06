package views;

import core.GameManager;
import core.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

// 결과 view
public class ResultView extends JPanel {

	// Constructors
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

	// Result for 1 vs 1
	public ResultView(Player winner) {
		this();

		GridBagConstraints c = new GridBagConstraints();
		JLabel label_winner;


		Color bgColor = null;
		Color textColor = Color.WHITE;
		if(winner != null) {
			label_winner = new JLabel(winner.getColor().toString());

			switch (winner.getColor()) {
				case BLACK:
					bgColor = Color.BLACK;
					break;
				case RED:
					bgColor = Color.RED;
					break;
				case WHITE:
					bgColor = Color.WHITE;
					textColor = Color.BLACK;
					break;
				case GREEN:
					bgColor = Color.GREEN;
					break;
			}
		} else {
			label_winner = new JLabel("DRAW");
			bgColor = new Color(0xD5, 0x97, 0x59);
			textColor = Color.WHITE;
		}

		c.weighty = 1;

		setBackground(bgColor);

		label_winner.setForeground(textColor);
		label_winner.setFont(new Font("Sans-Serif", Font.BOLD, 50));

		add(label_winner, c);
	}

	// Result for 2 vs 2
	public ResultView(Player winner1, Player winner2) {
		this();

		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.CENTER;
		c.weighty = 1;


		JPanel panel_left = new JPanel();
		JPanel panel_right = new JPanel();

		JLabel label_winner1 = new JLabel(winner1.getColor().toString());
		JLabel label_winner2 = new JLabel(winner2.getColor().toString());

		Color bgColor1 = null;
		Color bgColor2 = null;
		Color textColor1 = null;
		Color textColor2 = null;

		// Determine Color
		switch (winner1.getColor()) {
			case BLACK:
				bgColor1 = Color.BLACK;
				textColor1 = Color.WHITE;
				bgColor2 = Color.WHITE;
				textColor2 = Color.BLACK;
				break;
			case WHITE:
				bgColor1 = Color.WHITE;
				textColor1 = Color.BLACK;
				bgColor2 = Color.BLACK;
				textColor2 = Color.WHITE;
				break;
			case RED:
				bgColor1 = Color.RED;
				textColor1 = Color.WHITE;
				bgColor2 = Color.GREEN;
				textColor2 = Color.WHITE;
				break;
			case GREEN:
				bgColor1 = Color.GREEN;
				textColor1 = Color.WHITE;
				bgColor2 = Color.RED;
				textColor2 = Color.WHITE;
				break;
		}


		// Set Panels
		panel_left.setLayout(new GridBagLayout());
		panel_right.setLayout(new GridBagLayout());
		panel_left.setBackground(bgColor1);
		panel_right.setBackground(bgColor2);
		panel_left.setPreferredSize(new Dimension(getWidth()/2, getHeight()));
		panel_right.setPreferredSize(new Dimension(getWidth()/2, getHeight()));

		// Set Labels
		label_winner1.setForeground(textColor1);
		label_winner2.setForeground(textColor2);
		label_winner1.setFont(new Font("Sans-Serif", Font.BOLD, 50));
		label_winner2.setFont(new Font("Sans-Serif", Font.BOLD, 50));


		panel_left.add(label_winner1, c);
		panel_right.add(label_winner2, c);

		c.weightx = 0.5;
		c.fill = GridBagConstraints.BOTH;

		add(panel_left, c);
		add(panel_right, c);
	}

}
