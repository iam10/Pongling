package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.io.IOException;
import java.util.Vector;



public class Game {
	static public BowlingBall bowlingBall = new BowlingBall(150);
	static public PongBall pongBall = new PongBall(15);
    static public Frame[] frame = new Frame[12];
	
	public Game() {
		
		bowlingBall.setX(15);
        bowlingBall.setY(660);
        bowlingBall.setSpeed(40);
        
        pongBall.setSpeed(100);
        pongBall.setAngle(315.0);
        pongBall.setX(250);
        pongBall.setY(250);
        
        int totalSpeed = 100;
        bowlingBall.xSpeed(5);
        bowlingBall.ySpeed(0);
        pongBall.xSpeed(2);
        pongBall.ySpeed(2);
        
        int frameX = 0;
        int frameY = 0;
        for(int i = 0; i < 12; ++i) {
        	frame[i] = new Frame(i, frameX, frameY);
        	frameX += 100;
        }
	}
	
	public void update(Point mouseLocation) {
        bowlingBall.update(mouseLocation);
        pongBall.update();
	}
	
	public void draw(Graphics g) {
		pongBall.draw(g);
		bowlingBall.draw(g);
		for(int i = 0; i < 12; ++i) {
        	frame[i].paintFrame(g);
        }
	}
	
	public void setBowlingBall(BowlingBall bowlingBall) {
		Game.bowlingBall = bowlingBall;
	}
	
	public BowlingBall getBowlingBall() {
		return bowlingBall;
	}
	
	public void save() throws IOException {
		
	}
	
	public void load() throws IOException {
		
	}
}
