import processing.core.PApplet;

public class Block {

	private PApplet p;
	private int x, y, h;
	
	public Block(int x, int y, int h, PApplet parent) {
		this.x = x;
		this.y = y;
		this.h = h;
		this.p = parent;
	}
	
	public void slide() {
		this.x -= 2;
		
		if(this.x < -50)
			refresh();
	}
	
	public void refresh() {
		this.x = p.width + 20;
		this.h = (int) p.random(300) + 100;
		this.y = (int) p.random(p.height - this.h);
	}
	
	public void draw() {
		p.fill(255);
		p.rect(x, 0, 50, p.height);
		p.fill(0);
		p.rect(x, y, 50, h);
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public int getH() {
		return this.h;
	}
	
}
