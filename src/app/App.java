package app;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class App extends JFrame {
	private static final long serialVersionUID = 805679694800463754L;
	public GUI gui;

    public App() {
        gui = new GUI();
        setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
//        setResizable(false);
        setFocusable(true);
        addMouseListener(gui);
        addMouseWheelListener(gui);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(gui);
        pack();
        setVisible(true);
    }

}