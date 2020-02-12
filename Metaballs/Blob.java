import processing.core.PApplet;
import processing.core.PVector;

public class Blob {

	float r;
	PVector pos, vel;
	PApplet p;
	
	public Blob(float x, float y, PApplet parent) {
		p = parent;
		pos = new PVector(x, y);
		vel = PVector.random2D();
		vel.mult(p.random(2, 5));
		r = p.random(40, 100);
	}
	
	public void update() {
		pos.add(vel);
		
		if (pos.x > p.width || pos.x < 0)
			vel.x *= -1;
		if (pos.y > p.height || pos.y < 0)
			vel.y *= -1;
	}

	public void show() {
		p.noFill();
		p.stroke(0);
		p.strokeWeight(4);
		p.ellipse(pos.x, pos.y, r * 2, r * 2);
	}
	
}
