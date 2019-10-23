import java.util.ArrayList;
import processing.core.PApplet;

public class Board extends PApplet{

	int ballX = 50;
	Ship ship = new Ship(this);
	ArrayList<Fire> fires = new ArrayList<>();
	ArrayList<Ball> balls = new ArrayList<>();
	
	public static void main(String[] args) {
		PApplet.main("Board");
	}
	
	public void setup() {
		for(int i=0; i<6; i++) {
			balls.add(new Ball(this, ballX));
			ballX += 75;
		}
	}
	
	public void settings() {
		size(600, 400);
	}
	
	public void draw() {
		background(0);
		ship.show();
		for(int i=0; i<fires.size(); i++) {
			fires.get(i).show();
			fires.get(i).update();
			if(fires.get(i).y < 0) {
				fires.remove(i);
			}
			for(int j=0; j<balls.size(); j++) {
				if(fires.size() != 0) {
					if(isInterrupted(balls.get(j).x, balls.get(j).y, balls.get(j).r, 
							fires.get(i).x, fires.get(i).y, fires.get(i).sx, fires.get(i).sy)) {
						balls.get(j).r += 5;
						fires.remove(i);
					}
				}
			}
		}
		for(int i=0; i<balls.size(); i++) {
			balls.get(i).show();
			balls.get(i).update();
			if(balls.get(i).x < 35) {
				lineDown(1);
				break;
			}
			if(balls.get(i).x > 550) {
				lineDown(-1);
				break;
			}
		}
	}
	
	public void keyPressed() {
		if(key == 'A' || key == 'a') {
			ship.update(0);
		}
		if(key == 'D' || key == 'd') {
			ship.update(1);
		}
		if(key == 'C' || key == 'c') {
			fire();
		}
	}
	
	public void fire() {
		fires.add(new Fire(this, ship.x, ship.y));
	}
	
	public boolean isInterrupted(float cx, float cy, float radius, float rx, float ry, float rw, float rh) {

	  float testX = cx;
	  float testY = cy;

	  if (cx < rx)         
		  testX = rx;     
	  else if (cx > rx+rw) 
		  testX = rx+rw;   
	  if (cy < ry)         
		  testY = ry;
	  else if (cy > ry+rh) 
		  testY = ry+rh;

	  float distX = cx-testX;
	  float distY = cy-testY;
	  float distance = sqrt((distX*distX) + (distY*distY));

	  if (distance <= radius) {
	    return true;
	  }
	  return false;
	}
	
	public void lineDown(int dir) {
		for(int i=0; i<balls.size(); i++) {
			balls.get(i).dir = dir;
			balls.get(i).y += 15;
		}
	}
	
}
