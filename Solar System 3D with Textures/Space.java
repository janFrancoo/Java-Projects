import peasy.*;
import processing.core.PApplet;
import processing.core.PImage;

public class Space extends PApplet {

	Planet sun;
	PeasyCam cam;
	PImage sunTexture;
	
	public static void main(String[] args) {
		PApplet.main("Space");
	}
	
	public void setup() {
		sunTexture = loadImage("imgs/sun.jpg");
		cam = new PeasyCam(this, 100);
		sun = new Planet(this, 65, 0, 0, sunTexture);
		sun.spawnMoons(3, 1);
	}
	
	public void settings() {
		size(600, 450, P3D);
	}
	
	public void draw() {
		background(0);
		lights();
		pointLight(255, 255, 255, 0, 0, 0);
		sun.show();
		sun.update();
	}

}
