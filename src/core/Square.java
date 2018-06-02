package core;

import kr.ac.cau.mecs.lenerd.chess.ImagePanel;
import java.awt.*;

public class Square extends ImagePanel {
	public static final int WIDTH = 80;
	public static final int HEIGHT = 80;


    public static final Color COLOR_DARK = new Color(59, 59, 59);
	public static final Color COLOR_BRIGHT = new Color(238, 238, 238);

    private int pos_x;

    private int pos_y;

    private Color color;

    public Square(int x, int y, Color color) {
        this.pos_x = x;
        this.pos_y = y;
        this.color = color;

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(this.color);
    }

    public int[] getPosition() {
        return new int[] {pos_x, pos_y};
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;

        setBackground(this.color);
    }

}