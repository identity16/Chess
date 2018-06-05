import core.GameManager;

import javax.swing.*;
import java.awt.*;

public class BoardAndGameTest {

	public static void main(String[] args) {
		// Frame
		JFrame frame = new JFrame("BoardAndGameTest");
		frame.setSize(new Dimension(1280, 720));
		frame.getContentPane().setBackground(Color.WHITE);
		frame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

		GameManager gm2 = null;
		GameManager gm4 = null;
		try {
			gm2 = new GameManager(2);
			gm4 = new GameManager(4);
		} catch (Exception e) {
			e.printStackTrace();
		}

		assert gm2 != null && gm4 != null;
		frame.getContentPane().add(gm2.getBoard());
		frame.getContentPane().add(gm4.getBoard());

		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
