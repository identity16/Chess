package core;

import javax.swing.*;
import java.awt.*;

public class MainView extends JPanel {

    public MainView() {
    	setLayout(new GridBagLayout());
        setBackground(Color.WHITE);

        JPanel menu = new JPanel();
        menu.setLayout(new GridLayout(0, 1, 0, 20));
		menu.setPreferredSize(new Dimension(300, 190));

		JButton btn_1vs1 = new JButton("1 vs 1");
		JButton btn_2vs2 = new JButton("2 vs 2");
		JButton btn_exit = new JButton("EXIT");

		btn_1vs1.setPreferredSize(new Dimension(300, 50));
		btn_2vs2.setPreferredSize(new Dimension(300, 50));
		btn_exit.setPreferredSize(new Dimension(300, 50));

		btn_1vs1.addActionListener(e -> {
			Container parent = getParent();

			getParent().add(new GameView(2));
			getParent().remove(this);

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

}