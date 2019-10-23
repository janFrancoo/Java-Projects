import processing.core.PApplet;

public class Fire {
	
	int sx, sy, x, y;
	PApplet parent;
	
	public Fire(PApplet p, int x, int y) {
		parent = p;
		sx = 10;
		sy = 20;
		this.x = x;
		this.y = y;
	}
	
	public void show() {
		parent.fill(177, 162, 0);
		parent.noStroke();
		parent.rect(x, y, sx, sy);
	}
	
	public void update() {
		y -= 5;
	}

}
