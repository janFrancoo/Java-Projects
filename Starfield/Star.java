
import processing.core.PApplet;

public class Star {
	
	float x, y, z, pz;
	PApplet parent;
	
	public Star(PApplet p) {
		parent = p;
		x = parent.random(-parent.width, parent.width);
		y = parent.random(-parent.height, parent.height);
		z = parent.random(parent.width);
		pz = z;
	}
	
	void update() {
		z -= StarField.speed;
		if(z < 1) {
			x = parent.random(-parent.width, parent.width);
			y = parent.random(-parent.height, parent.height);
			z = parent.width;
			pz = z;
		}
	}
	
	void show() {
		float sx = PApplet.map(x/z, 0, 1, 0, parent.width);
		float sy = PApplet.map(y/z, 0, 1, 0, parent.height);
		parent.stroke(255);
		float px = PApplet.map(x/pz, 0, 1, 0, parent.height);
		float py = PApplet.map(y/pz, 0, 1, 0, parent.height);
		pz = z;
		parent.line(px, py, sx, sy);
	}
	
}
