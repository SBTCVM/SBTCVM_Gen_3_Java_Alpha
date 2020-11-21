package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.Timer;

import libcalc.TernUtil;
import memory.LongQueue;

public class Display  extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	private final BufferedImage[] images;
	private final int size;
	
	private final GraphicsOp[] opcodes = new GraphicsOp[] {
			this::placeHolder, //0
			this::setx1, //1
			this::sety1, //2
			this::setx2, //3
			this::sety2, //4
			this::setColor, //5
			this::plotLine, //6
			this::placeHolder, //7
			this::plotRect, //8
			this::setW, //9
			this::setH, //10
			this::placeHolder, //11
			this::placeHolder, //12
			this::setx3, //13
			this::sety3, //14
			this::plotTriangle, //15
			this::copy, //16
			this::blit, //17
			this::selectBuffer //18
			//more to be added
	};
	
	private final LongQueue op, data;
	
	private Graphics2D graphics;
	
	private final Timer timer;
	
	public Display(int size) {
		this.images = new BufferedImage[27];
		this.size = size;
		this.timer = new Timer(1, this);
		this.timer.start();
		
		this.op = new LongQueue(531441);
		this.data = new LongQueue(531441);
		
		for(int i = 0; i < images.length; i++) {
			images[i] = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
		}
		
		this.graphics = (Graphics2D) images[13].getGraphics();
		setColor(0);
		this.graphics.fillRect(0, 0, images[13].getWidth(), images[13].getHeight());
	}
	
	private long x1, y1, x2, y2, x3, y3;
	private long rw, rh;
	
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
	
	public void setW(long rw) {
		this.rw = rw;
	}
	public void setH(long rh) {
		this.rh = rh;
	}
	
	public void placeHolder(long n) {
		//do nothing
	}
	
	public void setColor(long n) {
		int b = (int) TernUtil.cast(n, 3);
		int g = (int) TernUtil.cast(TernUtil.shr(n, 3), 3);
		int r = (int) TernUtil.cast(TernUtil.shr(n, 6), 3);
		graphics.setColor(new Color((int) ((r+13) * (255/27.0)), (int) ((g+13) * (255/27.0)), (int) ((b+13) * (255/27.0))));
	}
	
	public void plotRect(long n) {
		graphics.fillRect(getx1()+size/2, gety1()+size/2, (int)rw, (int)rh);
	}
	public void plotLine(long n) {
		graphics.drawRect(getx1()+size/2, gety1()+size/2, getx2()+size/2, gety2()+size/2);
	}
	public void plotTriangle(long n) {
		graphics.fillPolygon(new int[] {getx1()+size/2, getx2()+size/2, getx3()+size/2}, new int[] {gety1()+size/2, gety2()+size/2, gety3()+size/2}, 3);
	}
	
	public void selectBuffer(long n) {
		buffN = (int) n;
		graphics = (Graphics2D) images[buffN + 13].getGraphics();
	}
	public void blit(long n) {
		graphics.drawImage(images[buffN + 13], 0, 0, null);
	}
	public void copy(long n) {
		Graphics2D g = (Graphics2D) images[(int) (n + 13)].getGraphics();
		g.drawImage(images[buffN + 13], 0, 0, null);
	}
	
	public int getx1() {
		if(x1 < -121) return -121;
		else if(x1 > 121) return 121;
		return (int) x1;
	}
	public int getx2() {
		if(x2 < -121) return -121;
		else if(x2 > 121) return 121;
		return (int) x2;
	}
	public int getx3() {
		if(x3 < -121) return -121;
		else if(x3 > 121) return 121;
		return (int) x3;
	}
	
	public int gety1() {
		if(y1 < -121) return -121;
		else if(y1 > 121) return 121;
		return (int) y1;
	}
	public int gety2() {
		if(y2 < -121) return -121;
		else if(y2 > 121) return 121;
		return (int) y2;
	}
	public int gety3() {
		if(y3 < -121) return -121;
		else if(y3 > 121) return 121;
		return (int) y3;
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
		synchronized(op) {
			if(op.size() > 0) {
				for(int i = 0; i < op.size(); i++) opcodes[(int) op.popFront()].calc(data.popFront());
			}
		}
		g.scale(scale, scale);
		g.drawImage(images[13], 0, 0, null);
	}
	
	public synchronized void submit(long opcode, long dat) {
		synchronized(op) {
			op.pushEnd(opcode);
			data.pushEnd(dat);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
	}
	
	private interface GraphicsOp {
		public void calc(long n);
	}
}
