package old_graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;

import javax.swing.JFrame;

import libcalc.TernUtil;

public class SBTGA_Old extends JFrame {
	private final Panel panel;
	public SBTGA_Old() {
		panel = new Panel(248);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(panel);
		Dimension size = new Dimension(800, 600);
		this.setPreferredSize(size);
		this.setResizable(true);
		this.setVisible(true);
		this.setFocusable(true);
		this.pack();
	}
	
	private long x1, y1, x2, y2, x3, y3;
	private long rw, rh;
	
	private int r, g, b;
	
	private int buffN = 0;
	
	public void setx1(long x1) {
		this.x1 = x1;
	}
	public void sety1(long y1) {
		this.y1 = y1;
	}
	
	public void setx2(long x2) {
		this.x2 = x2;
	}
	public void sety2(long y2) {
		this.y2 = y2;
	}
	
	public void setx3(long x3) {
		this.x3 = x3;
	}
	public void sety3(long y3) {
		this.y3 = y3;
	}
	
	public void rectWidth(long rw) {
		this.rw = rw;
	}
	public void rectHeight(long rh) {
		this.rh = rh;
	}
	
	public void drawRect(long n) {
		panel.addDrawCommand(new DrawCommand() {
			final int mx1 = (int) x1;
			final int my1 = (int) y1;
			final int mrw = (int) rw;
			final int mrh = (int) rh;
			public void draw(Graphics2D g) {
				g.fillRect(mx1+121, my1+121, mrw, mrh);
			}
		});
	}
	public void drawTri(long n) {
		panel.addDrawCommand(new DrawCommand() {
			final int mx1 = (int) x1;
			final int my1 = (int) y1;
			final int mx2 = (int) x2;
			final int my2 = (int) y2;
			final int mx3 = (int) x3;
			final int my3 = (int) y3;
			public void draw(Graphics2D g) {
				g.fillPolygon(new int[] {mx1, mx2, mx3}, new int[] {my1, my2, my3}, 3);
			}
		});
	}
	
	public void plotLine(long n) {
		panel.addDrawCommand(new DrawCommand() {
			public void draw(Graphics2D g) {
				g.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
			}
		});
	}
	
	public void setColor(long n) {
		int blue = (int) TernUtil.cast(n, 3);
		int green = (int) TernUtil.cast(TernUtil.shr(n, 3), 3);
		int red = (int) TernUtil.cast(TernUtil.shr(n, 6), 3);
		panel.addDrawCommand(new DrawCommand() {
			public void draw(Graphics2D g) {
				g.setColor(new Color((int) ((red+13) * (255/27.0)), (int) ((green+13) * (255/27.0)), (int) ((blue+13) * (255/27.0))));
			}
		});
	}
}
