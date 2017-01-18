package model;

public class Pin {

	//int number;
	int X;
	int Y;
	int radius;
	boolean isUp;
	
	public Pin() {
		X = 0;
		Y = 0;
		radius = 10;
		isUp = true;
	}
	
	 public Pin(int X, int Y) {
		this.X = X;
		this.Y = Y;
		radius = 10;
		isUp = true;
	}
	
	public void setStatus(boolean isUp) {
		this.isUp = isUp;
	}
	
	public void setX(int X) {
		this.X = X;
	}
	
	public void setY(int Y) {
		this.Y = Y;
	}
	
	public void setRadius(int radius) {
		this.radius = radius;
	}
	
	
	
	public boolean getStatus() {
		return isUp;
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
	
}
