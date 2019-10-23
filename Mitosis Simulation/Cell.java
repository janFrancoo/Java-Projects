import processing.core.PApplet;

public class Cell {

	PApplet parent;
	float x, y, r, dx, dy;
	
	public Cell(PApplet p, float x, float y, float r) {
		parent = p;
		if(x == 0 || y == 0) {
			this.x = parent.random(parent.width - 50);
			this.y = parent.random(parent.height);
		}
		else {
			this.x = x;
			this.y = y;
		}
		this.r = r;
	}
	
	public void show() {
		parent.noStroke();
		parent.fill(249, 59, 245, 127);
		parent.circle(x, y, r);
	}
	
	public void update() {
		dx = parent.random(-1, 1);
		dy = parent.random(-1, 1);
		x += dx;
		y += dy;
		if(r < 50) {
			r += 0.01;
		}
	}
	
}
