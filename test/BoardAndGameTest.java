import core.GameManager;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class BoardAndGameTest {

	public static void main(String[] args) {
		// Frame
		JFrame frame = new JFrame("BoardAndGameTest");
		frame.setSize(new Dimension(1280, 720));
		frame.getContentPane().setBackground(Color.WHITE);

		GameManager gm = new GameManager(2);

		frame.getContentPane().add(gm.getBoard());

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
