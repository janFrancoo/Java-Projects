import processing.core.PApplet;
import processing.core.PVector;

public class Particle {

	PApplet p;
	PVector pos, vel, acc, prevPos;
	int maxSpeed = 2;
	
	
	public Particle(PApplet parent) {
		this.p = parent;
		pos = new PVector(p.random(p.width), p.random(p.height));
		vel = PVector.random2D();
		acc = new PVector(0, 0);
		prevPos = pos.copy();
	}
	
	public void update() {
		vel.add(acc);
		vel.limit(maxSpeed);
		pos.add(vel);
		acc.mult(0);
	}
	
	public void applyForce(PVector force) {
		acc.add(force);
	}
	
	public void show() {
		p.stroke(0, 5);
		p.strokeWeight(1);
		p.line(pos.x, pos.y, prevPos.x, prevPos.y);
		updatePrev();
	}
	
	public void edges() {
		if (pos.x > p.width) {
			pos.x = 0;
			updatePrev();
		}
		if (pos.x < 0) {
			pos.x = p.width;
			updatePrev();
		}
		if (pos.y > p.height) {
			pos.y = 0;
			updatePrev();
		}
		if (pos.y < 0) {
			pos.y = p.height;
			updatePrev();
		}
	}
	
	public void updatePrev() {
		prevPos.x = pos.x;
		prevPos.y = pos.y;
	}
	
	public void follow(PVector[] vectors, int scale, int cols) {
		int x = PApplet.floor((pos.x / scale));
		int y = PApplet.floor((pos.y / scale));
		int index = x + y * cols;
		if(index < vectors.length) {
			PVector force = vectors[index];
			applyForce(force);
		}
	}
	
}
