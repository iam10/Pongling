package model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

public class Frame {

	Pin[] pin;
	int frameNumber;
	int X;
	int Y;
	int length;
	boolean open;
	boolean firstShot;
	float wallWidth;
	int score;
	boolean strike;
	boolean spare;
	
	public Frame() {
		frameNumber = 0;
		pin = new Pin[10];
		X = 0;
		Y = 0;
		length = 100;
		wallWidth = (float) ((float) length * 0.05);
		score = 0;
		strike = false;
		spare = false;
		firstShot = true;
	}
	
	public Frame(int frameNumber) {
		this.frameNumber = frameNumber;
		pin = new Pin[10];
		X = 0;
		Y = 0;
		length = 100;
		wallWidth = (float) ((float) length * 0.05);
		score = 0;
		strike = false;
		spare = false;
		firstShot = true;
	}
	
	public Frame(int frameNumber, int X, int Y) {
		this.frameNumber = frameNumber;
		pin = new Pin[10];
		this.X = X;
		this.Y = Y;
		length = 100;
		wallWidth = (float) ((float) length * 0.05);
		score = 0;
		strike = false;
		spare = false;
		firstShot = true;
		
		int i = 9;
		for(int l = 0; l < 4; l++) {
			int pinSize = 8;
			int dx = pinSize / 2;
			int margin = length - (int) (2 * wallWidth + pinSize * 2 * 4 + dx * 3);
			int pinY = Y + ( (int) wallWidth + margin / 2 + l * (pinSize * 2 + dx) + pinSize);
			int pinX = X + length - ( (int) wallWidth + margin / 2 + l * ( (pinSize * 2 + dx) / 2 ) + pinSize);
			for(int p = l; p < 4; p++) {
				pin[i] = new Pin(pinX, pinY);
				pinX -= (pinSize * 2) + dx;
				--i;
			}
		}
	}
	
	public void setX(int X) {
		this.X = X;
	}
	
	public void setY(int Y) {
		this.Y = Y;
	}
	
	public void setLength(int length) {
		this.length = length;
	}
	
	public void setFirstShot() {
		firstShot = false;
	}
	

	
	public int getX() {
		return X;
	}
	
	public int getY() {
		return Y;
	}
	
	public int getLength() {
		return length;
	}
	
	public boolean isFirstShot() {
		return firstShot;
	}
	
	
	
	public void addPin(int i) {
		pin[i] = new Pin();
	}
	
	public void removePin(int i) {
		pin[i].setStatus(false);
	}
	
	public Pin getPin(int i) {
		return pin[i];
	}
	
	public void updateScore() {
    	for(int i = 0; i < 12; ++i) {
    		int pinCount = 0;
    		for(int j = 0; j < 10; ++j) {
    			if(this.getPin(j).getStatus()) ++pinCount;
    		}
    		score = 10 - pinCount;
    		if( pinCount == 10 ) {
    			if(firstShot == true) {
    				strike = true;
    			}
    			else {
    				spare = true;
    			}
    		}
    	}
	}
	
	public int getScore() {
		return score;
	}
	
	public boolean isStrike() {
		return strike;
	}
	
	public boolean isSpare() {
		return spare;
	}
	
	
	public void paintFrame(Graphics g) {
		
		g.setColor(Color.BLUE);
        for(int i = 0; i < 10; i++) {
        	if(pin[i].getStatus()) {
        		g.fillOval(pin[i].getX() - pin[i].getRadius(), pin[i].getY() - pin[i].getRadius(), pin[i].getRadius() * 2, pin[i].getRadius() * 2);
        		g.drawOval(pin[i].getX() - pin[i].getRadius(), pin[i].getY() - pin[i].getRadius(), pin[i].getRadius() * 2, pin[i].getRadius() * 2);
        		//System.out.println(pin[i].getX() + " " + pin[i].getY());
        	}
        }
        
        g.setColor(Color.BLACK);
        Graphics2D g2 = (Graphics2D) g;
		float thickness = (float) ((float) length * 0.05);
		Stroke oldStroke = g2.getStroke();
		g2.setStroke(new BasicStroke(thickness));
		g2.drawRect(X, Y, length, length);
		g2.setStroke(oldStroke);
		
		
		
        //g.setColor(Color.BLACK);
        //g.drawRect(X, Y, length, length);
	}
	
	
}
