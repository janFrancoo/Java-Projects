import processing.core.PApplet;

public class Space extends PApplet {

	Planet sun;
	
	public static void main(String[] args) {
		PApplet.main("Space");
	}
	
	public void setup() {
		sun = new Planet(this, 100, 0, 0);
		sun.spawnMoons(5, 1);
	}
	
	public void settings() {
		size(700, 500);
	}
	
	public void draw() {
		background(0);
		translate(width/2, height/2);
		sun.show();
		sun.update();
	}

}
