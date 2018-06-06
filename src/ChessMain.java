import views.MainView;

import javax.swing.*;
import java.awt.*;

class ChessMain {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		JPanel mainView = new MainView();

		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setUndecorated(true);

		frame.getContentPane().setBackground(Color.WHITE);

		frame.add(mainView);

		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}