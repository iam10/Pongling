package model;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class Main {
		
	public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
    
    private static void createAndShowGUI() {
        System.out.println("Created GUI on EDT? "+
                SwingUtilities.isEventDispatchThread());
        JFrame f = new JFrame("Pongling");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //f.setSize(100,1000);
        f.add(new MyPanel());
        f.pack();
        f.setVisible(true);
    }
}


class MyPanel extends JPanel implements ActionListener {
	
	Timer timer;
	Game game = new Game();

    public MyPanel() {
    	setBorder(BorderFactory.createLineBorder(Color.black));
    	timer = new Timer(10, this);
        timer.start();
    }
	
	public void actionPerformed(ActionEvent ev){
    	Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
    	game.update(mouseLocation);
    	
    	Game.pongBall.detectCollisions();
    	repaint();
	}
	
	public Dimension getPreferredSize() {
        return new Dimension(1200,600);
    }
	
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
        game.draw(g);
    }
}
