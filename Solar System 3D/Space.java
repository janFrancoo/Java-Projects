import peasy.*;
import processing.core.PApplet;

public class Space extends PApplet {

	Planet sun;
	PeasyCam cam;
	
	public static void main(String[] args) {
		PApplet.main("Space");
	}
	
	public void setup() {
		
		cam = new PeasyCam(this, 100);
		sun = new Planet(this, 65, 0, 0);
		sun.spawnMoons(6, 1);
	}
	
	public void settings() {
		size(600, 450, P3D);
	}
	
	public void draw() {
		background(0);
		lights();
		sun.show();
		sun.update();
	}

}
