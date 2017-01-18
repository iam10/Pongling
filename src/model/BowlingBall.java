package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;


public class BowlingBall {

	protected String name;
	protected double angle;
	protected int speed;
	protected int rotation;
	protected int X;
	protected int Y;
	protected int xSpeed;
	protected int ySpeed;
	protected int radius;
	protected String portraitLocation;
	
	
	
	public BowlingBall() {
		this.radius = 10;
		angle = 0;
		speed = 0;
		rotation = 0;
		X = 50;
		Y = 50;
	}
	
	public BowlingBall(int radius) {
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
	}
	
	public void setRotation(int rotation) {
		this.rotation = rotation;
	}
	
	public void setName(String name) {
		this.name = name;
		if(name == "BowlingBall") {
			this.portraitLocation = "src/res/img/bowling_ball.jpg";
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
	
	public void update(Point mouseLocation) {
        int mouseX = (int) mouseLocation.getX();
        int mouseY = (int) mouseLocation.getY();
        
        if(mouseX < X + xSpeed && mouseX > X - xSpeed) {
	    	this.X = mouseX;
	    }
        else if(mouseX < X) {
	    	this.X = X - xSpeed;
	    }
	    else if(mouseX > X) {
	    	this.X = X + xSpeed;
	    }
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.RED);
        g.fillOval(X - radius, Y - radius,radius*2,radius*2);
        g.setColor(Color.BLACK);
        g.drawOval(X - radius, Y - radius,radius*2,radius*2);
	}
	
}