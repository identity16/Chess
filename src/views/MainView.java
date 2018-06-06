package views;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class MainView extends JPanel {

    public MainView() {
    	// Layout Settings
    	setLayout(new GridBagLayout());

        JPanel menu = new JPanel();
        menu.setLayout(new GridLayout(0, 1, 0, 25));
		menu.setBackground(new Color(255, 255, 255, 0));
		menu.setOpaque(true);

		JButton btn_1vs1 = new JButton("1 vs 1");
		JButton btn_2vs2 = new JButton("2 vs 2");
		JButton btn_exit = new JButton("EXIT");

		btn_1vs1.setBackground(new Color(0xB7, 0x68, 0x00));
		btn_2vs2.setBackground(new Color(0xB7, 0x68, 0x00));
		btn_exit.setBackground(new Color(0xB7, 0x68, 0x00));

		btn_1vs1.setContentAreaFilled(false);
		btn_2vs2.setContentAreaFilled(false);
		btn_exit.setContentAreaFilled(false);

		btn_1vs1.setOpaque(true);
		btn_2vs2.setOpaque(true);
		btn_exit.setOpaque(true);

		btn_1vs1.setBorderPainted(false);
		btn_2vs2.setBorderPainted(false);
		btn_exit.setBorderPainted(false);

		btn_1vs1.setForeground(Color.WHITE);
		btn_2vs2.setForeground(Color.WHITE);
		btn_exit.setForeground(Color.WHITE);


		btn_1vs1.setFont(new Font("Sans-Serif", Font.BOLD, 25));
		btn_2vs2.setFont(new Font("Sans-Serif", Font.BOLD, 25));
		btn_exit.setFont(new Font("Sans-Serif", Font.BOLD, 25));

		btn_1vs1.setPreferredSize(new Dimension(239, 60));
		btn_2vs2.setPreferredSize(new Dimension(239, 60));
		btn_exit.setPreferredSize(new Dimension(239, 60));

		btn_1vs1.addActionListener(e -> {
			Container parent = getParent();

			parent.add(new GameView(2));
			parent.remove(this);

			parent.validate();
		});

		btn_2vs2.addActionListener(e -> {
			Container parent = getParent();

			getParent().add(new GameView(4));
			getParent().remove(this);

			parent.validate();
		});

		btn_exit.addActionListener(e -> System.exit(0));

        menu.add(btn_1vs1);
        menu.add(btn_2vs2);
        menu.add(btn_exit);

        add(menu, new GridBagConstraints());
    }

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		try {
			Image bg = ImageIO.read(new File("bg.jpeg"));
			g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}