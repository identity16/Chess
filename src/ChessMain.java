import core.MainView;

import javax.swing.*;

class ChessMain {

	public static void main(String[] args) {
		JFrame jFrame = new JFrame();
		JPanel mainView = new MainView();


		jFrame.setSize(500, 250);

		jFrame.add(mainView);

		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setLocationRelativeTo(null);
		jFrame.setVisible(true);
	}
}