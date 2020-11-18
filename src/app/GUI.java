package app;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class GUI extends JPanel implements MouseListener, MouseWheelListener, ActionListener {
	private static final long serialVersionUID = -1547704781734736532L;
	
	Timer timer;
    public GUI() {
        setBackground(Color.BLACK);
        setFocusable(true);
        timer = new Timer(1, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
    	super.paintComponent(g);
        draw((Graphics2D) g);
    }
    private void draw(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        
//        TFloat za, zb, ca, cb, sm;
//        TFloat tza1 = new TFloat();
//        TFloat tza2 = new TFloat();
//        for(int i = 0; i < 576; i++) {
//        	for(int j = 0; j < 576; j++) {
//            	
//        		ca = new TFloat((i-400), -5);
//				cb = new TFloat((j-288), -5);
//				
//				za = new TFloat((i-400), -5);
//				zb = new TFloat((j-288), -5);
//				
//				sm = new TFloat(0);
//				sm.selfadd(za.mul(za));
//				sm.selfadd(zb.mul(zb));
//				int k = 0;
//				while(!sm.greaterThan(TFloat.FOUR) && k < 100) {
////					zb.zero();
////					za.zero();
//					
////					tza1.zero();
////					tza2.zero();
////					
////					tza1.selfadd(za);
////					tza1.selfmul(za);
////					tza2.add(zb);
////					tza2.selfmul(zb);
////					tza1.selfsub(tza2);
//					tza1 = za.mul(za).sub(zb.mul(zb));
//					zb = za.mul(zb).mul(TFloat.TWO).add(cb);
//					za = tza1.add(ca);
//					sm.zero();;
//					sm.selfadd(za.mul(za));
//					sm.selfadd(za.mul(zb));
//					k++;
//				}
////        		
////				Color c = Color.getHSBColor((float) (sm.getDouble()/10), 1f, 1f);
////				g.setColor(new Color(c.getRed() - c.getRed()%27, c.getGreen() - c.getGreen()%27, c.getBlue() - c.getBlue()%27));
//				
//				g.setColor((!sm.greaterThan(TFloat.FOUR)) ? Color.BLACK : Color.WHITE);
//				g.fillRect(i, j, 1, 1);
//            }
//        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}