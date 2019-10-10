import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;

public class Box {
	
	PApplet parent;
	PVector pos;
	float r;
	
	public Box(PApplet p, float x, float y, float z, float r) {
		parent = p;
		this.r = r;
		pos = new PVector(x, y, z);
	}
	
	void show() {
		parent.pushMatrix();
		parent.translate(pos.x, pos.y, pos.z);
		parent.fill(255);
		parent.box(r);
		parent.popMatrix();
	}
	
	ArrayList<Box> generate() {
		ArrayList<Box> boxes = new ArrayList<Box>();
		for(int i=-1; i<2; i++) {
			for(int j=-1; j<2; j++) {
				for(int k=-1; k<2; k++) {
					int sum = PApplet.abs(i) + PApplet.abs(j) + PApplet.abs(k);
					if(sum > 1) {
						float newR = r / 3;
						Box b = new Box(parent, pos.x + i * newR, pos.y + j * newR, pos.z + k * newR, newR);
						boxes.add(b);
					}
				}
			}
		}	
		return boxes;
	}

}
