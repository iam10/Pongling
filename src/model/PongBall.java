package model;

import java.awt.Color;
import java.awt.Graphics;


public class PongBall {

	protected String name;
	protected double angle;
	protected int speed;
	protected int rotation;
	protected int X;
	protected int Y;
	protected int xSpeed;
	protected int ySpeed;
	public int radius;
	protected String portraitLocation;
	
	
	
	public PongBall() {
		this.radius = 10;
		angle = 0;
		speed = 0;
		rotation = 0;
		X = 50;
		Y = 50;
	}
	
	public PongBall(int radius) {
		this.radius = radius;
		angle = 0;
		speed = 0;
		rotation = 0;
		X = 50;
		Y = 50;
	}
	
	//Setters
	public void setAngle(double angle) {
		this.angle = angle;
	}
	
	public void setSpeed(int speed) {
		this.xSpeed = speed/2;
		this.ySpeed = speed/2;
	}
	
	public void setRotation(int rotation) {
		this.rotation = rotation;
	}
	
	public void setName(String name) {
		this.name = name;
		if(name == "PongBall") {
			this.portraitLocation = "src/res/img/pong_ball.png";
		}
	}
	
	public void setX(int X) {
		this.X = X;
	}
	
	public void setY(int Y) {
		this.Y = Y;
	}
	
	
	//Getters
	public double getAngle() {
		return angle;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public int getRotation() {
		return rotation;
	}
	
	public String getName() {
		return name;
	}
	
	public int getX() {
		return X;
	}
	
	public int getY() {
		return Y;
	}
	
	public int getRadius() {
		return radius;
	}
	
	
	public void xSpeed(int i) {
		this.xSpeed = i;
	}
	
	public void ySpeed(int i) {
		this.ySpeed = i;
	}
	
	public void update() {
		this.X += xSpeed;
		this.Y += ySpeed;
		//Check boundaries
		if (X >= 1200-radius && xSpeed > 0) xSpeed = -xSpeed;
		if (X <= radius && xSpeed < 0) xSpeed = -xSpeed;
		if (Y >= 600-radius && ySpeed > 0) ySpeed = -ySpeed;
		if (Y <= radius && ySpeed < 0) ySpeed = -ySpeed;
	}
	

	public void draw(Graphics g) {
		g.setColor(Color.RED);
        g.fillOval(X - radius, Y - radius,radius*2,radius*2);
        g.setColor(Color.BLACK);
        g.drawOval(X - radius, Y - radius,radius*2,radius*2);
	}
	
	public void detectCollisions() {
		//Collision with Bowling Ball
		int bX = Game.bowlingBall.X;
		int bY = Game.bowlingBall.Y;
		int bRadius = Game.bowlingBall.radius;
		if (X + radius + bRadius > bX && X < bX + radius + bRadius && Y + radius + bRadius > bY && Y < bY + radius + bRadius) {
			//Calculate distance between balls
			double d = Math.sqrt(((X - bX) * (X - bX)) + ((Y - bY) * (Y - bY)));
			if (d < 0) { d = d * -1; }
			if (d < radius + bRadius) {
				calculateNewVelocities(this, Game.bowlingBall);
			}
		}
		
		//Collision with Frame
		if(Y - radius <= Game.frame[0].getLength()) {
	    	//Here I normalize the X position relative to length of frame
	    	int frameNumber = (int) Math.floor(X / Game.frame[0].getLength());
	    	double temp = (double)X / Game.frame[0].getLength();
	    	temp -= frameNumber;
	    	Game.frame[frameNumber].setFirstShot();
	    	
	    	//System.out.println(frameNumber);
	    	//Check if it hit the back of frame or side
	    	if(Y - radius <= Game.frame[0].getLength() * 0.05) {
	    		ySpeed = -ySpeed;
		    	this.Y = (int)(1 + Game.frame[0].getLength() * 0.05) + radius;
	    		//while(pBall.getY() - pBall.getRadius() <= frame[0].getLength() * 0.05) pBall.changeY((int) changeY);
	    		//pBall.changeY((int) changeY);
	    	}
	    	else if(temp <= 0.2 || temp >= 0.8) {
	    		//This needs to be fixed
	    		if((Y - radius == Game.frame[0].getLength() * 0.05)) {
		    		ySpeed = Math.abs(ySpeed);
		    		while(Y - 0.2 * radius >= Game.frame[0].getLength() && Y - radius <= Game.frame[0].getLength()) this.ySpeed = (int)ySpeed;
		    		//pBall.changeY((int) changeY);
	    		}
	    	}
	    	//Check is it hits side of frame while inside
	    	if(temp <= 0.2 || temp >= 0.8) {
	    		if(temp <= 0.2){
	    			xSpeed = Math.abs(xSpeed);
		    		this.speed = (int) xSpeed;
	    		}
	    		else if(temp >= 0.8){
	    			xSpeed = -Math.abs(xSpeed);
		    		this.speed = (int) xSpeed;
	    		}
	    	}
	    	
	    	Pin pin[] = {
	    			Game.frame[frameNumber].getPin(0),
	    			Game.frame[frameNumber].getPin(1),
	    			Game.frame[frameNumber].getPin(2),
	    			Game.frame[frameNumber].getPin(3),
	    			Game.frame[frameNumber].getPin(4),
	    			Game.frame[frameNumber].getPin(5),
	    			Game.frame[frameNumber].getPin(6),
	    			Game.frame[frameNumber].getPin(7),
	    			Game.frame[frameNumber].getPin(8),
	    			Game.frame[frameNumber].getPin(9)
	    	};
	    	int pinRadius = pin[0].getRadius();
	    	for(int i = 0; i < 10; i++) {
	    		if((int) Math.sqrt(Math.pow(Math.abs(X - pin[i].getX()), 2) + Math.pow(Math.abs(Y - pin[i].getY()), 2)) < (pinRadius + radius)){
	    			pin[i].setStatus(false);
	    			Game.frame[frameNumber].updateScore();
	    			//this.updateTotalScore();
	    		}
	    	}
	    }
		
	}
	
	public void calculateNewVelocities(PongBall firstBall, BowlingBall secondBall) {
	    double mass1 = firstBall.radius;
	    double mass2 = secondBall.radius;
	    double velX1 = firstBall.xSpeed;
	    double velX2 = secondBall.xSpeed;
	    double velY1 = firstBall.ySpeed;
	    double velY2 = secondBall.ySpeed;
        
	    int newVelX1 = (int)Math.round((velX1 * (mass1 - mass2) + (2 * mass2 * velX2)) / (mass1 + mass2));
	    int newVelY1 = (int)Math.round((velY1 * (mass1 - mass2) + (2 * mass2 * velY2)) / (mass1 + mass2));
		
		
        firstBall.xSpeed = newVelX1;
        firstBall.ySpeed = newVelY1;
        
        firstBall.X = firstBall.X + newVelX1;
        firstBall.Y = firstBall.Y + newVelY1;
	}
	
}
