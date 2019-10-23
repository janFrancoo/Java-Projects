import java.util.ArrayList;

import processing.core.PApplet;

public class Panel extends PApplet{
	
	ArrayList<Cell> cells = new ArrayList<>();

	public static void main(String[] args) {
		PApplet.main("Panel");
	}
	
	public void setup() {
		cells.add(new Cell(this, 0, 0, 20));
		cells.add(new Cell(this, 0, 0, 20));
	}
	
	public void settings() {
		size(600, 350);
	}
	
	public void draw() {
		background(231, 235, 210);
		for(int i=0; i<cells.size(); i++) {
			cells.get(i).show();
			cells.get(i).update();
		}
	}
	
	public void mousePressed() {
		int size = cells.size();
		float randomX = random(-3, 3), randomY = random(-3, 3);
		float updatedR = (float) ((cells.get(0).r) * 0.8);
		for(int i=0; i<size; i++) {
			cells.add(new Cell(this, cells.get(i).x + randomX, cells.get(i).y + randomY, updatedR));
			cells.get(i).r = updatedR;
		}
	}

}
