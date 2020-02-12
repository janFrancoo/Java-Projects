import processing.core.PApplet;

public class Bird {
	
	private PApplet p;
	private int x, y, r;
	
	public Bird(int x, int y, int r, PApplet parent) {
		this.x = x;
		this.y = r;
		this.r = r;
		this.p = parent;
	}
	
	public void draw() {
		p.fill(175, 0, 0);
		p.circle(x, y, r);
	}
	
	public void fly() {
		if (this.y + this.r < p.height) {
			this.y += 2;
		}
	}
	
	public void up() {
		this.y -= 50;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getR() {
		return this.r;
	}
	
	public int getY() {
		return this.y;
	}

}
