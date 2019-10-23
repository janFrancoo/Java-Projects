import processing.core.PApplet;

public class Ball {
	
	PApplet parent;
	int x, y, r, dir = 1;

	public Ball(PApplet p, int x) {
		parent = p;
		r = 50;
		this.x = x;
		y = 50;
	}
	
	public void show() {
		parent.fill(177, 43, 111);
		parent.noStroke();
		parent.circle(x, y, r);
	}
	
	public void update() {
		if(dir == 1) {
			x += 1;
		}
		
		if(dir == -1) {
			x -= 1;
		}
	}
	
}
