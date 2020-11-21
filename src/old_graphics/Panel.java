package old_graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Panel extends JPanel implements ActionListener {
	private final BufferedImage[] images;
	private final int size;
	
	private volatile LinkedList<DrawCommand> buffer;
	private Graphics2D graphics;
	
	private final Timer timer;
	private Color color = Color.BLACK;
	
	public Panel(int size) {
		this.images = new BufferedImage[27];
		this.size = size;
		timer = new Timer(1, this);
		timer.start();
		this.buffer = new LinkedList<>();
		
		for(int i = 0; i < images.length; i++) {
			images[i] = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
		}
		
		this.graphics = (Graphics2D) images[13].getGraphics();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
		
		draw(g2);
	}
	
	private void draw(Graphics2D g) {
		double scale = ((double)getHeight())/images[13].getWidth();
		synchronized(buffer) {
			if(buffer.size() > 0) {
				for(int i = 0; i < buffer.size(); i++) buffer.removeFirst().draw(graphics);
			}
		}
		g.scale(scale, scale);
		g.drawImage(images[13], 0, 0, null);
	}
	
	public void setColor(int r, int g, int b) {
		graphics.setColor(new Color((int) (r * (255/27.0)), (int) (g * (255/27.0)), (int) (b * (255/27.0))));
	}
	
	public synchronized void addDrawCommand(DrawCommand command) {
		synchronized(buffer) {
			buffer.add(command);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
	}
}
