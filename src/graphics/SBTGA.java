package graphics;

import java.awt.Dimension;

import javax.swing.JFrame;

public class SBTGA  extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private final Display display;
	
	public SBTGA() {
		display = new Display(243);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(display);
		Dimension size = new Dimension(800, 600);
		this.setPreferredSize(size);
		this.setResizable(true);
		this.setVisible(true);
		this.setFocusable(true);
		this.pack();
	}
	
	public void setx1(long x1) { display.submit(1, x1); }
	public void sety1(long y1) { display.submit(2, y1); }
	public void setx2(long x2) { display.submit(3, x2); }
	public void sety2(long y2) { display.submit(4, y2); }
	
	public void setx3(long x3) { display.submit(13, x3); }
	public void sety3(long y3) { display.submit(14, y3); }
	
	public void setColor(long n) { display.submit(5, n); }
	public void plotLine(long n) { display.submit(6, n); }
	
	public void drawRect(long n) { display.submit(8, n); }
	public void rectWidth(long rw) { display.submit(9, rw); }
	public void rectHeight(long rh) { display.submit(10, rh); }
	
	public void drawTri(long n) { display.submit(15, n); }
}
