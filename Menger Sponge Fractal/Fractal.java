import java.util.ArrayList;
import processing.core.PApplet;

public class Fractal extends PApplet {
	
	float a = 0;
	ArrayList<Box> sponge;
	
	public static void main(String[] args) {
		PApplet.main("Fractal");
	}
	
	public void setup() {
		sponge = new ArrayList<Box>();
		Box b = new Box(this, 0, 0, 0, 200);
		sponge.add(b);
	}
	
	public void settings() {
		size(400, 400, P3D);
	}
	
	public void draw() {
		background(51);
		stroke(255);
		noFill();
		noStroke();
		lights();
		translate(width/2, height/2);
		rotateX(a);
		rotateY(a);
		rotateZ(a);
		for(Box b : sponge) {
			b.show();
		}
		a += 0.01;
	}
	
	public void mousePressed() {
		ArrayList<Box> next = new ArrayList<Box>();
		for(Box b : sponge) {
			ArrayList<Box> newBoxes = b.generate();
			next.addAll(newBoxes);
		}
		sponge = next;
	}

}
